package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.marlowe.music.entity.Permission;
import com.marlowe.music.entity.Role;
import com.marlowe.music.entity.User;
import com.marlowe.music.mapper.UserMapper;
import com.marlowe.music.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.marlowe.music.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public boolean register(User user) {

        // 1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        // 2. 将随机盐保存到数据库
        user.setSalt(salt);
        // 3. 明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        return userMapper.insert(user) > 0;
    }

    /**
     * 通过用户名获取所有权限
     *
     * @param username
     * @return
     */
    @Override
    public User findRolesByUserName(String username) {
        return userMapper.findRolesByUserName(username);
    }

    /**
     * 根据角色id查询权限集合
     *
     * @param id
     * @return
     */
    @Override
    public List<Permission> findPermsByRoleId(int id) {
        return userMapper.findPermsByRoleId(id);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        // 1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        // 2. 将随机盐保存到数据库
        user.setSalt(salt);
        // 3. 明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(md5Hash.toHex());
        return userMapper.insert(user) > 0;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserById(int id) {
        return userMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteUserByIds(List<Integer> ids) {
        return userMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 根据id更新用户信息，包括角色信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUserMsg(User user) {
        return userMapper.updateById(user) > 0;
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
