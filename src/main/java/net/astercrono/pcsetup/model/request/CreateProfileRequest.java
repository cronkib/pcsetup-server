package net.astercrono.pcsetup.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.astercrono.pcsetup.annotation.ValidEmail;
import net.astercrono.pcsetup.annotation.ValidPassword;
import net.astercrono.pcsetup.domain.Profile;

public class CreateProfileRequest {
	@NotNull
	@Size(min = 8, max = 64)
	private String username;
	@NotNull
	@Size(min = 2, max = 128)
	private String fullname;
	@Size(min = 0, max = 256)
	private String bio;
	@ValidPassword
	private String password;
	@NotNull
	@Size(min = 10, max = 256)
	@ValidEmail
	private String email;
	
	public Profile mapToProfile() {
		Profile profile = new Profile();
		profile.setUsername(getUsername());
		profile.setFullname(getFullname());
		profile.setBio(getBio());
		profile.setEmail(getEmail());
		return profile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
