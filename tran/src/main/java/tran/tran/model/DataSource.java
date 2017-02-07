package tran.tran.model;

import tran.tran.model.dto.DataSourceDto;

public class DataSource {

	private String id;
	private int type;
	private String dataBase;
	
	public DataSource(){
		
	}
	public DataSource(DataSourceDto dataSourceDto){
		this.id=dataSourceDto.getId();
		this.type=dataSourceDto.getType();
		if(this.type==1){
			this.dataBase=dataSourceDto.getDataBaseDto().getId();
		}else{
			this.dataBase=dataSourceDto.getPath();
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
}
