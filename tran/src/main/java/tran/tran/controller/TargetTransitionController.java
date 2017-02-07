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
public class TargetTransitionController {

	@Autowired
	private IDataBase iDataBase;
	
	@RequestMapping(value="/targetTableNames", method = RequestMethod.GET)
	@ResponseBody
	public List<String> queryTargetTableNames(DataBase customDataBase){
		return iDataBase.queryTableNames(customDataBase);
	}
	
	@RequestMapping(value="/targetColumnNames", method = RequestMethod.POST)
	@ResponseBody
	public List<String> queryTargetColumnNames(@RequestBody DataBaseTableNameDto dataBaseTableNameDto){
		return iDataBase.queryColumnNames(dataBaseTableNameDto);
	}
	@RequestMapping(value="/targetDataBase",method=RequestMethod.POST)
	@ResponseBody
	public DataBase createPreTran(DataBase customDataBase){
		return iDataBase.createDataBase(customDataBase);
	}
	@RequestMapping(value="/targetDataBase",method=RequestMethod.GET)
	@ResponseBody
	public String testPreTran(DataBase customDataBase){
		return iDataBase.testDataBase(customDataBase);
	}
}
