package net.astercrono.pcsetup.interceptor;

import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import net.astercrono.pcsetup.model.auth.AuthenticatedSession;
import net.astercrono.pcsetup.util.CryptUtil;

@Component
public class UserAuthenticationInterceptor implements HandlerInterceptor {
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String TYPE_BEARER = "Bearer";
	
	@Autowired
	private CryptUtil cryptUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			Optional<String> jwt = extractJwtToken(request);
			
			if (jwt.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
				return false;
			}
			
			Optional<Long> userId = cryptUtil.verifyToken(jwt.get());
			
			if (userId.isEmpty()) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}
			
			AuthenticatedSession session = new AuthenticatedSession();
			session.setUserId(userId.get());
			request.setAttribute(AuthenticatedSession.REQUEST_ATTRIBUTE, session);
			
			return true;
		} catch (Exception ex) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return false;
		}
	}

	private Optional<String> extractJwtToken(HttpServletRequest request) {
		String authHeader = request.getHeader(AUTHORIZATION_HEADER);
		
		if (authHeader == null || authHeader.isEmpty()) {
			return Optional.empty();
		}
		
		String[] authHeaderSplit = authHeader.split(" ");
		
		if (authHeaderSplit == null || authHeaderSplit.length != 2) {
			return Optional.empty();
		}

		String authType = authHeaderSplit[0];
		String authKey = authHeaderSplit[1];
		
		if (!Objects.equals(authType, TYPE_BEARER)) {
			return Optional.empty();
		}
		
		return Optional.of(authKey);
	}
}
