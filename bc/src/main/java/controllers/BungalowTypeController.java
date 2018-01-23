package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.BungalowTypeDao;
import entities.BungalowType;
import wrappers.BungalowTypeWrapper;

@Controller
public class BungalowTypeController {

	private BungalowTypeDao bungalowTypeDao;
		
	@Autowired
	public void setBungalowTypeDao(BungalowTypeDao bungalowTypeDao) {
		this.bungalowTypeDao = bungalowTypeDao;
	}
	
	public List<BungalowType> getAll(){
		return bungalowTypeDao.findAllByOrderByTypeAsc();
	}

	public BungalowType getBungalowTypeById(int id){
		return bungalowTypeDao.findOne(id);
	}

	public void modifyBungalowType(BungalowTypeWrapper bungalowTypeWrapper) {
		BungalowType bungalowType = bungalowTypeDao.findOne(bungalowTypeWrapper.getId());
		
		bungalowType.setDecToJanPrice(bungalowTypeWrapper.getDecToJanPrice());
		bungalowType.setJanToAprPrice(bungalowTypeWrapper.getJanToAprPrice());
		bungalowType.setAprToJunPrice(bungalowTypeWrapper.getAprToJunPrice());
		bungalowType.setJulToOctPrice(bungalowTypeWrapper.getJulToOctPrice());
		bungalowType.setOctToDecPrice(bungalowTypeWrapper.getOctToDecPrice());
		
		this.bungalowTypeDao.save(bungalowType);
	}
}
