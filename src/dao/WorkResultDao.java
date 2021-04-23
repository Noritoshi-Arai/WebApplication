package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import object.WorkResult;

public class WorkResultDao {
	private final String URL = "jdbc:mysql://localhost/java_architect?serverTimezone=JST";
	private String userId = "root";
	private String password = "password";
	
	//DB接続メソッド
	private Connection connection() {
		Connection con = null;
		
		//DB接続
		try {
			con = DriverManager.getConnection(URL, userId, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//勤務実績の検索
	public List<WorkResult> search(String id, String year, String month){
		//格納するリストの定義
		List<WorkResult> wrList = new ArrayList<WorkResult>();
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		WorkResult workResult = null;
		//SQL文
		String sql = "SELECT * FROM work_result LEFT OUTER JOIN work_status ON work_result.status=work_status.status WHERE work_result.id=? AND date LIKE ?";
		
		//DB接続
		Connection con = connection();
		
		//SQL文の実行
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, generateDate(year, month));
			result = pstmt.executeQuery();
			
			//WorkResultリストに格納
			while(result.next()) {
				workResult = new WorkResult();
				workResult.setId(result.getString("id"));
				workResult.setDate(result.getDate("date"));
				workResult.setStatus(result.getInt("status"));
				workResult.setStatusName(result.getString("status_name"));
				workResult.setTimeStart(result.getInt("time_start"));
				workResult.setTimeFinish(result.getInt("time_finish"));
				
				//WorkResultオブジェクトに格納
				wrList.add(workResult);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(result != null) {
					result.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return wrList;
	}
	
	//勤務実績の更新
	public boolean update(String id, String date, int status, int timeStart, int timeFinish) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE work_result SET status=?, time_start=?, time_finish=? WHERE id=? and date=?";
		
		//DB接続
		Connection con = connection();
		
		//SQL文の実行
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setInt(2, timeStart);
			pstmt.setInt(3, timeFinish);
			pstmt.setString(4, id);
			pstmt.setString(5, date);
			pstmt.executeUpdate();
			result = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//勤務実績の未反映チェック
	public List<Date> checkDate(String id, String year, String month, String day) {
		List<Date> nullList = new ArrayList<Date>();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		//SQL文
		String sql = "SELECT date FROM work_result WHERE id=? AND date BETWEEN ? AND ? AND status is null";
		
		//DB接続
		Connection con = connection();
		
		//SQL文の実行
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, year + "-" + month + "-" + "01");
			pstmt.setString(3, year + "-" + month + "-" + day);
			result = pstmt.executeQuery();
			
			while(result.next()) {
				nullList.add(result.getDate("date"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(result != null) {
					result.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return nullList;
		
	}
	
	
	//Date型文字列の生成メソッド
	public String generateDate(String year, String month) {
		return year + "-" + month + "-%";
	}
}
