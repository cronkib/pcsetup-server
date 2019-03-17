package net.astercrono.pcsetup.model.auth;

import javax.xml.bind.DatatypeConverter;

public class JwtKey {
	private String secret;

	public JwtKey(String secret) {
		this.secret = secret;
	}

	public byte[] getSecretBytes() {
		return DatatypeConverter.parseHexBinary(getSecret());
	}

	public String getSecret() {
		return secret;
	}
}
