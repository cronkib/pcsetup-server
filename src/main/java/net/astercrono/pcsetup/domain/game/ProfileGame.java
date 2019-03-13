package net.astercrono.pcsetup.domain.game;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.astercrono.pcsetup.domain.Profile;

@Entity
@Table(name = "profile_game", schema = "game")
public class ProfileGame {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = true, updatable = false)
	private Profile profile;
	@OneToOne(optional = false)
	@JoinColumn(name = "title_id")
	private Title title;
	@Column(name = "average_fps")
	private Integer averageFps;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profileGame", orphanRemoval = true)
	private List<GameSetting> settings;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Integer getAverageFps() {
		return averageFps;
	}

	public void setAverageFps(Integer averageFps) {
		this.averageFps = averageFps;
	}

	public List<GameSetting> getSettings() {
		return settings;
	}

	public void setSettings(List<GameSetting> settings) {
		for (GameSetting s : settings) {
			s.setProfileGame(this);
		}
		this.settings = settings;
	}
	
	public void addSetting(GameSetting setting) {
		setting.setProfileGame(this);
		settings.add(setting);
	}
}
