package com.jidnivai.sdcian.sdcian.interfaces;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.jidnivai.sdcian.sdcian.dto.MemoDto;
import com.jidnivai.sdcian.sdcian.dto.NewMemoDto;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.Memo;

import net.sf.jasperreports.engine.JRException;

public interface MemoServiceInt {

    Page<Memo> getAllMemos(int page, int size, User user);

    MemoDto getMemo(Long id, User user);

    MemoDto addMemo(NewMemoDto newMemoDto, User user);

    MemoDto updateMemo(Long id, MemoDto memoDto, User user);

    void deleteMemo(Long id, User user);

    Page<Memo> searchMemos(String name, int page, int size, User user);

    Integer nextMemoNumber(User user);

    MemoDto execute(NewMemoDto newMemoDto, User user);

    ResponseEntity<byte[]> print(NewMemoDto newMemoDto, User user) throws JRException, IOException;

}
