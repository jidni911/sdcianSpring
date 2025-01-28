package com.jidnivai.sdcian.sdcian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.Messege;

@Repository
public interface MessegeRepository extends JpaRepository<Messege, Long> {

}
