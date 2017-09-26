package com.zealot.mytest.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class ClearSessionRegistryLogoutHandler implements LogoutHandler {
	
	@Autowired
    SessionRegistry sessionRegistry;

	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		HttpSession session = request.getSession(false);
		if (session != null) {

			sessionRegistry.removeSessionInformation(session.getId());
		}
		
	}

}
