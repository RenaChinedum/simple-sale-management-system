package com.rena.simplemanagementsystem.Repository;

import com.rena.simplemanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    @Query(value = "SELECT * FROM users WHERE role = 'CLIENT'", nativeQuery = true)
    List<User> findAllClients();

    @Query(value = "SELECT * FROM users u1 WHERE u1.role = 'CLIENT' AND EXISTS (SELECT 1 FROM users u2 WHERE u2.role = 'CLIENT' AND u2.address LIKE CONCAT('%', u1.address, '%') AND u2.id <> u1.id)", nativeQuery = true)
    List<User> findClientsWithSimilarAddress();
}
