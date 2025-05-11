package com.jidnivai.sdcian.sdcian.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.dto.MemoDto;
import com.jidnivai.sdcian.sdcian.dto.NewMemoDto;
import com.jidnivai.sdcian.sdcian.entity.shop.Memo;
import com.jidnivai.sdcian.sdcian.interfaces.MemoServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/memo")
public class MemoController {

    @Autowired
    private MemoServiceInt memoServiceInt;

    @GetMapping
    public Page<Memo> getAllMemos(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return memoServiceInt.getAllMemos(page, size, userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public MemoDto getMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return memoServiceInt.getMemo(id, userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping
    public MemoDto addMemo(@RequestBody NewMemoDto newMemoDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return memoServiceInt.addMemo(newMemoDto, userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public MemoDto updateMemo(@PathVariable Long id, @RequestBody MemoDto memoDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return memoServiceInt.updateMemo(id, memoDto, userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            memoServiceInt.deleteMemo(id, userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
        }
    }

    @GetMapping("/search/{name}")
    public Page<Memo> searchMemos(@PathVariable String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return memoServiceInt.searchMemos(name, page, size, userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
            return null;
        }
    }

    
    @GetMapping("/nextMemoNumber")
    public Integer nextMemoNumber(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return memoServiceInt.nextMemoNumber(userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/execute")
    public MemoDto execute(@RequestBody NewMemoDto newMemoDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            return memoServiceInt.execute(newMemoDto, userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/print")
    public ResponseEntity<byte[]> print(@RequestBody NewMemoDto newMemoDto, @AuthenticationPrincipal UserDetailsImpl userDetails)  throws JRException, IOException {
        try {
            return memoServiceInt.print(newMemoDto, userDetails.getUser());
        } catch (Exception e) {
            System.out.println("MemoController: " + e.getMessage());
            return null;
        }
    }


}

