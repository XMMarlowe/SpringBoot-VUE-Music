package com.marlowe.music.mapper;

import com.marlowe.music.entity.Permission;
import com.marlowe.music.entity.Role;
import com.marlowe.music.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author marlowe
 * @since 2021-06-13
 */
public interface UserMapper extends BaseMapper<User> {

    User findByUserName(String username);

    /**
     * 根据用户名获取所有角色
     *
     * @param username
     * @return
     */
    User findRolesByUserName(String username);


    /**
     * 根据角色id查询权限集合
     *
     * @param id
     * @return
     */
    List<Permission> findPermsByRoleId(int id);


    /**
     * 根据用户id查找用户信息
     *
     * @param id
     * @return
     */
    User findUserById(int id);

    List<User> findAllUsers();

}
