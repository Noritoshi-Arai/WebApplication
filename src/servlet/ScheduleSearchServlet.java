package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.text.SimpleDateFormat;
import dao.WorkScheduleLogic;
import object.WorkSchedule;

@WebServlet("/ScheduleSearchServlet")
public class ScheduleSearchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
			{
			this.doPost(request, response);
			}
	
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		//�o�Η\��I�u�W�F�N�g���i�[���邽�߂̃��X�g
		List<WorkSchedule> list = null;
		
		HttpSession session = request.getSession();
		
		//ScheduleSearch.jsp���瑗��ꂽ�l�̎擾
		String nameKanji = request.getParameter("nameKanji");	//����
		String nameKana = request.getParameter("nameKana");	//�V���C
		String startYear = request.getParameter("startYear");	//�N�i�X�^�[�g�j
		String startMonth = request.getParameter("startMonth");	//���i�X�^�[�g�j
		if(startMonth.length() == 1) {
			startMonth = "0" + startMonth;
		}
		String startDay = request.getParameter("startDay");	//���i�X�^�[�g�j
		if(startDay.length() == 1) {
			startDay = "0" + startDay;
		}
		String finishYear = request.getParameter("finishYear");	//�N�i�I���j
		String finishMonth = request.getParameter("finishMonth");	//���i�I���j
		if(finishMonth.length() == 1) {
			finishMonth = "0" + finishMonth;
		}
		String finishDay = request.getParameter("finishDay");	//���i�I���j
		if(finishDay.length() == 1) {
			finishDay = "0" + finishDay;
		}
		
		//���t�i�X�^�[�g�j��null�����邩�`�F�b�N
		if((startYear == null || startYear.length() == 0) && (startMonth == null || startMonth.length() == 0) && (startDay == null || startDay.length() == 0)) {
			startYear = finishYear;
			startMonth = finishMonth;
			startDay = finishDay;
		}
		
		//���t�i�I���j��null�͂Ȃ����`�F�b�N
		if((finishYear == null || finishYear.length() == 0) && (finishMonth == null || finishMonth.length() == 0) && (finishDay == null || finishDay.length() == 0)) {
			finishYear = startYear;
			finishMonth = startMonth;
			finishDay = startDay;
		}
		
		//�S�Ă̓��t�ɓ��͂��Ȃ��ꍇ
		String startDate = startYear + startMonth + startDay;
		String finishDate = finishYear + finishMonth + finishDay;
		
		if((startDate == null || startDate.length() == 0) && (finishDate == null || finishDate.length() == 0)) {
			//SimpleDateFormat
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat msdf = new SimpleDateFormat("MM");
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
			
			//���݂̔N�ƌ����擾
			Date date = new Date();
			startYear = finishYear = ysdf.format(date);
			startMonth = finishMonth = msdf.format(date);
			startDay = finishDay = dsdf.format(date);
		}
		
		/*
		if((startYear == null) && (startMonth == null) && (startDay == null) && 
				(finishYear == null) && (finishMonth == null) && (finishDay == null)) {
			//SimpleDateFormat
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat msdf = new SimpleDateFormat("MM");
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
			
			//���݂̔N�ƌ����擾
			Date date = new Date();
			startYear = finishYear = ysdf.format(date);
			startMonth = finishMonth = msdf.format(date);
			startDay = finishDay = dsdf.format(date);
		}
		*/
		
		
		//�o�Η\�茟�����\�b�h�i�o�Η\�茟���@�\�p�j�̌Ăяo��
		WorkScheduleLogic logic = new WorkScheduleLogic();
		list = logic.searchName(nameKanji, nameKana, (startYear + startMonth + startDay), (finishYear + finishMonth + finishDay));
		session.setAttribute("searchResult", list);
		
		//��ʑJ��
		request.getRequestDispatcher("/ScheduleResult.jsp").forward(request, response);
		
		
	}

}
