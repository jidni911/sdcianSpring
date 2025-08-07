package com.jidnivai.sdcian.sdcian.dto.bkash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JerseyBuyerRepository extends JpaRepository<JerseyBuyer, String> {

    boolean existsByNumber(String number);

    JerseyBuyer findByNumber(String number);

    @Query("SELECT j.role FROM JerseyBuyer j WHERE j.number = ?1 AND j.password = ?2")
    String findRoleByNumberAndPassword(String number, String password);
    
}
