package net.astercrono.pcsetup.dto.hardware;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class HardwareSettingDto {
	private Long id;
	@NotNull
	@Size(min = 3, max = 256)
	private String name;
	@Size(min = 0, max = 512)
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
