package net.astercrono.pcsetup.dto.hardware;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class HardwareSettingDto {
	private Long id;
	@NotEmpty
	private String name;
	private String notes;
	@NotNull
	private ComponentDto component;

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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public ComponentDto getComponent() {
		return component;
	}

	public void setComponent(ComponentDto component) {
		this.component = component;
	}
}
