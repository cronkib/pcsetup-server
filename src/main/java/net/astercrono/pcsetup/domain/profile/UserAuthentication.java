package net.astercrono.pcsetup.domain.profile;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.astercrono.pcsetup.domain.Profile;

@Entity
@Table(name = "authentication", schema = "profile")
public class UserAuthentication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private String ciphertext;
	@Column(name = "last_login_timestamp")
	private Date lastLoginTimestamp;
	@Column(name = "expiration_timestamp")
	private Date expirationTimestamp;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = true, updatable = false)
	private Profile profile;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCiphertext() {
		return ciphertext;
	}

	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	public Date getLastLoginTimestamp() {
		return lastLoginTimestamp;
	}

	public void setLastLoginTimestamp(Date lastLoginTimestamp) {
		this.lastLoginTimestamp = lastLoginTimestamp;
	}

	public Date getExpirationTimestamp() {
		return expirationTimestamp;
	}

	public void setExpirationTimestamp(Date expirationTimestamp) {
		this.expirationTimestamp = expirationTimestamp;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public boolean isActive() {
		return !isExpired() && !profile.getDeleted();
	}
	
	public boolean isExpired() {
		Long currentTimeMs = new Date().getTime();
		Long expiredMs = expirationTimestamp.getTime();
		return expiredMs <= currentTimeMs;
	}
}
