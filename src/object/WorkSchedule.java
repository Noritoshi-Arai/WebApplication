package object;

import java.sql.Date;

public class WorkSchedule {
	//�ϐ��̒�`
	private String id;
	private Date date;
	private int status;
	private String statusName;
	private String nameKanji;
	private String nameKana;
	
	//�R���X�g���N�^�̒�`
	public WorkSchedule() {
		id = null;
		date = null;
		status = 0;
		statusName = null;
	}
	
	public WorkSchedule(String id, Date date, int status, String statusName) {
		this.id = id;
		this.date = date;
		this.status = status;
		this.statusName = statusName;
	}
	
	//�o�Η\�茟���p�R���X�g���N�^
	public WorkSchedule(Date date, String nameKanji, String nameKana, String statusName) {
		this.date = date;
		this.nameKanji = nameKanji;
		this.nameKana = nameKana;
		this.statusName = statusName;
	}
	
	//setter, getter�̒�`
	//ID
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	//Date
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	//status flag
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
	
	//nameKanji
	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}
	
	public String getNameKanji() {
		return nameKanji;
	}
	
	//nameKana
	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}
	
	public String getNameKana() {
		return nameKana;
	}
}
