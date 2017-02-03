package tran.tran.intf;

import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
import tran.tran.model.DataBase;
import tran.tran.model.dto.DataBaseTableNameDto;

public interface IDataBase {

	public DataSource createDataBase(DataBase customDataBase);
	
	public String testDataBase(DataBase customDataBase);
	
	public List<String> queryTableNameList(DataBase customDataBase);

	public List<String> queryColumnNames(DataBaseTableNameDto dataBaseTableNameDto);
	
	public void insertDataBase(DataBase dataBase) throws Exception;
}
