package object;

//import java.sql.Date;

public class Rest {
	//�ϐ��̒�`
	private String id;
	private int year;
	private int possibleRest;
	private int mustRest;
	
	//�R���X�g���N�^
	public Rest() {
		id = null;
		year = 0;
		possibleRest = 10;
		mustRest = 5;
	}
	
	public Rest(String id, int year, int possibleRest, int mustRest) {
		this.id = id;
		this.year = year;
		this.possibleRest = possibleRest;
		this.mustRest = mustRest;
	}
	
	//getter, setter�̒�`
	//ID
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	//�N
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	
	//�擾�\�L����
	public void setPossibleRest(int possibleRest) {
		this.possibleRest = possibleRest;
	}
	
	public int getPossibleRest() {
		return possibleRest;
	}
	
	//�擾�K�{�L����
	public void setMustRest(int mustRest) {
		this.mustRest = mustRest;
	}
	
	public int getMustRest() {
		return mustRest;
	}

}
