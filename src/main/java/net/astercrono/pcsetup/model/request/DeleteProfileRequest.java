package net.astercrono.pcsetup.model.request;

import javax.validation.constraints.NotNull;

public class DeleteProfileRequest {
	@NotNull(message = "id cannot be empty")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
