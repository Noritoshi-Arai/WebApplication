package dao;

import object.Staff;

public class StaffLogic {
	//���O�C�����\�b�h
	public Staff searchStaff(String id, String password) {
		//Staff staff = new Staff();
		StaffDao dao = new StaffDao();
		Staff staff = dao.search(id, password);
		return staff;
		
		/*
		//�������\�b�h�̌Ăяo��
		result = dao.search(id, password);
		try {
			if(result.next()) {
				staff.setId(result.getString("id"));
				staff.setNameKanji(result.getString("name_kanji"));
				staff.setNameKana(result.getString("name_kana"));
				staff.setPassword(result.getString("password"));
				staff.setFlag(result.getBoolean("flag"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return staff;
		*/
	}
	

}
