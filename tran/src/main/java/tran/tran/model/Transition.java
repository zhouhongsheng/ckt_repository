package tran.tran.model;

import java.util.UUID;

public class Transition {

	private String id=UUID.randomUUID().toString().trim().replaceAll("-", "");
	private String tranName;
	private String userId;
	private String preDataSourceId;
	private String targetDataSourceId;
	private int operateType;
	private int countType;//这个字段无实际意义，insert or update的时候使用
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPreDataSourceId() {
		return preDataSourceId;
	}
	public void setPreDataSourceId(String preDataSourceId) {
		this.preDataSourceId = preDataSourceId;
	}
	public String getTargetDataSourceId() {
		return targetDataSourceId;
	}
	public void setTargetDataSourceId(String targetDataSourceId) {
		this.targetDataSourceId = targetDataSourceId;
	}
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public int getCountType() {
		return countType;
	}
	public void setCountType(int countType) {
		this.countType = countType;
	}
	
}
