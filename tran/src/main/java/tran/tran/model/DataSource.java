package tran.tran.model;

import tran.tran.model.dto.DataSourceDto;

public class DataSource {

	private String id;
	private int type;
	private String dataSource;
	
	public DataSource(){
		
	}
	public DataSource(DataSourceDto dataSourceDto){
		this.id=dataSourceDto.getId();
		this.type=dataSourceDto.getType();
		if(this.type==1){
			this.dataSource=dataSourceDto.getDataBaseDto().getId();
		}else{
			this.dataSource=dataSourceDto.getPath();
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
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
}
