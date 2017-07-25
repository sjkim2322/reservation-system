package kr.or.seongjin.reservation.dto;

public class NaverLoginToken {

	private String resultCode;
    private String message;
    private User response;
    
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getResponse() {
		return response;
	}
	public void setResponse(User response) {
		this.response = response;
	}
    
    
}
