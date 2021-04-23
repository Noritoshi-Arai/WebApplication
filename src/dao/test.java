package dao;

import object.Staff;
import object.Rest;
import object.WorkResult;
import object.WorkSchedule;
import dao.WorkScheduleDao;
import dao.WorkScheduleLogic;
import java.util.List;
import java.sql.Date;
import dao.WorkResultDao;

public class test {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//Dao用テスト
		/*
		WorkResultDao dao = new WorkResultDao();
		dao.update("111111", "2021-01-01", 1, 850, 1700);
		List<WorkResult> list = dao.search("111111", "2021", "01");
		WorkResult wr = list.get(0);
		System.out.println("size" + list.size());
		System.out.println("date" + wr.getDate());
		System.out.println("status" + wr.getStatusName());
		System.out.println("timeStart" + wr.getTimeStart());
		System.out.println("timeFinish" + wr.getTimeFinish());
		*/
		
		//Logic用テスト
		/*
		WorkResultLogic logic = new WorkResultLogic();
		logic.updateResult("111111", "2021-01-02", 2, 850, 1700);
		List<WorkResult> list = logic.searchResult("111111", "2021", "01");
		WorkResult wr = list.get(1);
		System.out.println("size" + list.size());
		System.out.println("date" + wr.getDate());
		System.out.println("status" + wr.getStatusName());
		System.out.println("timeStart" + wr.getTimeStart());
		System.out.println("timeFinish" + wr.getTimeFinish());
		*/
		
		/*
		WorkScheduleLogic logic = new WorkScheduleLogic();
		List<WorkSchedule> list = logic.searchStatus("111111", "2021", "01");
		for(int i = 0; i <= 6; i++) {
			WorkSchedule workSchedule = list.get(i);
			System.out.println("ID:" + workSchedule.getId());
			System.out.println("Date:" + workSchedule.getDate());
			System.out.println("status:" + workSchedule.getStatusName());
		}
		System.out.println("number" + list.size());
		
		boolean result = logic.updateStatus("111111", "20210103", 2);
		System.out.println(result);
		*/
		
		/*
		String a = "1";
		if(a.length() == 1) {
			a = "0" + a;
			System.out.println(a);
		}else {
			System.out.println(a);
		}
		*/
		
		/*
		RestDao dao = new RestDao();
		Date date = dao.searchRest("111111", "2021", "04");
		System.out.println(date);
		*/
		
		/*
		WorkResultLogic logic = new WorkResultLogic();
		List<Date> list = logic.checkNull("111111", "2021", "04", "05");
		System.out.println(list.size());
		*/
		
	}
}
