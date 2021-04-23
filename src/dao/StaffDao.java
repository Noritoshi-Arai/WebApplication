package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import object.Staff;

public class StaffDao {
	private final String URL = "jdbc:mysql://localhost/java_architect?serverTimezone=JST";
	private String userId = "root";
	private String password = "password";
	
	//DB�ڑ����\�b�h
	private Connection connection() {
		Connection con = null;
		
		//DB�ڑ�
		try {
			con = DriverManager.getConnection(URL, userId, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//�������\�b�h
	public Staff search(String id, String password) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Staff staff = null;
		//SQL��
		String sql = "SELECT * FROM staff WHERE id = ? AND password = ?";
		
		//DB�ڑ�
		Connection con = connection();
		
		//SQL���̎��s
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			result = pstmt.executeQuery();
			
			//Staff�I�u�W�F�N�g�Ɋi�[
			if(result.next()) {
				staff = new Staff();
				staff.setId(result.getString("id"));
				staff.setNameKanji(result.getString("name_kanji"));
				staff.setNameKana(result.getString("name_kana"));
				staff.setPassword(result.getString("password"));
				staff.setFlag(result.getBoolean("flag"));
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
		return staff;
		
	}

}
