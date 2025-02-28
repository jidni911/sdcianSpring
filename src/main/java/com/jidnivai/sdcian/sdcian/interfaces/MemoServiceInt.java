package com.jidnivai.sdcian.sdcian.interfaces;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.jidnivai.sdcian.sdcian.dto.MemoDto;
import com.jidnivai.sdcian.sdcian.dto.NewMemoDto;
import com.jidnivai.sdcian.sdcian.entity.Memo;

import net.sf.jasperreports.engine.JRException;

public interface MemoServiceInt {

    Page<Memo> getAllMemos(int page, int size, Long id);

    MemoDto getMemo(Long id, Long id2);

    MemoDto addMemo(NewMemoDto newMemoDto, Long id);

    MemoDto updateMemo(Long id, MemoDto memoDto, Long id2);

    void deleteMemo(Long id, Long id2);

    Page<Memo> searchMemos(String name, int page, int size, Long id);

    Integer nextMemoNumber(Long id);

    MemoDto execute(NewMemoDto newMemoDto, Long id);

    ResponseEntity<byte[]> print(NewMemoDto newMemoDto, Long id) throws JRException, IOException;

}
