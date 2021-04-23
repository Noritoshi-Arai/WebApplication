package dao;

import java.util.Date;
import java.util.List;
import object.WorkSchedule;

public class WorkScheduleLogic {
	//出勤予定を検索するメソッド（出勤予定入力機能用）
	public List<WorkSchedule> searchStatus(String id, String year, String month){
		WorkScheduleDao dao = new WorkScheduleDao();
		List<WorkSchedule> wsList = dao.searchSt(id, year, month);
		return wsList;
	}
	
	//出勤予定を検索するメソッド（出勤予定検索機能用）
	public List<WorkSchedule> searchName(String nameKanji, String nameKana, String startDate, String finishDate){
		WorkScheduleDao dao = new WorkScheduleDao();
		List<WorkSchedule> list = dao.searchNa(nameKanji, nameKana, startDate, finishDate);
		return list;
	}
	
	
	//出勤予定を更新するメソッド
	public boolean updateStatus(String id, String date, int status) {
		boolean result = false;
		WorkScheduleDao dao = new WorkScheduleDao();
		result = dao.update(id, date, status);
		return result;
	}
	
	//Toppageメッセージ出力メソッド(有休)
	public boolean message(String id, String year, String month) {
		boolean result = false;
		WorkScheduleDao dao = new WorkScheduleDao();
		Date date = dao.searchRest(id, year, month);
		if(date != null) {
			result = true;
		}
		return result;
	}
	
	//Toppageメッセージ出力メソッド（null）
	public boolean messageNull(String id, String year, String month) {
		boolean result = false;
		WorkScheduleDao dao = new WorkScheduleDao();
		Date date = dao.searchNull(id, year, month);
		if(date != null) {
			result = true;
		}
		return result;
	}

}
