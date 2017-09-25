package com.zealot.mytest.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zealot.mytest.po.User;
import com.zealot.mytest.po.UserRole;
import com.zealot.mytest.security.entity.SysUserEntity;
import com.zealot.mytest.service.UserRoleService;
import com.zealot.mytest.service.UserService;

@Service("userDetailsService")
public class CustomUserService implements UserDetailsService {

	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="userRoleService")
	private UserRoleService userRoleService;
	
	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		User user = userService.findUserByLoginName(name);
		if( user == null ){
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", name));
        }
		
		SysUserEntity sysUser = new SysUserEntity();
		sysUser.setId((long)user.getUid());
		sysUser.setPassword(user.getPwd());
		sysUser.setUsername(user.getUname());
		
		List<UserRole> userRoleList = userRoleService.findByUser(user.getUid());
		
		sysUser.setRoles(userRoleList);
		return sysUser;
	}

}
