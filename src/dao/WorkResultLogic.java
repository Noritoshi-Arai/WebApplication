package dao;

import java.util.List;
import java.util.Date;
import object.WorkResult;


public class WorkResultLogic {
	//勤務実績を検索するメソッド
	public List<WorkResult> searchResult(String id, String year, String month){
		WorkResultDao dao = new WorkResultDao();
		List<WorkResult> wrList = dao.search(id, year, month);
		return wrList;
	}
	
	//勤務実績を更新するメソッド
	public boolean updateResult(String id, String date, int status, int timeStart, int timeFinish) {
		boolean result = false;
		WorkResultDao dao = new WorkResultDao();
		result = dao.update(id, date, status, timeStart, timeFinish);
		return result;
	}
	
	//Toppageメッセージ出力メソッド
	public List<Date> checkNull(String id, String year, String month, String day){
		List<Date> nullList = null;
		WorkResultDao dao = new WorkResultDao();
		nullList = dao.checkDate(id, year, month, day);
		return nullList;
	}

}
