package object;

public class WorkStatus {
	//変数の定義
	private int status;
	private String statusName;
	
	//コンストラクタの定義
	public WorkStatus() {
		status = 0;
		statusName = null;
	}
	
	public WorkStatus(int status, String statusName) {
		this.status = status;
		this.statusName = statusName;
	}
	
	//setter, getterの定義
	//status
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	//statusName
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	public String getStatusName() {
		return statusName;
	}

}
