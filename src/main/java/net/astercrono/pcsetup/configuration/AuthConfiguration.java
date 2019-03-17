package net.astercrono.pcsetup.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.astercrono.pcsetup.model.auth.AuthProperties;

@Configuration
public class AuthConfiguration {
	private String authPropertiesPathname = System.getProperty("user.dir") + "/data/auth.properties";
	
	@Bean
	public AuthProperties authProperties() throws IOException {
		Properties props = new Properties();

		FileInputStream is = new FileInputStream(authPropertiesPathname);
		props.load(is);
		
		String key = props.getProperty("pepper.key");
		String iv = props.getProperty("pepper.iv");
		
		return new AuthProperties(key, iv);
	}
}
