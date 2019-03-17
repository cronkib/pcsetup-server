package net.astercrono.pcsetup.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.astercrono.pcsetup.model.auth.AuthProperties;
import net.astercrono.pcsetup.model.auth.JwtKey;
import net.astercrono.pcsetup.model.auth.Pepper;

@Configuration
public class AuthConfiguration {
	private String authPropertiesPathname = System.getProperty("user.dir") + "/data/auth.properties";
	
	@Bean
	public AuthProperties authProperties() throws IOException {
		Properties props = new Properties();

		FileInputStream is = new FileInputStream(authPropertiesPathname);
		props.load(is);
		
		String pepperKey = props.getProperty("pepper.key");
		String pepperIv = props.getProperty("pepper.iv");
		Pepper pepper = new Pepper(pepperKey, pepperIv);
		
		String jwtSecret = props.getProperty("jwt.key");
		JwtKey jwt = new JwtKey(jwtSecret);
		
		return new AuthProperties(pepper, jwt);
	}
}
