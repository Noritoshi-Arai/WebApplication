package object;

//import java.sql.Date;

public class Rest {
	//変数の定義
	private String id;
	private int year;
	private int possibleRest;
	private int mustRest;
	
	//コンストラクタ
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
	
	//getter, setterの定義
	//ID
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	//年
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	
	//取得可能有給数
	public void setPossibleRest(int possibleRest) {
		this.possibleRest = possibleRest;
	}
	
	public int getPossibleRest() {
		return possibleRest;
	}
	
	//取得必須有給数
	public void setMustRest(int mustRest) {
		this.mustRest = mustRest;
	}
	
	public int getMustRest() {
		return mustRest;
	}

}
