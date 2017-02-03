package tran.tran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tran.tran.intf.ITransition;
import tran.tran.model.Transition;

@Controller
@RequestMapping(value="/api/tran")
public class IndexController {

	@Autowired
	private ITransition iTransition;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String welcomeIndex(){
		return "index";
	}
	@RequestMapping(value="/query-transitions",method=RequestMethod.GET)
	@ResponseBody
	public List<Transition> queryTransitions(String username){
		return iTransition.queryTransitions(username);
	}
}
