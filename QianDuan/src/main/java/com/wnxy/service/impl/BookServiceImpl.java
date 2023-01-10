package com.wnxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wnxy.entity.Book;
import com.wnxy.mapper.BookMapper;
import com.wnxy.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-07 08:44:19
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Override
    public Page<Book> getTopNBook(Integer currentPage, Integer pageSize){
        Page<Book> bookPage = new Page<>(currentPage,pageSize);

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("buycount");

        Page<Book> bookPageResult = bookMapper.selectPage(bookPage, queryWrapper);

        return bookPageResult;
    }

}
