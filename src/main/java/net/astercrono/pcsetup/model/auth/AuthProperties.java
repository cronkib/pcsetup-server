package net.astercrono.pcsetup.model.auth;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AuthProperties {
	private static final String KEY_ALGORITHM = "AES";
	private String key;
	private String iv;
	
	public AuthProperties() {
	}
	
	public AuthProperties(String key, String iv) {
		this.key = key;
		this.iv = iv;
	}
	
	public IvParameterSpec getIvSpec() {
		return new IvParameterSpec(getIvBytes());
	}
	
	public SecretKeySpec getKeySpec() {
		return new SecretKeySpec(getKeyBytes(), KEY_ALGORITHM);
	}
	
	public byte[] getKeyBytes() {
		return DatatypeConverter.parseHexBinary(getKey());
	}
	
	public byte[] getIvBytes() {
		return DatatypeConverter.parseHexBinary(getIv());
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}
}
