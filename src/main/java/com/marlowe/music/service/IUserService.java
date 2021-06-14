package com.marlowe.music.service;

import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.Permission;
import com.marlowe.music.entity.Role;
import com.marlowe.music.entity.Song;
import com.marlowe.music.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-13
 */
public interface IUserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param user
     */
    boolean register(User user);

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 通过用户名获取所有角色
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
     * 添加用户
     *
     * @param user
     * @return
     */
    boolean addUser(User user);


    /**
     * 分页查询所有用户
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<User> allUsers(int pageNo, int pageSize);


    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUserById(int id);

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    boolean deleteUserByIds(List<Integer> ids);

    /**
     * 根据id更新用户信息，包括角色信息
     *
     * @param user
     * @return
     */
    boolean updateUserMsg(User user);


    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    User findUserById(int id);

}
