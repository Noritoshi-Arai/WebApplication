package object;

public class WorkStatus {
	//�ϐ��̒�`
	private int status;
	private String statusName;
	
	//�R���X�g���N�^�̒�`
	public WorkStatus() {
		status = 0;
		statusName = null;
	}
	
	public WorkStatus(int status, String statusName) {
		this.status = status;
		this.statusName = statusName;
	}
	
	//setter, getter�̒�`
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
