package kr.or.seongjin.reservation.Exception;

public class ExceptionHandler {
	
	public static String createErrorMessage(String errorName) {
		
		return new String(""
				+ "{"
				+ "error :"+errorName
				+ "}");
	}
	

}
