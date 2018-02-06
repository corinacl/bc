package api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.IncompletePlanningFormException;

import org.springframework.web.bind.annotation.RequestMethod;

import controllers.PlanningController;
import wrappers.PlanningWrapper;

@RestController
@RequestMapping(Uris.PLANNING)
public class PlanningResource {

	private PlanningController planningController;
	
	@Autowired
	public void setPlanningController(PlanningController planningController){
		this.planningController = planningController;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public Map<Integer, List<Integer>> showPlanning(@RequestBody PlanningWrapper planningWrapper) throws IncompletePlanningFormException {
		if(!planningController.validatePlanningWrapper(planningWrapper)){
			throw new IncompletePlanningFormException();
		}else{
			return planningController.getBookingsForPlanning(planningWrapper);
		}
    }
}
