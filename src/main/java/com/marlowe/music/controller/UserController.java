package com.marlowe.music.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.commons.result.Result;
import com.marlowe.music.entity.User;
import com.marlowe.music.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author marlowe
 * @since 2021-06-13
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理控制器")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("register")
    public Result register(@RequestBody User user) {
        boolean register = userService.register(user);
        if (register) {
            return Result.ok("注册成功");
        } else {
            return Result.ok("注册失败");
        }
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return Result.ok("请输入用户名和密码！");
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(token);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return Result.ok("用户名不存在！");
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return Result.ok("账号或密码错误！");
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return Result.ok("没有权限");
        }
        return Result.ok("login success");
    }

    /**
     * 退出登录
     *
     * @return
     */
    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (subject.isAuthenticated()) {
            // session 会销毁，在SessionListener监听session销毁，清理权限缓存
            subject.logout();
        }
        return Result.ok("用户" + username + "退出登录");
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @ApiOperation("添加用户")
    @PostMapping("add")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public Result addUser(@RequestBody User user) {
        boolean addUser = userService.addUser(user);
        if (addUser) {
            return Result.ok("添加成功");
        } else {
            return Result.ok("添加失败");
        }
    }


    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation("更新用户信息")
    @PostMapping("update")
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    public Result updateUserMsg(@RequestBody User user) {
        boolean updateUserMsg = userService.updateUserMsg(user);
        if (updateUserMsg) {
            return Result.ok("更新成功");
        } else {
            return Result.ok("更新失败");
        }
    }


    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id删除用户")
    @GetMapping("delete/{id}")
    @RequiresRoles("root")
    public Result deleteById(@PathVariable int id) {
        boolean deleteUserById = userService.deleteUserById(id);
        if (deleteUserById) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }


    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @ApiOperation("批量删除用户")
    @PostMapping("deletes")
    @RequiresRoles("root")
    public Result deleteByIds(@RequestBody List<Integer> ids) {
        boolean deleteUserByIds = userService.deleteUserByIds(ids);
        if (deleteUserByIds) {
            return Result.ok("删除成功");
        } else {
            return Result.ok("删除失败");
        }
    }


    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id查找用户")
    @GetMapping("/detail/{id}")
    public Result<User> findUserById(@PathVariable int id) {
        User user = userService.findUserById(id);
        return Result.ok(user);
    }

    /**
     * 分页查找所有用户
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查找所有用户")
    @GetMapping("allUsers/{pageNo}/{pageSize}")
    public Result<List<User>> allUsers(@PathVariable int pageNo, @PathVariable int pageSize) {
        PageInfo<User> pageInfo = userService.allUsers(pageNo, pageSize);
        List<User> users = pageInfo.getList();
        return Result.ok(users);
    }


    /**
     * 获得用户总数
     *
     * @return
     */
    @ApiOperation(value = "获得用户总数")
    @GetMapping("count")
    public Result userCount() {
        int userCount = userService.userCount();
        return Result.ok(userCount);
    }


    /**
     * 获得男性和女性用户的个数
     *
     * @return
     */
    @ApiOperation("获得男性和女性用户的个数")
    @GetMapping("detail/sex")
    public Result userBySex() {
        JSONObject jsonObject = new JSONObject();
        // 查询男性的个数
        int countBySex1 = userService.userCountBySex(1);
        jsonObject.put("男性", countBySex1);
        // 查询女性的个数
        int countBySex2 = userService.userCountBySex(0);
        jsonObject.put("女性", countBySex2);
        return Result.ok(jsonObject);
    }
}
