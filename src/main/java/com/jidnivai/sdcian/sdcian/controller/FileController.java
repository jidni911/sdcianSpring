package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.interfaces.FileServiceInt;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileServiceInt fileServiceInt;
    //TODO
}
