package com.jidnivai.sdcian.sdcian.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

        Page<Memo> findBySeller(User user, PageRequest of);

        Page<Memo> findByBuyer(User user, PageRequest of);

        Page<Memo> findByMemoNumberContainingOrBuyerNameContainingOrBuyerAddressContainingOrBuyerPhoneNumberContainingOrBuyerEmailContaining(
                        String name, String name2, String name3, String name4, String name5, PageRequest of);

        Page<Memo> findBySellerIdAndMemoNumberContainingOrBuyerNameContainingOrBuyerAddressContainingOrBuyerPhoneNumberContainingOrBuyerEmailContaining(
                        User user, String name, String name2, String name3, String name4, String name5, PageRequest of);

        @Query("SELECT MAX(m.memoNumber) FROM Memo m WHERE m.seller = :user")
        Optional<Integer> findMaxMemoNumberBySeller(@Param("user") User user);

}