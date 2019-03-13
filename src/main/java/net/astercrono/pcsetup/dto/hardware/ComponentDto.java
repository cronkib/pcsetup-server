package net.astercrono.pcsetup.dto.hardware;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ComponentDto {
	@NotNull
	private Long id;
	@NotEmpty
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
