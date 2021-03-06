package net.astercrono.pcsetup.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.astercrono.pcsetup.domain.game.ProfileGame;
import net.astercrono.pcsetup.domain.hardware.HardwareSetting;
import net.astercrono.pcsetup.domain.profile.UserAuthentication;

@Entity
@Table(name = "user", schema = "profile")
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String username;

	@Column
	private String fullname;

	@Column
	private String bio;

	@Column(name = "created_timestamp", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

	@Column(name = "modified_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTimestamp;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
	private List<HardwareSetting> hardwareSettings;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
	private List<ProfileGame> games;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
	private UserAuthentication authentication;
	
	@Column
	private String email;
	
	@Column
	private Boolean deleted;
	
	public Profile() {
		deleted = false;
	}

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

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Date getModifiedTimestamp() {
		return modifiedTimestamp;
	}

	public void setModifiedTimestamp(Date modifiedTimestamp) {
		this.modifiedTimestamp = modifiedTimestamp;
	}

	public List<HardwareSetting> getHardwareSettings() {
		return hardwareSettings;
	}

	public void setHardwareSettings(List<HardwareSetting> hardwareSettings) {
		if (hardwareSettings == null) {
			return;
		}
		
		for (HardwareSetting s : hardwareSettings) {
			s.setProfile(this);
		}
		
		this.hardwareSettings = hardwareSettings;
	}

	public List<ProfileGame> getGames() {
		return games;
	}

	public void setGames(List<ProfileGame> games) {
		if (games == null) {
			return;
		}
		
		for (ProfileGame g : games) {
			g.setProfile(this);
		}
		
		this.games = games;
	}
	
	public void addHardwareSetting(HardwareSetting setting) {
		setting.setProfile(this);
		hardwareSettings.add(setting);
	}
	
	public void addGame(ProfileGame game) {
		game.setProfile(this);
		games.add(game);
	}

	public UserAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(UserAuthentication authentication) {
		if (authentication == null) {
			return;
		}
		authentication.setProfile(this);
		this.authentication = authentication;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
