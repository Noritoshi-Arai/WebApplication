package object;

public class Staff {
	private String id;
	private String nameKanji;
	private String nameKana;
	private String password;
	private boolean flag;
	
	//�R���X�g���N�^
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
	
	//�������O
	public void setNameKanji(String nameKanji) {
		this.nameKanji = nameKanji;
	}
		
	public String getNameKanji() {
		return nameKanji;
	}
	
	//���Ȗ��O
	public void setNameKana(String nameKana) {
		this.nameKana = nameKana;
	}
		
	public String getNameKana() {
		return nameKana;
	}
	
	//�p�X���[�h
	public void setPassword(String password) {
		this.password = password;
	}
		
	public String getPassword() {
		return password;
	}
	
	//�Ǘ��҃t���O
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean getFlag() {
		return flag;
	}

}
