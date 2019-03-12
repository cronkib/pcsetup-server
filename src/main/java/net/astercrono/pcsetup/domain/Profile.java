package net.astercrono.pcsetup.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.astercrono.pcsetup.domain.hardware.HardwareSetting;

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
	@Column(name = "created_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;
	@Column(name = "modified_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTimestamp;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<HardwareSetting> hardwareSettings;

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
		this.hardwareSettings = hardwareSettings;
	}
}
