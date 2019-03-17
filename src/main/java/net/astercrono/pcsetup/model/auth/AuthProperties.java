package net.astercrono.pcsetup.model.auth;

public class AuthProperties {
	private Pepper pepper;
	private JwtKey jwt;

	public AuthProperties() {
	}

	public AuthProperties(Pepper pepper, JwtKey jwt) {
		this.pepper = pepper;
		this.jwt = jwt;
	}
	
	public Pepper getPepper() {
		return pepper;
	}
	
	public JwtKey getJwt() {
		return jwt;
	}
}
