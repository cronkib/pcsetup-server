package net.astercrono.pcsetup.util;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import net.astercrono.pcsetup.exception.authentication.InvalidPasswordException;
import net.astercrono.pcsetup.model.auth.AuthProperties;
import net.astercrono.pcsetup.model.auth.JwtKey;

@Component
public class CryptUtil {
	private static final String PEPPER_CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
	private static final String JWT_ISSUER = "PCSETUP";
	@Autowired
	private AuthProperties authProperties;

	public String securePassword(String plaintext) throws GeneralSecurityException {
		String ciphertext = BCrypt.hashpw(plaintext, BCrypt.gensalt());
		return pepper(ciphertext);
	}

	public boolean checkPassword(String plaintext, String ciphertext) throws InvalidPasswordException {
		try {
			String unpeppered = unpepper(ciphertext);
			return BCrypt.checkpw(plaintext, unpeppered);
		} catch (Exception ex) {
			throw new InvalidPasswordException(ex);
		}
	}

	public Cipher createCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance(PEPPER_CIPHER_TRANSFORMATION);
	}

	public String createAccessToken(Long userId, String username) {
		return createToken(userId, username, 120);
	}

	public String createRefreshToken(Long userId, String username) {
		return createToken(userId, username, 240);
	}

	public Optional<Long> verifyToken(String token) {
		try {
			JwtKey jwt = authProperties.getJwt();
			Algorithm algorithm = Algorithm.HMAC256(jwt.getSecretBytes());

			JWTVerifier verifier = JWT.require(algorithm).withIssuer(JWT_ISSUER).build();
			DecodedJWT decoded = verifier.verify(token);

			Long userId = Long.valueOf(decoded.getSubject());
			return Optional.of(userId);
		} catch (Exception ex) {
			return Optional.empty();
		}
	}

	private String createToken(Long userId, String username, int minutesToLive) {
		JwtKey jwt = authProperties.getJwt();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, minutesToLive);

		Date expirationTime = cal.getTime();

		Algorithm algorithm = Algorithm.HMAC256(jwt.getSecretBytes());
		return JWT.create().withSubject(userId.toString()).withClaim("username", username).withIssuer(JWT_ISSUER)
				.withExpiresAt(expirationTime).sign(algorithm);
	}

	private String pepper(String ciphertext) throws GeneralSecurityException {
		IvParameterSpec iv = authProperties.getPepper().getIvSpec();
		SecretKeySpec key = authProperties.getPepper().getKeySpec();

		Cipher cipher = createCipher();
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);

		byte[] bytes = cipher.doFinal(ciphertext.getBytes(Charset.forName("UTF-8")));
		return DatatypeConverter.printHexBinary(bytes);
	}
	
	private String unpepper(String ciphertext) throws GeneralSecurityException {
		byte[] cipherBytes = DatatypeConverter.parseHexBinary(ciphertext);
		
		IvParameterSpec iv = authProperties.getPepper().getIvSpec();
		SecretKeySpec key = authProperties.getPepper().getKeySpec();

		Cipher cipher = createCipher();
		cipher.init(Cipher.DECRYPT_MODE, key, iv);

		byte[] bytes = cipher.doFinal(cipherBytes);
		return new String(bytes, Charset.forName("UTF-8"));
	}
}
