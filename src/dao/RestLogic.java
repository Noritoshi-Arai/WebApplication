package dao;

import object.Rest;
import java.util.Date;

public class RestLogic {
	//�x�ɎQ�ƃ��\�b�h
	public Rest searchRest(String id, int year) {
		RestDao dao = new RestDao();
		Rest rest = dao.search(id, year);
		return rest;
	}
	

}
