package net.astercrono.pcsetup.model;

public class EmptyPCSResponseModel extends PCSResponseModel<Object> {
	public EmptyPCSResponseModel() {
		super();
	}

	public EmptyPCSResponseModel(PCSResponseStatus status) {
		super(status);
	}
}
