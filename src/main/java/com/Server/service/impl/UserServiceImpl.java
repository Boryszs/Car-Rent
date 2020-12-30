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

/**
 * Class Service implements interface RoleService.
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 1.0
 * @since 2020-12-29.
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {
    /**userRepository*/
    private UserRepository userRepository;
    /**reservationRepository*/
    private ReservationRepository reservationRepository;
    /**roleRepository*/
    private RoleRepository roleRepository;
    /**encoder*/
    private PasswordEncoder encoder;

    @Autowired
    /**Constructor*/
    public UserServiceImpl(UserRepository userRepository, ReservationRepository reservationRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    /**
     * Find the user on username.
     * @param username username on user.
     * @return data on user.
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Check whether user exist on username.
     * @param username username on user.
     * @return true or false.
     */
    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Return reservation user.
     * @param id id user.
     * @return List reservation.
     * @throws ExceptionRequest when id user is wrong.
     */
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

    /**
     * Delete user on id.
     * @param id id user.
     * @throws ExceptionRequest when id user is wrong.
     */
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

    /**
     * Update user.
     * @param user new user data.
     * @return return new data on user.
     */
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * Update user.
     * @param editUser new user data.
     * @return return new data on user.
     * @throws ExceptionRequest when request data user is wrong.
     */
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

    /**
     * Check whether user exist on email.
     * @param email email on user.
     * @return true or false.
     */
    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Find user on email.
     * @param email email user.
     * @return data user.
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Find user on id.
     * @param id is user.
     * @return user data
     * @throws ExceptionRequest when id is wrong
     */
    @Override
    public Optional<User> findById(Long id) throws ExceptionRequest {
        if (!userRepository.existsById(id)) {
            throw new ExceptionRequest("User not Exist");
        } else {
            return Optional.of(userRepository.findById(id).get());
        }
    }

    /**
     * Save new user data.
     * @param registerRequest user data.
     * @return new data user.
     * @throws ExceptionRequest when request data user register is wrong.
     */
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

    /**
     * Get all user.
     * @return List of all user.
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Find Reservation user on id.
     * @param id is reservation user.
     * @return User data with list reservation.
     */
    @Override
    public User findByReservations_Idrent(Long id) {
        return userRepository.findByReservations_Idrent(id);
    }

    /**
     * Check whether user on id exist.
     * @param id id user.
     * @return true or false.
     */
    @Override
    public Boolean existsById(long id) {
        return userRepository.existsById(id);
    }
}
