package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import dao.RestLogic;
import object.Rest;
import object.Staff;

@WebServlet("/RestServlet")
public class RestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
			{
			this.doPost(request, response);
			}
	
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//Map<String, Rest> restMap = new HashMap();
		String year = null;
		
		//�ǂ̃y�[�W����J�ڂ��������f���邽�߂̃t���O
		String page = request.getParameter("page");
		if(page == null) {
			page = "not change";
		}
		
		//�Z�b�V�����ɓo�^����id���擾����
		HttpSession session = request.getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		Rest rest = (Rest) session.getAttribute("rest");
		
		if(page.equals("change")) {
			year = request.getParameter("year");
		}else {
			//���݂̔N���擾
			Date date = new Date();
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			year = ysdf.format(date);
		}
		
		if(rest == null) {
			RestLogic logic = new RestLogic();
			rest = logic.searchRest(staff.getId(), Integer.parseInt(year));
			session.setAttribute("rest", rest);
		}else {
			if(rest.getYear() != Integer.parseInt(year)) {
				RestLogic logic = new RestLogic();
				rest = logic.searchRest(staff.getId(), Integer.parseInt(year));
				session.setAttribute("rest", rest);
			}
		}
		
		/*
		if(rest.getYear() != Integer.parseInt(year)) {
			RestLogic logic = new RestLogic();
			rest = logic.searchRest(staff.getId(), Integer.parseInt(year));
			session.setAttribute("rest", rest);
		}
		*/
		
		/*
		if(restMap.get(year) != null) {
			rest = restMap.get(year);
		}else {
			RestLogic logic = new RestLogic();
			rest = logic.searchRest(staff.getId(), Integer.parseInt(year));
		}
		*/
		
		//rest�I�u�W�F�N�g���Z�b�V�����ɓo�^
		//session.setAttribute("rest", rest);

		request.getRequestDispatcher("/RestSituation.jsp").forward(request, response);
		
	}

}
