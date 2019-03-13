package net.astercrono.pcsetup.dto.game;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TitleDto {
	@NotNull
	private Long id;
	@Size(min = 0, max = 512)
	private String name;

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
}
