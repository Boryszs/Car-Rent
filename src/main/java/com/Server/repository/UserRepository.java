package com.Server.repository;

import com.Server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    void deleteById(Long id);

    User findByReservations_Idrent(Long id);

    Boolean existsById(long id);

    Optional<User> findById(Long id);


}
