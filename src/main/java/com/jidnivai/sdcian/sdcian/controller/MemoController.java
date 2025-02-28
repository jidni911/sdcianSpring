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
import com.jidnivai.sdcian.sdcian.entity.Memo;
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
        return memoServiceInt.getAllMemos(page, size, userDetails.getId());
    }

    @GetMapping("/{id}")
    public MemoDto getMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoServiceInt.getMemo(id, userDetails.getId());
    }

    @PostMapping
    public MemoDto addMemo(@RequestBody NewMemoDto newMemoDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return memoServiceInt.addMemo(newMemoDto, userDetails.getId());
    }

    @PutMapping("/{id}")
    public MemoDto updateMemo(@PathVariable Long id, @RequestBody MemoDto memoDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoServiceInt.updateMemo(id, memoDto, userDetails.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        memoServiceInt.deleteMemo(id, userDetails.getId());
    }

    @GetMapping("/search/{name}")
    public Page<Memo> searchMemos(@PathVariable String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoServiceInt.searchMemos(name, page, size, userDetails.getId());
    }

    
    @GetMapping("/nextMemoNumber")
    public Integer nextMemoNumber(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoServiceInt.nextMemoNumber(userDetails.getId());
    }

    @PostMapping("/execute")
    public MemoDto execute(@RequestBody NewMemoDto newMemoDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return memoServiceInt.execute(newMemoDto, userDetails.getId());
    }

    @PostMapping("/print")
    public ResponseEntity<byte[]> print(@RequestBody NewMemoDto newMemoDto, @AuthenticationPrincipal UserDetailsImpl userDetails)  throws JRException, IOException {
        return memoServiceInt.print(newMemoDto, userDetails.getId());
    }


}
