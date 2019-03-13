package net.astercrono.pcsetup.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import net.astercrono.pcsetup.dto.game.ProfileGameDto;
import net.astercrono.pcsetup.dto.hardware.HardwareSettingDto;

public class ProfileDto {
	private Long id;
	@NotEmpty
	private String username;
	@NotEmpty
	private String fullname;
	private String bio;
	private List<HardwareSettingDto> hardwareSettings;
	private List<ProfileGameDto> games;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<HardwareSettingDto> getHardwareSettings() {
		return hardwareSettings;
	}

	public void setHardwareSettings(List<HardwareSettingDto> hardwareSettings) {
		this.hardwareSettings = hardwareSettings;
	}

	public List<ProfileGameDto> getGames() {
		return games;
	}

	public void setGames(List<ProfileGameDto> games) {
		this.games = games;
	}
}
