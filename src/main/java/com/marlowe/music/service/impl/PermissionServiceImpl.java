package com.marlowe.music.service.impl;

import com.marlowe.music.entity.Permission;
import com.marlowe.music.mapper.PermissionMapper;
import com.marlowe.music.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-06-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
