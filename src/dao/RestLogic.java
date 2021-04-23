package dao;

import object.Rest;
import java.util.Date;

public class RestLogic {
	//休暇参照メソッド
	public Rest searchRest(String id, int year) {
		RestDao dao = new RestDao();
		Rest rest = dao.search(id, year);
		return rest;
	}
	

}
