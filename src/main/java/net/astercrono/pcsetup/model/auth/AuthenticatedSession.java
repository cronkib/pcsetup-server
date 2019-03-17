package net.astercrono.pcsetup.model.auth;

import javax.servlet.http.HttpServletRequest;

public class AuthenticatedSession {
	public static final String REQUEST_ATTRIBUTE = "AuthenticatedSession";
	
	private Long userId;
	
	public static AuthenticatedSession getSession(HttpServletRequest request) {
		return (AuthenticatedSession) request.getAttribute(REQUEST_ATTRIBUTE);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
