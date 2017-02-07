package tran.tran.model;

import java.util.UUID;

public class PreTargetMapping {

	private String id=UUID.randomUUID().toString().trim().replaceAll("-", "");
	private int type;
	private String preColumnName;
	private String targetColumnName;
	private String transitionId;
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
	public String getTransitionId() {
		return transitionId;
	}
	public void setTransitionId(String transitionId) {
		this.transitionId = transitionId;
	}
	
}
