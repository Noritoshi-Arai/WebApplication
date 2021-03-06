package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
			{
			this.doPost(request, response);
			}
	
	
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		
		//登録したセッションの削除
		HttpSession session = request.getSession();
		session.invalidate();
		
		//ログアウト画面へ遷移
		response.sendRedirect("Logout.jsp");
	}

}
