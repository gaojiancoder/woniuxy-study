package com.wnxy.controller;


import com.wnxy.entity.Booktype;
import com.wnxy.service.BooktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-07 08:44:19
 */
@RestController
@RequestMapping("/booktype")
public class BooktypeController {

    @Autowired
    BooktypeService booktypeService;

    @RequestMapping("/all")
    public List<Booktype> all() {

        List<Booktype> list = booktypeService.list();
        return list;
    }

}

