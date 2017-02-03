package tran.tran.model.dto;

import tran.tran.model.DataBase;

public class DataBaseTableNameDto {

	private String tableName;
	private DataBase dataBase;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public DataBase getDataBase() {
		return dataBase;
	}
	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}
	
}
