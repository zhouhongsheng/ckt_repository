package tran.tran.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tran.tran.intf.IDataBase;
import tran.tran.model.DataBase;
import tran.tran.model.dto.DataBaseDto;

@Controller
@RequestMapping(value="/api/transition/v1")
public class DataBaseController {

	@Autowired
	private IDataBase iDataBase;
	
	@RequestMapping(value="/dataBase/tableNames", method = RequestMethod.GET)
	@ResponseBody
	public List<String> queryPreTableNames(DataBase customDataBase){
		return iDataBase.queryTableNames(customDataBase);
	}
	@RequestMapping(value="/dataBase/columnNames", method = RequestMethod.GET)
	@ResponseBody
	public List<String> queryPreColumnNames(DataBaseDto dataBaseDto){
		return iDataBase.queryColumnNames(dataBaseDto);
	}
	@RequestMapping(value="/dataBase",method=RequestMethod.POST)
	@ResponseBody
	public DataBase createPreDataBase(DataBase customDataBase){
		return iDataBase.createDataBase(customDataBase);
	}
	@RequestMapping(value="/dataBase",method=RequestMethod.GET)
	@ResponseBody
	public String testPreDataBase(DataBase customDataBase){
		return iDataBase.testDataBase(customDataBase);
	}
}
