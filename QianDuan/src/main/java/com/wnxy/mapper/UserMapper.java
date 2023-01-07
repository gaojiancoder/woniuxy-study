package com.wnxy.mapper;

import com.wnxy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author woniumrwang
 * @since 2023-01-07 08:44:19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
