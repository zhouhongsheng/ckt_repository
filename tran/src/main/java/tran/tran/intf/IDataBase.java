package tran.tran.intf;

import java.util.List;
import tran.tran.model.DataBase;
import tran.tran.model.dto.DataBaseTableNameDto;

public interface IDataBase {

	public DataBase createDataBase(DataBase customDataBase);
	
	public String testDataBase(DataBase customDataBase);
	
	public List<String> queryTableNames(DataBase customDataBase);

	public List<String> queryColumnNames(DataBaseTableNameDto dataBaseTableNameDto);
	
	public void insertDataBase(DataBase dataBase) throws Exception;
	
	public void updateDataBase(DataBase dataBase) throws Exception;
}
