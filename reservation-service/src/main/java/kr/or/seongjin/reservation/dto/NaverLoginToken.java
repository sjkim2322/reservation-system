package kr.or.seongjin.reservation.dto;

public class NaverLoginToken {

	private String resultCode;
    private String message;
    private NaverUser response;
    
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
	public NaverUser getResponse() {
		return response;
	}
	public void setResponse(NaverUser response) {
		this.response = response;
	}
    
    
}
