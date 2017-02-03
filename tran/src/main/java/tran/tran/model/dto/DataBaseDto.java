package tran.tran.model.dto;

import java.util.UUID;

public class DataBaseDto {

	private String id=UUID.randomUUID().toString().trim().replaceAll("-", "");
	private String url;
	private String port;
	private String schemaName;
	private String driverName;
	private String userName;
	private String passWord;
	private int dataBaseType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getDataBaseType() {
		return dataBaseType;
	}
	public void setDataBaseType(int dataBaseType) {
		this.dataBaseType = dataBaseType;
	}
	
}
