package daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Booking;
import entities.Bungalow;
import entities.Client;

public interface BookingDao extends JpaRepository<Booking, Integer>{

	@Query("SELECT b FROM Booking b WHERE ("
			+ "(?1 between b.arrivalDate and b.departureDate OR ?2 between b.arrivalDate and b.departureDate) OR " 
			+ "(b.arrivalDate between ?1 and ?2 OR b.departureDate between ?1 and ?2))")
	List<Booking> findByDatesBetween(Calendar start, Calendar end);
	
	@Query("SELECT b FROM Booking b WHERE ("
			+ "((?1 between b.arrivalDate and b.departureDate OR ?2 between b.arrivalDate and b.departureDate) AND b.bungalow = ?3) OR " 
			+ "((b.arrivalDate between ?1 and ?2 OR b.departureDate between ?1 and ?2) AND b.bungalow = ?3)) ORDER BY b.arrivalDate ASC")
	List<Booking> findByBookingsByBungalowBetween(Calendar start, Calendar end, Bungalow bungalow);
	
	@Query("SELECT b FROM Booking b WHERE ("
			+ "(?1 between b.arrivalDate and b.departureDate OR ?2 between b.arrivalDate and b.departureDate) OR " 
			+ "(b.arrivalDate between ?1 and ?2 OR b.departureDate between ?1 and ?2)) ORDER BY b.arrivalDate ASC")
	List<Booking> findByBookingsBetween(Calendar start, Calendar end);
	
	List<Booking> findByClient(Client client);
	
	public Page<Booking> findAll(Pageable pageable);

}
