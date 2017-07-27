package kr.or.seongjin.reservation.dao;
import java.util.List;

import kr.or.seongjin.reservation.domain.Reservation;
import kr.or.seongjin.reservation.domain.ReservationCount;
import kr.or.seongjin.reservation.domain.ReservationDTO;

public interface ReservationDAO {
	
	public int insertReservation(Reservation reservation);

	public List<ReservationCount> selectReservationCountByUser(int userId);
	public List<ReservationDTO> selectReservationByUser(int userId);

}
