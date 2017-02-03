package tran.tran.model;

import tran.tran.model.dto.DataBaseDto;

public class DataBase {

	private String id;
	private String url;
	private String port;
	private String schemaName;
	private String driverName;
	private String userName;
	private String passWord;
	private int dataBaseType;
	public DataBase(){
		
	}
	public DataBase(DataBaseDto dataBaseDto){
		this.id=dataBaseDto.getId();
		this.url=dataBaseDto.getUrl();
		this.port=dataBaseDto.getPort();
		this.schemaName=dataBaseDto.getSchemaName();
		this.driverName=dataBaseDto.getDriverName();
		this.userName=dataBaseDto.getUserName();
		this.passWord=dataBaseDto.getPassWord();
		this.dataBaseType=dataBaseDto.getDataBaseType();
	}
	
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
