package tran.tran.intf;

import tran.tran.model.DataSource;

public interface IDataSource {

	public void insertDataSource(DataSource dataSource) throws Exception;
	
	public void updateDataSource(DataSource dataSource) throws Exception;
}
