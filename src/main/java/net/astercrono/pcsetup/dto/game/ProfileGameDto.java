package net.astercrono.pcsetup.dto.game;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProfileGameDto {
	private Long id;
	@NotNull
	private TitleDto title;
	@Max(100000)
	@Min(0)
	private Integer averageFps;
	private List<GameSettingDto> settings;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TitleDto getTitle() {
		return title;
	}

	public void setTitle(TitleDto title) {
		this.title = title;
	}

	public Integer getAverageFps() {
		return averageFps;
	}

	public void setAverageFps(Integer averageFps) {
		this.averageFps = averageFps;
	}

	public List<GameSettingDto> getSettings() {
		return settings;
	}

	public void setSettings(List<GameSettingDto> settings) {
		this.settings = settings;
	}
}
