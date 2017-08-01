package kr.or.seongjin.reservation.Exception;

public abstract class ReservationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public abstract ExceptionCode getExceptionCode();
	
}
