package tran.tran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tran.tran.intf.ITransition;
import tran.tran.model.Transition;
import tran.tran.model.dto.TransitionDataDto;

@Controller
@RequestMapping(value="/api/transition/v1")
public class TransitionController {
	@Autowired
	private ITransition iTransition;
	
	@RequestMapping(value="/transation", method = RequestMethod.PUT)
	@ResponseBody
	public String updateTransition(@RequestBody TransitionDataDto transitionDataDto){
		return iTransition.updateTransition(transitionDataDto);
	}
	@RequestMapping(value="/transition",method=RequestMethod.POST)
	@ResponseBody
	public String createTransition(@RequestBody TransitionDataDto transitionDataDto){
		return iTransition.createTransition(transitionDataDto);
	}
	@RequestMapping(value="/transitions",method=RequestMethod.GET)
	@ResponseBody
	public List<Transition> queryTransitions(String userId){
		return iTransition.queryTransitions(userId);
	}
}
