package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import api.exceptions.DuplicatedEntryClientException;
import api.exceptions.IncompleteDataSearchException;
import daos.ClientDao;
import entities.Client;
import wrappers.ClientCreateWrapper;
import wrappers.ClientWrapper;


@Controller
public class ClientController {

	private ClientDao clientDao;
	
	@Autowired
	public void setClientDao(ClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	public List<Client> getAll(){
		return clientDao.findAllByOrderByNameAsc();
}
	
	public Page<Client> getAll(Pageable pageable){
		Page<Client> page = clientDao.findAll(pageable);
		List<Client> clients = new ArrayList<>();
	
		for (Client client : page.getContent()){
			clients.add(client);
		}	
		return new PageImpl<Client>(clients, pageable, page.getTotalElements());
	}
	
	public Client getClientById(long id){
		return clientDao.findOne((int) id);
	}

	public Client createClient (ClientCreateWrapper clientCreateWrapper) throws DuplicatedEntryClientException {
		if(clientCreateWrapper.getDni() != null && clientDao.findByDni(clientCreateWrapper.getDni()) != null){
			throw new DuplicatedEntryClientException ("Ya hay un cliente con ese DNI");
		}else if (clientCreateWrapper.getEmail() != null && clientDao.findByEmail(clientCreateWrapper.getEmail()) != null){
			throw new DuplicatedEntryClientException ("Ya hay un cliente con ese email");
		}else{
			Client client = new Client( 
					clientCreateWrapper.getName(), 
					clientCreateWrapper.getSurname(),
					clientCreateWrapper.getPhone(),				
					clientCreateWrapper.getDni(),
					clientCreateWrapper.getEmail());
			
			return clientDao.save(client);
		}
	}
	
	public void clientModify (ClientWrapper clientWrapper) throws DuplicatedEntryClientException {
		if(clientWrapper.getDni() != null && clientDao.findByDni(clientWrapper.getDni()) != null && clientDao.findByDni(clientWrapper.getDni()).getId() != clientWrapper.getId()){
			throw new DuplicatedEntryClientException ("Ya hay un cliente con ese DNI");
		}else if (clientWrapper.getEmail() != null && clientDao.findByEmail(clientWrapper.getEmail()) != null && clientDao.findByEmail(clientWrapper.getEmail()).getId() != clientWrapper.getId()){
			throw new DuplicatedEntryClientException ("Ya hay un cliente con ese email");
		}else{
			Client client = clientDao.findOne(clientWrapper.getId());
			
			client.setName(clientWrapper.getName());
			client.setSurname(clientWrapper.getSurname());
			client.setDni(clientWrapper.getDni());
			client.setPhone(clientWrapper.getPhone());
			client.setEmail(clientWrapper.getEmail());
			
			this.clientDao.save(client);
		}
	}
	
	public List<Client> searchClientBy(String searchBy, String searchData) throws IncompleteDataSearchException {
		if(searchBy == null || searchData == null){
			throw new IncompleteDataSearchException();
		}else{
			switch(searchBy){
				case "name":
					return clientDao.findByName(searchData);
				case "phone":
					return clientDao.findAllByPhone(searchData);
				case "dni":
					return clientDao.findAllByDni(searchData);
				case "email":
					return clientDao.findAllByEmail(searchData);
				default:
					return null;
			}
		}
	}
}
