package net.astercrono.pcsetup.model;

public class PCSResponseModel<T> {
	private T data;
	private PCSResponseStatus status = PCSResponseStatus.GOOD;

	public PCSResponseModel() {
	}

	public PCSResponseModel(T data) {
		this.data = data;
	}

	public PCSResponseModel(T data, PCSResponseStatus status) {
		this.data = data;
		this.status = status;
	}

	public PCSResponseModel(PCSResponseStatus status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public PCSResponseStatus getStatus() {
		return status;
	}

	public void setStatus(PCSResponseStatus status) {
		this.status = status;
	}
}
