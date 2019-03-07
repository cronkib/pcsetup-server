package net.astercrono.pcsetup.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="game_category", schema = "pcsetup")
public class GameCategory {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_category_id")
	private List<GameCategorySetting> settings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GameCategorySetting> getSettings() {
		return settings;
	}

	public void setSettings(List<GameCategorySetting> settings) {
		this.settings = settings;
	}
}
