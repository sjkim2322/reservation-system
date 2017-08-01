package kr.or.seongjin.reservation.Exception;

public class NotExistProductException extends ReservationException {

	private static final long serialVersionUID = 1L;

	@Override
	public ExceptionCode getExceptionCode() {
		return ExceptionCode.NOT_FOUND_PRODUCT;
	}

}
