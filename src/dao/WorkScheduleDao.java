package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import object.WorkSchedule;

public class WorkScheduleDao {
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
	
	//出勤予定の検索
	public List<WorkSchedule> searchSt(String id, String year, String month) {
		//格納するリストの定義
		List<WorkSchedule> wsList = new ArrayList<WorkSchedule>();
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		WorkSchedule workSchedule = null;
		//SQL文
		String sql = "SELECT * FROM work_schedule LEFT OUTER JOIN work_status ON work_schedule.status=work_status.status WHERE work_schedule.id=? AND date LIKE ?";
		
		//DB接続
		Connection con = connection();
		
		//SQL文の実行
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, generateDate(year, month));
			result = pstmt.executeQuery();
			
			//WorkScheduleリストに格納
			while(result.next()) {
				//検索値をWorkScheduleオブジェクトに格納
				workSchedule = new WorkSchedule();
				workSchedule.setId(result.getString("id"));
				workSchedule.setDate(result.getDate("date"));
				workSchedule.setStatus(result.getInt("status"));
				workSchedule.setStatusName(result.getString("status_name"));
				
				//WorkScheduleオブジェクトをリストに格納
				wsList.add(workSchedule);
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
		return wsList;
	}
	
	//出勤予定検索機能用メソッド
	public List<WorkSchedule> searchNa(String nameKanji, String nameKana, String startDate, String finishDate){
		//格納するリストの定義
		List<WorkSchedule> list = new ArrayList<WorkSchedule>();
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		WorkSchedule ws = null;
		
		//SQL文
		String sql = "Select date, name_kanji, name_kana, status_name FROM work_schedule "
						+ "LEFT OUTER JOIN staff ON work_schedule.id=staff.id "
						+ "LEFT OUTER JOIN work_status ON work_schedule.status=work_status.status "
						+ "WHERE name_kanji LIKE ? AND name_kana LIKE ? AND date BETWEEN ? AND ?";
		
		//DB接続
		Connection con = connection();
		
		//SQL文の実行
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + nameKanji + "%");
			pstmt.setString(2, "%" + nameKana + "%");
			pstmt.setString(3, startDate);
			pstmt.setString(4, finishDate);
			result = pstmt.executeQuery();
			
			while(result.next()) {
				//検索値をWorkScheduleオブジェクトに格納
				ws = new WorkSchedule();
				ws.setDate(result.getDate("date"));
				ws.setNameKanji(result.getString("name_kanji"));
				ws.setNameKana(result.getString("name_kana"));
				ws.setStatusName(result.getString("status_name"));
				
				//WorkScheduleオブジェクトをリストに格納
				list.add(ws);
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
		return list;
		
	}
	
	//出勤予定の更新
	public boolean update(String id, String date, int status) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE work_schedule SET status=? WHERE id=? and date=?";
		
		//DB接続
		Connection con = connection();
		
		//SQL文の実行
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setString(2, id);
			pstmt.setString(3, date);
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
	
	//Toppageメッセージ出力用メソッド(有休)
	public Date searchRest(String id, String year, String month) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Date date = null;
		//SQL文
		String sql = "SELECT date FROM work_schedule WHERE id=? and date LIKE ? AND status=5";
			
		//DB接続
		Connection con = connection();
			
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, generateDate(year, month));
			result = pstmt.executeQuery();
				
			//格納
			if(result.next()) {
				date = result.getDate("date");
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
		return date;
	}
	
	//Toppageメッセージ出力用メソッド(未反映)
	public Date searchNull(String id, String year, String month) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Date date = null;
		//SQL文
		String sql = "SELECT date FROM work_schedule WHERE id=? and date LIKE ? AND status is null";
			
		//DB接続
		Connection con = connection();
			
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, generateDate(year, month));
			result = pstmt.executeQuery();
				
			//格納
			if(result.next()) {
				date = result.getDate("date");
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
		return date;
	}
	
	//Date型文字列の生成メソッド
	public String generateDate(String year, String month) {
		return year + "-" + month + "-%";
	}

}
