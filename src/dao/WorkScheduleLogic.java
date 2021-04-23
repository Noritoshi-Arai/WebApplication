package dao;

import java.util.Date;
import java.util.List;
import object.WorkSchedule;

public class WorkScheduleLogic {
	//�o�Η\����������郁�\�b�h�i�o�Η\����͋@�\�p�j
	public List<WorkSchedule> searchStatus(String id, String year, String month){
		WorkScheduleDao dao = new WorkScheduleDao();
		List<WorkSchedule> wsList = dao.searchSt(id, year, month);
		return wsList;
	}
	
	//�o�Η\����������郁�\�b�h�i�o�Η\�茟���@�\�p�j
	public List<WorkSchedule> searchName(String nameKanji, String nameKana, String startDate, String finishDate){
		WorkScheduleDao dao = new WorkScheduleDao();
		List<WorkSchedule> list = dao.searchNa(nameKanji, nameKana, startDate, finishDate);
		return list;
	}
	
	
	//�o�Η\����X�V���郁�\�b�h
	public boolean updateStatus(String id, String date, int status) {
		boolean result = false;
		WorkScheduleDao dao = new WorkScheduleDao();
		result = dao.update(id, date, status);
		return result;
	}
	
	//Toppage���b�Z�[�W�o�̓��\�b�h(�L�x)
	public boolean message(String id, String year, String month) {
		boolean result = false;
		WorkScheduleDao dao = new WorkScheduleDao();
		Date date = dao.searchRest(id, year, month);
		if(date != null) {
			result = true;
		}
		return result;
	}
	
	//Toppage���b�Z�[�W�o�̓��\�b�h�inull�j
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
