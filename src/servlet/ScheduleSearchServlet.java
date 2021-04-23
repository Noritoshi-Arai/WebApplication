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
		//出勤予定オブジェクトを格納するためのリスト
		List<WorkSchedule> list = null;
		
		HttpSession session = request.getSession();
		
		//ScheduleSearch.jspから送られた値の取得
		String nameKanji = request.getParameter("nameKanji");	//氏名
		String nameKana = request.getParameter("nameKana");	//シメイ
		String startYear = request.getParameter("startYear");	//年（スタート）
		String startMonth = request.getParameter("startMonth");	//月（スタート）
		if(startMonth.length() == 1) {
			startMonth = "0" + startMonth;
		}
		String startDay = request.getParameter("startDay");	//日（スタート）
		if(startDay.length() == 1) {
			startDay = "0" + startDay;
		}
		String finishYear = request.getParameter("finishYear");	//年（終わり）
		String finishMonth = request.getParameter("finishMonth");	//月（終わり）
		if(finishMonth.length() == 1) {
			finishMonth = "0" + finishMonth;
		}
		String finishDay = request.getParameter("finishDay");	//日（終わり）
		if(finishDay.length() == 1) {
			finishDay = "0" + finishDay;
		}
		
		//日付（スタート）にnullがあるかチェック
		if((startYear == null || startYear.length() == 0) && (startMonth == null || startMonth.length() == 0) && (startDay == null || startDay.length() == 0)) {
			startYear = finishYear;
			startMonth = finishMonth;
			startDay = finishDay;
		}
		
		//日付（終わり）にnullはないかチェック
		if((finishYear == null || finishYear.length() == 0) && (finishMonth == null || finishMonth.length() == 0) && (finishDay == null || finishDay.length() == 0)) {
			finishYear = startYear;
			finishMonth = startMonth;
			finishDay = startDay;
		}
		
		//全ての日付に入力がない場合
		String startDate = startYear + startMonth + startDay;
		String finishDate = finishYear + finishMonth + finishDay;
		
		if((startDate == null || startDate.length() == 0) && (finishDate == null || finishDate.length() == 0)) {
			//SimpleDateFormat
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat msdf = new SimpleDateFormat("MM");
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
			
			//現在の年と月を取得
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
			
			//現在の年と月を取得
			Date date = new Date();
			startYear = finishYear = ysdf.format(date);
			startMonth = finishMonth = msdf.format(date);
			startDay = finishDay = dsdf.format(date);
		}
		*/
		
		
		//出勤予定検索メソッド（出勤予定検索機能用）の呼び出し
		WorkScheduleLogic logic = new WorkScheduleLogic();
		list = logic.searchName(nameKanji, nameKana, (startYear + startMonth + startDay), (finishYear + finishMonth + finishDay));
		session.setAttribute("searchResult", list);
		
		//画面遷移
		request.getRequestDispatcher("/ScheduleResult.jsp").forward(request, response);
		
		
	}

}
