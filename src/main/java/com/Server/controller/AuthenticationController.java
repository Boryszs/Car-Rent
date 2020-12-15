package com.Server.controller;

import com.Server.dto.Request.LoginRequest;
import com.Server.dto.Request.RegisterRequest;
import com.Server.dto.Response.JwtResponse;
import com.Server.dto.Response.MessageResponse;
import com.Server.model.*;
import com.Server.security.JwtUtils;
import com.Server.service.impl.*;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    LocalizationServiceImpl localizationServiceImpl;

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private CarServiceImpl carServiceImpl;

    @Autowired
    SendMail sendMail;

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

        if (userServiceImpl.existsByUsername(registerRequest.getUsername())) {

            return new ResponseEntity(new MessageResponse("User is exist"), HttpStatus.BAD_REQUEST);
        } else {
            //sendMail.sendMail(registerRequest.getUsername(), "Thank you for register account.");
            List<Role> roles = new LinkedList<>();
            if (registerRequest.getRole().isEmpty()) {
                roles.add(roleServiceImpl.findByName(Roles.ROLE_USER).get());
            }
            for (String rol : registerRequest.getRole()) {
                if (rol.equals("user")) {
                    roles.add(roleServiceImpl.findByName(Roles.ROLE_USER).get());
                } else if (rol.equals("admin")) {
                    roles.add(roleServiceImpl.findByName(Roles.ROLE_ADMIN).get());
                }
            }
            return ResponseEntity.ok(userServiceImpl.save(new User(registerRequest.getUsername(), registerRequest.getEmail(), encoder.encode(registerRequest.getPassword()), roles)));
        }

    }
}
