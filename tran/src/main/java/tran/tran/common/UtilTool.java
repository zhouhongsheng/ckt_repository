package tran.tran.common;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;

import tran.tran.model.DataBase;

public class UtilTool {

	// 配置数据库连接信息
	public static DataSource dataBase(DataBase customDataSource) {
		DataSource dataSource = new DataSource();
		try {
			dataSource.setDriverClassName(customDataSource.getDriverName());
			dataSource.setUrl(customDataSource.getUrl());
			dataSource.setUsername(customDataSource.getUserName());
			dataSource.setPassword(customDataSource.getPassWord());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	public static void getQueryColumns(StringBuffer sb,List<String> prelist){
		for (String string : prelist) {
			sb.append(string);
			sb.append(",");
		}
		if(sb.lastIndexOf(",")>0){
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
	}
	public static void getQueryContext(StringBuffer sb,List<String> list){
		for (String string : list) {
			sb.append("'"+string+"'");
			sb.append(",");
		}
		if(sb.lastIndexOf(",")>0){
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
	}
}
