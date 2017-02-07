package tran.tran.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tran.tran.intf.IDataBase;
import tran.tran.model.dto.DataBaseTableNameDto;
import tran.tran.model.DataBase;

@Controller
@RequestMapping(value="/api/transition/v1")
public class PreTransitionController {

	@Autowired
	private IDataBase iDataBase;
	
	@RequestMapping(value="/preTableNames", method = RequestMethod.GET)
	@ResponseBody
	public List<String> queryPreTableNames(DataBase customDataSource){
		return iDataBase.queryTableNames(customDataSource);
	}
	
	@RequestMapping(value="/preColumnNames", method = RequestMethod.POST)
	@ResponseBody
	public List<String> queryPreColumnNames(@RequestBody DataBaseTableNameDto dataBaseTableNameDto){
		return iDataBase.queryColumnNames(dataBaseTableNameDto);
	}
	@RequestMapping(value="/preDataBase",method=RequestMethod.POST)
	@ResponseBody
	public DataBase createPreDataBase(DataBase customDataBase){
		return iDataBase.createDataBase(customDataBase);
	}
	@RequestMapping(value="/preDataBase",method=RequestMethod.GET)
	@ResponseBody
	public String testPreDataBase(DataBase customDataBase){
		return iDataBase.testDataBase(customDataBase);
	}
}
