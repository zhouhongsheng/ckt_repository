package tran.tran.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tran.tran.common.StaticConstant;
import tran.tran.common.UtilTool;
import tran.tran.dao.IDataBaseDao;
import tran.tran.intf.IDataBase;
import tran.tran.model.DataBase;
import tran.tran.model.dto.DataBaseDto;

@Service
public class DataBaseImpl implements IDataBase {

	@Autowired
	private IDataBaseDao iDataBaseDao;

	/*
	 * insert
	 * 
	 * @param dataBaseDto
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * 2017年1月23日
	 */
	@Override
	public void insertDataBase(DataBase dataBase) throws Exception {
		try {
			iDataBaseDao.insert(dataBase);
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * create
	 * 
	 * @param customDataBase
	 * 
	 * @return
	 * 
	 * @see tran.tran.intf.IDataBase#createDataBase(tran.tran.model.DataBase)
	 * 2017年1月22日
	 */
	@Override
	public DataBase createDataBase(DataBase customDataBase) {
		DataSource dataSource = UtilTool.dataBase(customDataBase);
		try {
			Connection con = dataSource.getConnection();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dataSource.close();
		}
		return customDataBase;
	}

	/*
	 * test
	 * 
	 * @param customDataBase
	 * 
	 * @return
	 * 
	 * @see tran.tran.intf.IDataBase#testDataBase(tran.tran.model.DataBase)
	 * 2017年1月22日
	 */
	@Override
	public String testDataBase(DataBase customDataBase) {
		String result = "";
		DataSource dataSource = UtilTool.dataBase(customDataBase);
		try {
			Connection con = dataSource.getConnection();
			result = "true";
			con.close();
		} catch (SQLException e) {
			result = e.toString();
		} finally {
			dataSource.close();
		}
		return result;
	}

	/*
	 * query tablenames
	 * 
	 * @param customDataBase
	 * 
	 * @return
	 * 
	 * @see
	 * tran.tran.intf.IDataBase#queryTableNameList(tran.tran.model.DataBase)
	 * 2017年1月22日
	 */
	@Override
	public List<String> queryTableNames(DataBase customDataBase) {
		List<String> result = new ArrayList<>();
		String sql = "";
		String tableName = "";

		DataSource dataSource = UtilTool.dataBase(customDataBase);
		switch (customDataBase.getDataBaseType()) {
		case 1:
			sql = "select tablename from pg_tables where schemaname='public'";
			break;
		case 2:
			sql = "mysql ";
		case 3:
			sql = "oracle";
		default:
			sql = "select tablename from pg_tables where schemaname='public'";
			break;
		}
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tableName = rs.getString("tablename");
				result.add(tableName);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dataSource.close();
		}
		return result;
	}

	/*
	 * query columns
	 * 
	 * @param preDataColumn
	 * 
	 * @return
	 * 
	 * @see
	 * tran.tran.intf.IDataBase#queryColumnNames(tran.tran.model.DataColumn)
	 * 2017年1月22日
	 */
	@Override
	public List<String> queryColumnNames(DataBaseDto dataBaseDto) {
		List<String> result = new ArrayList<>();
		String sql = "";
		String columnName = "";

		DataSource dataSource = UtilTool.dataBase(new DataBase(dataBaseDto));
		switch (dataBaseDto.getDataBaseType()) {
		case 1:
			sql = "select column_name from information_schema.columns where table_schema = 'public' and table_name='"
					+ dataBaseDto.getTableName() + "'";
			break;
		case 2:
			sql = "mysql ";
		case 3:
			sql = "oracle";
		default:
			sql = "select column_name from information_schema.columns where table_schema = 'public' and table_name='"
					+ dataBaseDto.getTableName() + "'";
			break;
		}
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// 由于只查询了一列，所有索引一直未1
				Object o = rs.getObject(StaticConstant.PRE_COLUMNNAME_NUM);
				columnName = o.toString();
				result.add(columnName);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dataSource.close();
		}
		return result;
	}

	@Override
	public void updateDataBase(DataBase dataBase) throws Exception {
		try {
			iDataBaseDao.update(dataBase);
		} catch (Exception e) {
			throw e;
		}
	}
}
