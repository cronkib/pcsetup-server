package net.astercrono.pcsetup.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RefreshTokenRequest {
	@NotNull
	@NotEmpty
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
