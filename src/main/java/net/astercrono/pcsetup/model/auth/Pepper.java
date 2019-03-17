package net.astercrono.pcsetup.model.auth;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class Pepper {
	private static final String KEY_ALGORITHM = "AES";

	private String key;
	private String iv;
	
	public Pepper(String key, String iv) {
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

	public String getIv() {
		return iv;
	}
}
