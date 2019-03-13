package net.astercrono.pcsetup.domain.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "setting", schema = "game")
public class GameSetting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column(name = "profile_game_id")
	private Long profileGameId;
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@Column
	private String setting;
	@Column 
	private String value;
}
