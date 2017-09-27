package com.zealot.mytest.security.auth;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.zealot.mytest.security.entity.SysUserEntity;

//此为自定义验证方式
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Resource(name="userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = authentication.getName();
	    String password = (String) authentication.getCredentials();
	    SysUserEntity user = (SysUserEntity) userDetailsService.loadUserByUsername(username);
	    if(user == null){
	      throw new BadCredentialsException("Username not found.");
	    }
	 
	    //加密过程在这里体现
	    if (!password.equals(user.getPassword())) {
	      throw new BadCredentialsException("Wrong password.");
	    }
	 
	    Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
	    return new RememberMeAuthenticationToken(username, user, authorities);
	    //return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(RememberMeAuthenticationToken.class);
	}

}
