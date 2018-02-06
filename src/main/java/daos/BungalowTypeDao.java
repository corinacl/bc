package daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.BungalowType;

public interface BungalowTypeDao extends JpaRepository<BungalowType, Integer>{
	
	List<BungalowType> findAllByOrderByTypeAsc();
		
}
