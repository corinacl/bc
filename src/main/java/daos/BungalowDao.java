package daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.Bungalow;

public interface BungalowDao extends JpaRepository<Bungalow, Integer>{
		
	@Query("SELECT a FROM Bungalow a WHERE a NOT IN "
			+ "(SELECT b.bungalow FROM Booking b WHERE ("
			+ "(?1 between b.arrivalDate and b.departureDate OR ?2 between b.arrivalDate and b.departureDate) OR"
			+ "(b.arrivalDate between ?1 and ?2 OR b.departureDate between ?1 and ?2)))")
	List<Bungalow> findAvailability(Calendar start, Calendar end);
	
	@Query("SELECT a FROM Bungalow a WHERE a NOT IN "
			+ "(SELECT b.bungalow FROM Booking b WHERE ("
			+ "((?1 between b.arrivalDate and b.departureDate OR ?2 between b.arrivalDate and b.departureDate) AND b.id != ?3) OR"
			+ "((b.arrivalDate between ?1 and ?2 OR b.departureDate between ?1 and ?2) AND b.id != ?3)))")
	List<Bungalow> findAvailabilityForModify(Calendar start, Calendar end, int idBooking);

}
