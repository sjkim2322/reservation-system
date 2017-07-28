package kr.or.seongjin.reservation.service;

import java.util.List;

import kr.or.seongjin.reservation.domain.Reservation;
import kr.or.seongjin.reservation.domain.ReservationCount;
import kr.or.seongjin.reservation.domain.ReservationDTO;

public interface ReservationService {

	public int setReservation(Reservation reservation);
	public List<ReservationCount> getReservationCountByUser(int userId) throws Exception;
	public List<ReservationDTO> getReservationByUser(int userId) throws Exception;
	public void modifyReservationTypeById(int id, int type);
}
