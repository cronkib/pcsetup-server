package net.astercrono.pcsetup.dto.game;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GameSettingDto {
	private Long id;
	@NotNull
	private CategoryDto category;
	@NotEmpty
	private String setting;
	@NotEmpty
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
