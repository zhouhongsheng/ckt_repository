package tran.tran.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tran.tran.dao.IDataSourceDao;
import tran.tran.intf.IDataSource;
import tran.tran.model.DataSource;

@Service
public class DataSourceImpl implements IDataSource {

	@Autowired
	private IDataSourceDao iDataSourceDao;

	/*
	 * insert
	 * 
	 * @param dataSourceDto
	 * 
	 * @throws Exception
	 * 
	 * @see tran.tran.intf.IDataSource#insertDataSource(tran.tran.model.dto.
	 * DataSourceDto) 2017年1月23日
	 */
	@Override
	public void insertDataSource(DataSource dataSource) throws Exception {
		try {
			iDataSourceDao.insert(dataSource);
		} catch (Exception e) {
			throw e;
		}
	}
}
