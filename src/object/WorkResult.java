package object;

import java.sql.Date;

public class WorkResult {
	//�ϐ��̒�`
	private String id;
	private Date date;
	private int status;
	private String statusName;
	private int timeStart;
	private int timeFinish;
	
	//�R���X�g���N�^�̒�`
	public WorkResult() {
		id = null;
		date = null;
		status = 0;
		statusName = null;
		timeStart = 0;
		timeFinish = 0;
	}
	
	public WorkResult(Date date, int status, String statusName, int timeStart, int timeFinish) {
		this.date = date;
		this.status = status;
		this.statusName = statusName;
		this.timeStart = timeStart;
		this.timeFinish = timeFinish;
	}
	
	//setter, getter�̒�`
	//ID
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	//date
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
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
	
	//timeStart
	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}
	
	public int getTimeStart() {
		return timeStart;
	}
	
	//timeFinish
	public void setTimeFinish(int timeFinish) {
		this.timeFinish = timeFinish;
	}
	
	public int getTimeFinish() {
		return timeFinish;
	}
}
