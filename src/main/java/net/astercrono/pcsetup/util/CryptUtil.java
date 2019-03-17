package net.astercrono.pcsetup.util;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import net.astercrono.pcsetup.model.auth.AuthProperties;

@Component
public class CryptUtil {
	private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
	@Autowired
	private AuthProperties authProperties;

	public String securePassword(String plaintext) throws GeneralSecurityException {
		String ciphertext = BCrypt.hashpw(plaintext, BCrypt.gensalt());
		return pepper(ciphertext, Cipher.ENCRYPT_MODE);
	}
	
	public boolean checkPassword(String plaintext, String ciphertext) throws GeneralSecurityException {
		String unpeppered = pepper(ciphertext, Cipher.DECRYPT_MODE);
		return BCrypt.checkpw(plaintext, unpeppered);
	}

	public Cipher createCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance(CIPHER_TRANSFORMATION);
	}

	private String pepper(String ciphertext, int mode) throws GeneralSecurityException {
		IvParameterSpec iv = authProperties.getIvSpec();
		SecretKeySpec key = authProperties.getKeySpec();

		Cipher cipher = createCipher();
		cipher.init(mode, key, iv);

		byte[] bytes = cipher.doFinal(ciphertext.getBytes());
		return DatatypeConverter.printHexBinary(bytes);
	}
}
