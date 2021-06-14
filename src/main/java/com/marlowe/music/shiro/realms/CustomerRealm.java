package com.marlowe.music.shiro.realms;

import cn.hutool.core.collection.CollectionUtil;
import com.marlowe.music.entity.Permission;
import com.marlowe.music.entity.Role;
import com.marlowe.music.entity.User;
import com.marlowe.music.service.impl.UserServiceImpl;
import com.marlowe.music.utils.SaltUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @program: music
 * @description: 自定义Realm
 * @author: Marlowe
 * @create: 2021-06-12 18:12
 **/
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();
        System.out.println("调用授权验证：" + primaryPrincipal);

        // 根据主身份信息获取角色和权限信息
        User user = userService.findRolesByUserName(primaryPrincipal);
        // 授权角色信息
        if (!CollectionUtil.isEmpty(user.getRoles())) {
            //权限信息对象info,用来存放查出的用户的所有的角色（Role）及权限（permission）
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                // 添加角色信息
                simpleAuthorizationInfo.addRole(role.getName());
                System.out.println(role.getName()+"================================");

                // 添加权限信息
//                List<Permission> perms = userService.findPermsByRoleId(role.getId());
//                if (!CollectionUtil.isEmpty(perms)) {
//                    perms.forEach(perm -> {
//                        simpleAuthorizationInfo.addStringPermission(perm.getName());
//                    });
//                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 根据身份信息获得principal
        String principal = (String) token.getPrincipal();

        User user = userService.findByUserName(principal);

        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}
