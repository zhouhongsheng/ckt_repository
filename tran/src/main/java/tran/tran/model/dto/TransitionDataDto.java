package tran.tran.model.dto;

import java.util.List;

public class TransitionDataDto {

	private DataSourceDto preDataSourceDto;
	private DataSourceDto targetDataSourceDto;
	private String tranName;
	private String userId;
	private int operateType;
	private String preColumnName;
	private String targetColumnName;
	private List<String> tranArray;
	
	public DataSourceDto getPreDataSourceDto() {
		return preDataSourceDto;
	}
	public void setPreDataSourceDto(DataSourceDto preDataSourceDto) {
		this.preDataSourceDto = preDataSourceDto;
	}
	public DataSourceDto getTargetDataSourceDto() {
		return targetDataSourceDto;
	}
	public void setTargetDataSourceDto(DataSourceDto targetDataSourceDto) {
		this.targetDataSourceDto = targetDataSourceDto;
	}
	public List<String> getTranArray() {
		return tranArray;
	}
	public void setTranArray(List<String> tranArray) {
		this.tranArray = tranArray;
	}
	public String getPreColumnName() {
		return preColumnName;
	}
	public void setPreColumnName(String preColumnName) {
		this.preColumnName = preColumnName;
	}
	public String getTargetColumnName() {
		return targetColumnName;
	}
	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public String getTranName() {
		return tranName;
	}
	public void setTranName(String tranName) {
		this.tranName = tranName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
