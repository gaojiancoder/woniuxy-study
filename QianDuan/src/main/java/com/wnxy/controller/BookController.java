package com.wnxy.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wnxy.entity.Book;
import com.wnxy.service.BookService;
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
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("topN")
    public List<Book> topN(Integer n) {
        Page<Book> topNBook = bookService.getTopNBook(1, n);
        List<Book> records = topNBook.getRecords();
        return records;
    }

}

