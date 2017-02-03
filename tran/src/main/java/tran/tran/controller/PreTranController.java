package tran.tran.controller;

import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
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
@RequestMapping(value="/api/pretran")
public class PreTranController {

	@Autowired
	private IDataBase iDataBase;
	
	@RequestMapping(value="/query-pre-table-list", method = RequestMethod.GET)
	@ResponseBody
	public List<String> queryPreTableNameList(DataBase customDataSource){
		return iDataBase.queryTableNameList(customDataSource);
	}
	
	@RequestMapping(value="/query-pre-columnnames", method = RequestMethod.POST)
	@ResponseBody
	public List<String> queryPreColumnNames(@RequestBody DataBaseTableNameDto dataBaseTableNameDto){
		return iDataBase.queryColumnNames(dataBaseTableNameDto);
	}
	@RequestMapping(value="/create-pretran-datasource",method=RequestMethod.PUT)
	@ResponseBody
	public DataBase createPreTran(DataBase customDataSource){
		DataSource dataSource= iDataBase.createDataBase(customDataSource);
		if(dataSource!=null){
			return customDataSource;
		}else{
			return null;
		}
	}
	@RequestMapping(value="/test-pretran-datasource",method=RequestMethod.POST)
	@ResponseBody
	public String testPreTran(DataBase customDataSource){
		return iDataBase.testDataBase(customDataSource);
	}
}
