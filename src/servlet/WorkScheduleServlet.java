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
import java.text.SimpleDateFormat;
import dao.WorkScheduleLogic;
import object.WorkSchedule;
import object.Staff;

@WebServlet("/WorkScheduleServlet")
public class WorkScheduleServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
			{
			this.doPost(request, response);
			}
	
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//SimpleDateFormat
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		
		//�o�Η\��I�u�W�F�N�g���i�[���邽�߂̃��X�g
		List<WorkSchedule> wsList = null;
		String year = null;
		String month = null;
		
		//�Z�b�V�����ɓo�^����id���擾����
		HttpSession session = request.getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		wsList = (List<WorkSchedule>) session.getAttribute("WorkSchedule");
		
		String page = request.getParameter("page");
		if(page == null) {
			page = "not change";
		}
		
		if(page.equals("change")) {
			year = request.getParameter("year");
			month = request.getParameter("month");
			
			WorkSchedule ws = wsList.get(0);
			Date date = ws.getDate();
			String year1 = ysdf.format(date);
			String month1 = msdf.format(date);
			if(!(year1.equals(year) && month1.equals(month))){
				//���\�b�h�̌Ăяo��
				WorkScheduleLogic logic = new WorkScheduleLogic();
				wsList = logic.searchStatus(staff.getId(), year, month);
				session.setAttribute("WorkSchedule", wsList);
			}
		}else if(page.equals("register")){
			WorkScheduleLogic logic = new WorkScheduleLogic();
			String[] statusArray = request.getParameterValues("status");
			int i = 0;
			for(WorkSchedule ws : wsList) {
				if(ws.getStatus() != Integer.parseInt(statusArray[i])) {
					//�Ζ��`�Ԃ̕ύX
					logic.updateStatus(staff.getId(), ws.getDate().toString(), Integer.parseInt(statusArray[i]));
				}
				i++;
			}
			//�ύX��̌��̃��X�g���i�[	
			WorkSchedule ws = wsList.get(0);
			year = ysdf.format(ws.getDate());
			month = msdf.format(ws.getDate());
			System.out.println("year" + year);
			System.out.println("month" + month);
			wsList = logic.searchStatus(staff.getId(), year, month);
			System.out.println("size:" + wsList.size());
			session.setAttribute("WorkSchedule", wsList);
		}else {
			//���݂̔N�ƌ����擾
			Date date = new Date();
			//�N
			year = ysdf.format(date);
			//��
			month = msdf.format(date);
			
			//���\�b�h�̌Ăяo��
			WorkScheduleLogic logic = new WorkScheduleLogic();
			wsList = logic.searchStatus(staff.getId(), year, month);
			session.setAttribute("WorkSchedule", wsList);
		}
		
		/*
		//���݂̔N�ƌ����擾
		Date date = new Date();
		//�N
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		String year = ysdf.format(date);
		//��
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		String month = msdf.format(date);
		*/
		
		/*
		//���\�b�h�̌Ăяo��
		WorkScheduleLogic logic = new WorkScheduleLogic();
		wsList = logic.searchStatus(staff.getId(), year, month);
		session.setAttribute("WorkSchedule", wsList);
		System.out.println("length:" + wsList.size());
		*/
		//��ʑJ��
		request.getRequestDispatcher("/ScheduleInput.jsp").forward(request, response);
		
		
	}

}
