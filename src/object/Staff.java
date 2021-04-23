package object;

public class Staff {
	private String id;
	private String nameKanji;
	private String nameKana;
	private String password;
	private boolean flag;
	
	//コンストラクタ
	public Staff() {
		id = null;
		nameKanji = null;
		nameKana = null;
		password = null;
		flag = false;
	}
	
	public Staff(String id, String nameKanji, String nameKana, String password, boolean flag) {
		this.id = id;
		this.nameKanji = nameKanji;
		this.nameKana = nameKana;
		this.password = password;
		this.flag = flag;
	}
	
	//ID
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	//漢字名前
	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}
		
	public String getNameKanji() {
		return nameKanji;
	}
	
	//かな名前
	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}
		
	public String getNameKana() {
		return nameKana;
	}
	
	//パスワード
	public void setPassword(String password) {
		this.password = password;
	}
		
	public String getPassword() {
		return password;
	}
	
	//管理者フラグ
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean getFlag() {
		return flag;
	}

}
