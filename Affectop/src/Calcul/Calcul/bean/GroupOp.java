package Calcul.Calcul.bean;

import java.util.List;

public class GroupOp {

	private int groupId;
	
	private int optionId;
	
	public GroupOp(int groupId, int optionId) {
		this.groupId = groupId;
		this.optionId = optionId;
	}
	
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	} 
}
