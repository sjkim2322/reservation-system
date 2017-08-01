package kr.or.seongjin.reservation.Exception;

public enum ExceptionCode {

	NOT_FOUND_PRODUCT(100, "product is not exist");
	
	private int code;
	private String message;
	
	ExceptionCode(int code, String message){
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
