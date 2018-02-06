package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;

import controllers.BungalowTypeController;
import entities.BungalowType;
import wrappers.BungalowTypeWrapper;

@RestController
@RequestMapping(Uris.TYPE)
public class BungalowTypeResource {

	private BungalowTypeController bungalowTypeController;
	
	@Autowired
	public void setBungalowTypeController(BungalowTypeController bungalowTypeController){
		this.bungalowTypeController = bungalowTypeController;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<BungalowType> listBungalows(){
		return bungalowTypeController.getAll();
	}
	
	@RequestMapping(value = Uris.ID, method = RequestMethod.GET)  
    public BungalowType getBungalowTypeById(@PathVariable(value = "id") int id){
        return bungalowTypeController.getBungalowTypeById(id);
    }
	
	@RequestMapping(method = RequestMethod.PUT)
    public void modifyBungalowType (@RequestBody BungalowTypeWrapper bungalowTypeWrapper) {
    	this.bungalowTypeController.modifyBungalowType(bungalowTypeWrapper);
    }

}
