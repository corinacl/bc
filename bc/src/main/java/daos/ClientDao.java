package daos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import entities.Client;

public interface ClientDao extends JpaRepository<Client, Integer>{
	
	public Client findByDni(String dni);
	
	public Client findById(int id);
	
	public Client findByPhone(String phone);
	
	public Client findByEmail(String email);
	
	public List<Client> findByName(String name);
	
	public List<Client> findAllByDni(String dni);
	
	public List<Client> findAllByPhone(String phone);
	
	public List<Client> findAllByEmail(String email);
	
	public Page<Client> findAll(Pageable pageable);
	
	public List<Client> findAllByOrderByNameAsc();
}
