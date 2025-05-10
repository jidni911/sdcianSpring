package com.jidnivai.sdcian.sdcian.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsernameIgnoreCase(String userName);

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    // Fetch all products with pagination and sorting
    Page<User> findAll(Pageable pageable);

    @Query( value = "SELECT u.username FROM users u", nativeQuery = true)
    List<String> findAllUsernames();

    Page<User> findByFullNameContainingOrUsernameContainingOrEmailContaining(String query, String query2, String query3,
            PageRequest of);

    boolean existsByUsernameIgnoreCaseAndEmailVerifiedIsTrue(String username);

    boolean existsByUsernameIgnoreCaseAndEmailVerifiedIsFalse(String username);

    boolean existsByEmailIgnoreCaseAndEmailVerifiedIsTrue(String email);

   

}
