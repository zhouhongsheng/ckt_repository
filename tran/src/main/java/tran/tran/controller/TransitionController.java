package tran.tran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tran.tran.intf.ITransition;
import tran.tran.model.dto.TransitionDataDto;

@Controller
@RequestMapping(value="/api/transition/v1")
public class TransitionController {
	@Autowired
	private ITransition tranIntf;
	
	@RequestMapping(value="/transation", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateTransition(@RequestBody TransitionDataDto transitionDataDto){
		return tranIntf.updateTransition(transitionDataDto);
	}
	@RequestMapping(value="/transition",method=RequestMethod.PUT)
	@ResponseBody
	public String createTransition(@RequestBody TransitionDataDto transitionDataDto){
		return tranIntf.createTransition(transitionDataDto);
	}
}
