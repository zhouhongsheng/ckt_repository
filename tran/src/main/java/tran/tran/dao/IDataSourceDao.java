package tran.tran.dao;

import tran.tran.model.DataSource;

public interface IDataSourceDao {

	public int insert(DataSource dataSource);
	
	public int update(DataSource dataSource);
}
