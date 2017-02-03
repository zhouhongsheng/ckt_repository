package tran.tran.model.dto;

import java.util.UUID;

public class DataSourceDto {
	private String id=UUID.randomUUID().toString().trim().replaceAll("-", "");
	private int type;
	private DataBaseDto dataBaseDto;
	private String path;
	
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
	public DataBaseDto getDataBaseDto() {
		return dataBaseDto;
	}
	public void setDataBaseDto(DataBaseDto dataBaseDto) {
		this.dataBaseDto = dataBaseDto;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
