package com.orange.service.impl;

import com.orange.component.JwtTokenUtil;
import com.orange.dao.UmsPermissionByAdminJoinRoleMapper;
import com.orange.exception.MallException;
import com.orange.exception.OperationException;
import com.orange.mapper.UmsAdminMapper;
import com.orange.model.UmsAdmin;
import com.orange.model.UmsAdminExample;
import com.orange.model.UmsPermission;
import com.orange.service.UmsAdminService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    UmsAdminMapper umsAdminMapper;
    @Autowired
    UmsPermissionByAdminJoinRoleMapper umsPermissionByAdminJoinRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> users = umsAdminMapper.selectByExample(example);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdmin) {
        UmsAdmin admin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdmin, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(1);
        //是否同名用户
        if (null != getAdminByUsername(admin.getUsername()))
            throw new MallException("该用户已存在");

        String encode = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encode);
        int insert = umsAdminMapper.insert(admin);
        if (insert < 1) throw new OperationException("注册失败");
        return admin;
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails) throw new MallException("用户不存在");

        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new MallException("密码错误");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public List<UmsPermission> getPermissionList(long adminId) {
        return umsPermissionByAdminJoinRoleMapper.getPermissionList(adminId);
    }
}
