package com.Server.service.impl;

import com.Server.dto.Request.UserRequest;
import com.Server.dto.Response.ReservationResponse;
import com.Server.dto.Response.UserResponse;
import com.Server.entiy.Role;
import com.Server.entiy.Roles;
import com.Server.entiy.User;
import com.Server.exception.WrongDataException;
import com.Server.mapper.Mapper;
import com.Server.mapper.impl.ReservationMapper;
import com.Server.repository.ReservationRepository;
import com.Server.repository.RoleRepository;
import com.Server.repository.UserRepository;
import com.Server.service.SendMail;
import com.Server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class Service implements interface RoleService.
 *
 * @author Krystian Cwioro Kamil Bieniasz Damian Mierzynski.
 * @version 2.0.
 * @since 2020-04-27.
 */

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    /**
     * userRepository
     */
    private final UserRepository userRepository;
    /**
     * reservationRepository
     */
    private final ReservationRepository reservationRepository;
    /**
     * roleRepository
     */
    private final RoleRepository roleRepository;
    /**
     * encoder
     */
    private final PasswordEncoder encoder;
    /**
     * sendMail
     */
    private final SendMail sendMail;
    /**
     * reservation mapper
     */
    private final ReservationMapper reservationMapper;
    /**
     * user mapper
     */
    private final Mapper<User, UserResponse, UserRequest> userMapper;

    @Autowired
    /**Constructor*/
    public UserServiceImpl(UserRepository userRepository, ReservationRepository reservationRepository, RoleRepository roleRepository, PasswordEncoder encoder, SendMail sendMail, ReservationMapper reservationMapper, Mapper<User, UserResponse, UserRequest>  userMapper) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.sendMail = sendMail;
        this.reservationMapper = reservationMapper;
        this.userMapper = userMapper;
    }

    /**
     * Find the user on username.
     *
     * @param username username on user.
     * @return data on user.
     */
    @Override
    public Optional<User> findByUsername(String username) {
        log.info("---- FIND USER ON USERNAME "+username+" ----");
        return userRepository.findByUsername(username);
    }

    /**
     * Return reservation user.
     *
     * @param id id user.
     * @return List reservation.
     * @throws WrongDataException when id user is wrong.
     */
    @Override
    public List<ReservationResponse> getReservationUser(Long id) throws WrongDataException {
        if (!reservationRepository.existsByIdrent(id)) {
            log.error("---- NOT EXIST RESERVATION ----");
            throw new WrongDataException("Reservation not exist!!!");
        } else {
            if (!userRepository.existsById(id)) {
                log.error("---- NOT EXIST USER ----");
                throw new WrongDataException("User not Exist");
            } else {
                log.info("---- GET USER RESERVATION ON ID "+id+" ----");
                List<ReservationResponse> reservations = userRepository.getReservationUser(id).stream().map(reservation -> reservationMapper.toDto(reservation)).collect(Collectors.toList());
                return reservations;
            }
        }
    }

    /**
     * Delete user on id.
     *
     * @param id id user.
     * @throws WrongDataException when id user is wrong.
     */
    @Override
    public void deleteUser(Long id) throws WrongDataException {
        if (!userRepository.existsById(id)) {
            log.error("---- NOT EXIST USER "+id+" ----");
            throw new WrongDataException("User not Exist");
        } else {
            log.info("---- DELETE USER ID "+id+"----");
            userRepository.deleteById(id);
        }
    }

    /**
     * Update user.
     *
     * @param user new user data.
     * @return return new data on user.
     */
    @Override
    public void update(User user) {
        log.info("---- USER UPDATE ----");
        userRepository.save(user);
    }

    /**
     * Update user.
     *
     * @param userRequest new user data.
     * @return return new data on user.
     * @throws WrongDataException when request data user is wrong.
     */
    @Override
    public void update(UserRequest userRequest,Long id) throws WrongDataException {
        if (!userRepository.existsById(id)) {
            log.error("---- USER NOT EXIST ----");
            throw new WrongDataException("User not Exist");
        } else {
            User user = userRepository.findById(id).get();
            user = userMapper.update(user,userRequest);
            List<Role> roles = new LinkedList<>();
            if (userRequest.getRole().isEmpty()) {
                roles.add(roleRepository.findByName(Roles.ROLE_USER).get());
            }
            for (String rol : userRequest.getRole()) {
                if (rol.equals("user")) {
                    roles.add(roleRepository.findByName(Roles.ROLE_USER).get());
                } else if (rol.equals("admin")) {
                    roles.add(roleRepository.findByName(Roles.ROLE_ADMIN).get());
                }
            }
            user.setRoles(roles);
            userRepository.save(user);
            log.info("---- USER UPDATE ----");
        }
    }

    /**
     * Find user on email.
     *
     * @param email email user.
     * @return data user.
     */
    @Override
    public UserResponse findByEmail(String email) {
        log.info("---- FIND USER ON EMAIL "+email+" ----");
        return userMapper.toDto(userRepository.findByEmail(email).get());
    }

    /**
     * Find user on id.
     *
     * @param id is user.
     * @return user data
     * @throws WrongDataException when id is wrong
     */
    @Override
    public UserResponse findById(Long id) throws WrongDataException {
        if (!userRepository.existsById(id)) {
            log.error("---- USER NOT EXIST ----");
            throw new WrongDataException("User not Exist");
        } else {
            log.info("---- USER ON ID "+id+" ----");
            return userMapper.toDto(userRepository.findById(id).get());
        }
    }

    /**
     * Save new user data.
     *
     * @param userRequest user data.
     * @return new data user.
     * @throws WrongDataException when request data user register is wrong.
     */
    @Override
    public void save(UserRequest userRequest) throws WrongDataException {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            log.error("---- USER EXIST ----");
            throw new WrongDataException("User Exist");
        } else {

            List<Role> roles = new LinkedList<>();
            if (userRequest.getRole().isEmpty()) {
                roles.add(roleRepository.findByName(Roles.ROLE_USER).get());
            }
            for (String rol : userRequest.getRole()) {
                if (rol.equals("user")) {
                    roles.add(roleRepository.findByName(Roles.ROLE_USER).get());
                } else if (rol.equals("admin")) {
                    roles.add(roleRepository.findByName(Roles.ROLE_ADMIN).get());
                }
            }
            User user = userMapper.toEntity(userRequest);
            user.setRoles(roles);
            userRepository.save(user);
            sendMail.sendMail(user.getId(), "Thank you for register account.");
            log.info("---- SAVE USER ----");
        }
    }

    /**
     * Get all user.
     *
     * @return List of all user.
     */
    @Override
    public List<UserResponse> findAll() {
        log.info("---- FIND ALL USER ----");
        return userRepository.findAll().stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
    }

    /**
     * Find Reservation user on id.
     *
     * @param id is reservation user.
     * @return User data with list reservation.
     */
    @Override
    public UserResponse findByReservationsIdRent(Long id) {
        log.info("---- FIND RESERVATION ON ID USER "+id+" ----");
        return userMapper.toDto(userRepository.findByReservations_Idrent(id));
    }

}
