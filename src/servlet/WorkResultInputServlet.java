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
import dao.WorkResultLogic;
import object.WorkResult;
import object.Staff;

@WebServlet("/WorkResultInputServlet")
public class WorkResultInputServlet extends HttpServlet{
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
		
		//勤務実績オブジェクトを格納するためのリスト
		List<WorkResult> wrList = null;
		String year = null;
		String month = null;
		
		//セッションに登録したidを取得する
		HttpSession session = request.getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		wrList = (List<WorkResult>) session.getAttribute("WorkResult");
		
		String page = request.getParameter("page");
		if(page == null) {
			page = "not change";
		}
		System.out.println(page);
		
		if(page.equals("change")) {
			year = request.getParameter("year");
			month = request.getParameter("month");
			
			WorkResult wr = wrList.get(0);
			Date date = wr.getDate();
			String year1 = ysdf.format(date);
			String month1 = msdf.format(date);
			if(!(year1.equals(year) && month1.equals(month))) {
				//メソッドの呼び出し
				WorkResultLogic logic = new WorkResultLogic();
				wrList = logic.searchResult(staff.getId(), year, month);
				session.setAttribute("WorkResult", wrList);
			}
		}else if(page.equals("register")) {
			WorkResultLogic logic = new WorkResultLogic();
			String[] statusArray = request.getParameterValues("status");
			String[] timeStartArray = request.getParameterValues("timeStart");
			String[] timeFinishArray = request.getParameterValues("timeFinish");
			String timeStart = null;
			String timeFinish = null;
			int i = 0;
			for(WorkResult wr : wrList) {
				if((wr.getStatus() != Integer.parseInt(statusArray[i])) || 
						(wr.getTimeStart() != Integer.parseInt(timeStartArray[i])) || 
						(wr.getTimeFinish() != Integer.parseInt(timeFinishArray[i]))){
					//timeStart[i]の先頭文字のチェック
					if(timeStartArray[i].startsWith("0")) {
						timeStart = timeStartArray[i].substring(1,4);
					}else {
						timeStart = timeStartArray[i];
					}
					System.out.println("timeStart" + timeStart);
					
					//timeFinish[i]の先頭文字のチェック
					if(timeFinishArray[i].startsWith("0")) {
						timeFinish = timeFinishArray[i].substring(1,4);
					}else {
						timeFinish = timeFinishArray[i];
					}
					System.out.println("timeFinish" + timeFinish);
					
					//勤務実績の変更
					logic.updateResult(staff.getId(), wr.getDate().toString(), Integer.parseInt(statusArray[i]), 
							Integer.parseInt(timeStart), Integer.parseInt(timeFinish));
					/*
					logic.updateResult(staff.getId(), wr.getDate().toString(), Integer.parseInt(statusArray[i]), 
							Integer.parseInt(timeStartArray[i]), Integer.parseInt(timeFinishArray[i]));
							*/
				}
				i++;
			}
			//変更後の月のリストを格納
			WorkResult wr = wrList.get(0);
			year = ysdf.format(wr.getDate());
			month = msdf.format(wr.getDate());
			wrList = logic.searchResult(staff.getId(), year, month);
			session.setAttribute("WorkResult", wrList);
		}else {
			//現在の年と月を取得
			Date date = new Date();
			//年
			year = ysdf.format(date);
			//月
			month = msdf.format(date);
			
			//メソッドの呼び出し
			WorkResultLogic logic = new WorkResultLogic();
			wrList = logic.searchResult(staff.getId(), year, month);
			session.setAttribute("WorkResult", wrList);
		}
		
		//画面遷移
		request.getRequestDispatcher("/WorkRecord.jsp").forward(request, response);
		
	}

}
