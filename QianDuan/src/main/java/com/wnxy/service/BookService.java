package com.wnxy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wnxy.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-07 08:44:19
 */
public interface BookService extends IService<Book> {


    Page<Book> getTopNBook(Integer currentPage, Integer pageSize);
}
