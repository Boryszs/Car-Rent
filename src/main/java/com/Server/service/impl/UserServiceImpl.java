package com.Server.service.impl;

import com.Server.dto.Request.EditUser;
import com.Server.dto.Request.RegisterRequest;
import com.Server.exception.ExceptionRequest;
import com.Server.model.Reservation;
import com.Server.model.Role;
import com.Server.model.Roles;
import com.Server.model.User;
import com.Server.repository.ReservationRepository;
import com.Server.repository.RoleRepository;
import com.Server.repository.UserRepository;
import com.Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ReservationRepository reservationRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ReservationRepository reservationRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<Reservation> getReservationUser(Long id) throws ExceptionRequest {
        if (!reservationRepository.existsByIdrent(id)) {
            throw new ExceptionRequest("Reservation not exist!!!");
        } else {
            if (!userRepository.existsById(id)) {
                throw new ExceptionRequest("User not Exist");
            } else {
                User user = userRepository.findById(id).get();
                List<Reservation> reservations = user.getReservations();
                return reservations;
            }
        }
    }

    @Override
    public void deleteUser(Long id) throws ExceptionRequest {
        if(!userRepository.existsById(id)){
            throw new ExceptionRequest("User not Exist");
        }else{
        User user = userRepository.findById(id).get();
        user.getReservations().forEach(reservation -> reservationRepository.delete(reservation));
        user.getReservations().removeAll(user.getReservations());
        userRepository.save(user);
        userRepository.deleteById(id);
        }
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(EditUser editUser) throws ExceptionRequest {
        System.out.println(editUser.getId());
        if(!userRepository.existsById(editUser.getId())){
            throw new ExceptionRequest("User not Exist");
        }else {
            if(userRepository.existsByUsername(editUser.getUsername())){
                throw new ExceptionRequest("User of Username is Exist");
            }
                User user = userRepository.findById(editUser.getId()).get();
                user.setUsername(editUser.getUsername());
                user.setEmail(editUser.getEmail());
                user.setPassword(encoder.encode(editUser.getPassword()));
                List<Role> roles = new LinkedList<>();
                if (editUser.getRole().isEmpty()) {
                    roles.add(roleRepository.findByName(Roles.ROLE_USER).get());
                }
                for (String rol : editUser.getRole()) {
                    if (rol.equals("user")) {
                        roles.add(roleRepository.findByName(Roles.ROLE_USER).get());
                    } else if (rol.equals("admin")) {
                        roles.add(roleRepository.findByName(Roles.ROLE_ADMIN).get());
                    }
                }
                user.setRoles(roles);
                return userRepository.save(user);
        }
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) throws ExceptionRequest {
        if (!userRepository.existsById(id)) {
            throw new ExceptionRequest("User not Exist");
        } else {
            return Optional.of(userRepository.findById(id).get());
        }
    }

    @Override
    public User save(RegisterRequest registerRequest) throws ExceptionRequest {
        //System.out.print(userRepository.existsByUsername(registerRequest.getUsername()));
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ExceptionRequest("User Exist");
        } else {
            //sendMail.sendMail(registerRequest.getUsername(), "Thank you for register account.");
            List<Role> roles = new LinkedList<>();
            if (registerRequest.getRole().isEmpty()) {
                roles.add(roleRepository.findByName(Roles.ROLE_USER).get());
            }
            for (String rol : registerRequest.getRole()) {
                if (rol.equals("user")) {
                    roles.add(roleRepository.findByName(Roles.ROLE_USER).get());
                } else if (rol.equals("admin")) {
                    roles.add(roleRepository.findByName(Roles.ROLE_ADMIN).get());
                }
            }
            return userRepository.save(new User(registerRequest.getUsername(), registerRequest.getEmail(), encoder.encode(registerRequest.getPassword()), roles));
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByReservations_Idrent(Long id) {
        return userRepository.findByReservations_Idrent(id);
    }

    @Override
    public Boolean existsById(long id) {
        return userRepository.existsById(id);
    }
}
