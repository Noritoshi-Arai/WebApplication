package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import object.Rest;
import java.util.Date;

public class RestDao {
	//DB接続用変数
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
	
	//DB検索メソッド
	public Rest search(String id, int year) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Rest rest = null;
		//SQL文
		String sql = "SELECT * FROM rest WHERE id = ? AND year = ?";
		
		//DB接続
		Connection con = connection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, year);
			result = pstmt.executeQuery();
			
			//Restオブジェクトに格納
			if(result.next()) {
				rest = new Rest();
				rest.setId(result.getString("id"));
				rest.setYear(result.getInt("year"));
				rest.setPossibleRest(result.getInt("possible_rest"));
				rest.setMustRest(result.getInt("must_rest"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(result != null) {
					result.close();
				}
				if(pstmt != null) {
					result.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return rest;
	}
	
	//Date型文字列の生成メソッド
		public String generateDate(String year, String month) {
			return year + "-" + month + "-%";
		}

}
