package com.Server.controller;

import com.Server.dto.Request.LoginRequest;
import com.Server.dto.Request.RegisterRequest;
import com.Server.dto.Response.JwtResponse;
import com.Server.dto.Response.MessageResponse;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Localization;
import com.Server.security.JwtUtils;
import com.Server.service.CarService;
import com.Server.service.LocalizationService;
import com.Server.service.RoleService;
import com.Server.service.UserService;
import com.Server.service.impl.SendMailImpl;
import com.Server.service.impl.UserDetailsimpl;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 *   AuthenticationController is use to authentication and register user.
 *   @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 *   @version 1.0.
 *   @since 2020-12-29.
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthenticationController {

    /**Logger use to logger on server.*/
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    /**AuthenticationManager us to authentication*/
    private AuthenticationManager authenticationManager;
    /**JwtUtils generate token*/
    private JwtUtils jwtUtils;
    /**UserService operation on database table User*/
    private UserService userServiceImpl;
    /**LocationSercive operation on database table Localization*/
    private LocalizationService localizationServiceImpl;
    /**RoleService operation on database table Role*/
    private RoleService roleServiceImpl;
    /**PasswordEncoder encoder password*/
    private PasswordEncoder encoder;
    /**CarService operation on database table Car*/
    private CarService carServiceImpl;
    /**SendMail use to send mail*/
    private SendMailImpl sendMailImpl;


    /**Constructor*/
    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userServiceImpl, LocalizationService localizationServiceImpl, RoleService roleServiceImpl, PasswordEncoder encoder, CarService carServiceImpl, SendMailImpl sendMailImpl) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userServiceImpl = userServiceImpl;
        this.localizationServiceImpl = localizationServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.encoder = encoder;
        this.carServiceImpl = carServiceImpl;
        this.sendMailImpl = sendMailImpl;
    }

    //Logowanie
    /**
     * This method is use to login / authentication user.
     * This method use endpoint /authentication.
     * @param loginRequest Data witch login user (username,password).
     * @return JwtResponse witch token number and user data  Http.Status 200.
     */
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
    /**
     * This method is use to register user.
     * This method use endpoint /register.
     * @param registerRequest user data.
     * @return user data  Http.Status 200 or 400.
     * @exception  ExceptionRequest when user not exist
     */
    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            logger.info("------ User successfully registered------");
            return  new ResponseEntity<>(userServiceImpl.save(registerRequest),HttpStatus.OK);
        } catch (ExceptionRequest exceptionRequest) {
            logger.error("------ User Not Exist ------");
            return new ResponseEntity(new MessageResponse(exceptionRequest.getErr()), HttpStatus.BAD_REQUEST);
        }
    }
}
