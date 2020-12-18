package com.Server.controller;

import com.Server.dto.Request.LoginRequest;
import com.Server.dto.Request.RegisterRequest;
import com.Server.dto.Response.JwtResponse;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.*;
import com.Server.security.JwtUtils;
import com.Server.service.CarService;
import com.Server.service.LocalizationService;
import com.Server.service.RoleService;
import com.Server.service.UserService;
import com.Server.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserService userServiceImpl;
    private LocalizationService localizationServiceImpl;
    private RoleService roleServiceImpl;
    private PasswordEncoder encoder;
    private CarService carServiceImpl;
    private SendMail sendMail;


    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userServiceImpl, LocalizationService localizationServiceImpl, RoleService roleServiceImpl, PasswordEncoder encoder, CarService carServiceImpl, SendMail sendMail) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userServiceImpl = userServiceImpl;
        this.localizationServiceImpl = localizationServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.encoder = encoder;
        this.carServiceImpl = carServiceImpl;
        this.sendMail = sendMail;
    }

    //Logowanie
    @ResponseBody
    @PostMapping("/authentication")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsimpl userDetails = (UserDetailsimpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        List<Localization> localizations = localizationServiceImpl.findAll();

        logger.info("------Logowanie user:"+loginRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                localizations,
                roles));
    }

    //Rejestracja.
    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            return  new ResponseEntity<>(userServiceImpl.save(registerRequest),HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ User Exist ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }
}
