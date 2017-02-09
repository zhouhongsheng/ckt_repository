package tran.tran.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/api/transition/v1")
public class IndexController {

	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String welcomeIndex(){
		return "index";
	}
}
