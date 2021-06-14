package com.marlowe.music.service.impl;

import com.marlowe.music.entity.Role;
import com.marlowe.music.mapper.RoleMapper;
import com.marlowe.music.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
