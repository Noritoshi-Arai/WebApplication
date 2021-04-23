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
	
	//�o�Η\��̌���
	public List<WorkSchedule> searchSt(String id, String year, String month) {
		//�i�[���郊�X�g�̒�`
		List<WorkSchedule> wsList = new ArrayList<WorkSchedule>();
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		WorkSchedule workSchedule = null;
		//SQL��
		String sql = "SELECT * FROM work_schedule LEFT OUTER JOIN work_status ON work_schedule.status=work_status.status WHERE work_schedule.id=? AND date LIKE ?";
		
		//DB�ڑ�
		Connection con = connection();
		
		//SQL���̎��s
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, generateDate(year, month));
			result = pstmt.executeQuery();
			
			//WorkSchedule���X�g�Ɋi�[
			while(result.next()) {
				//�����l��WorkSchedule�I�u�W�F�N�g�Ɋi�[
				workSchedule = new WorkSchedule();
				workSchedule.setId(result.getString("id"));
				workSchedule.setDate(result.getDate("date"));
				workSchedule.setStatus(result.getInt("status"));
				workSchedule.setStatusName(result.getString("status_name"));
				
				//WorkSchedule�I�u�W�F�N�g�����X�g�Ɋi�[
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
	
	//�o�Η\�茟���@�\�p���\�b�h
	public List<WorkSchedule> searchNa(String nameKanji, String nameKana, String startDate, String finishDate){
		//�i�[���郊�X�g�̒�`
		List<WorkSchedule> list = new ArrayList<WorkSchedule>();
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		WorkSchedule ws = null;
		
		//SQL��
		String sql = "Select date, name_kanji, name_kana, status_name FROM work_schedule "
						+ "LEFT OUTER JOIN staff ON work_schedule.id=staff.id "
						+ "LEFT OUTER JOIN work_status ON work_schedule.status=work_status.status "
						+ "WHERE name_kanji LIKE ? AND name_kana LIKE ? AND date BETWEEN ? AND ?";
		
		//DB�ڑ�
		Connection con = connection();
		
		//SQL���̎��s
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + nameKanji + "%");
			pstmt.setString(2, "%" + nameKana + "%");
			pstmt.setString(3, startDate);
			pstmt.setString(4, finishDate);
			result = pstmt.executeQuery();
			
			while(result.next()) {
				//�����l��WorkSchedule�I�u�W�F�N�g�Ɋi�[
				ws = new WorkSchedule();
				ws.setDate(result.getDate("date"));
				ws.setNameKanji(result.getString("name_kanji"));
				ws.setNameKana(result.getString("name_kana"));
				ws.setStatusName(result.getString("status_name"));
				
				//WorkSchedule�I�u�W�F�N�g�����X�g�Ɋi�[
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
	
	//�o�Η\��̍X�V
	public boolean update(String id, String date, int status) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "UPDATE work_schedule SET status=? WHERE id=? and date=?";
		
		//DB�ڑ�
		Connection con = connection();
		
		//SQL���̎��s
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
	
	//Toppage���b�Z�[�W�o�͗p���\�b�h(�L�x)
	public Date searchRest(String id, String year, String month) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Date date = null;
		//SQL��
		String sql = "SELECT date FROM work_schedule WHERE id=? and date LIKE ? AND status=5";
			
		//DB�ڑ�
		Connection con = connection();
			
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, generateDate(year, month));
			result = pstmt.executeQuery();
				
			//�i�[
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
	
	//Toppage���b�Z�[�W�o�͗p���\�b�h(�����f)
	public Date searchNull(String id, String year, String month) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Date date = null;
		//SQL��
		String sql = "SELECT date FROM work_schedule WHERE id=? and date LIKE ? AND status is null";
			
		//DB�ڑ�
		Connection con = connection();
			
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, generateDate(year, month));
			result = pstmt.executeQuery();
				
			//�i�[
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
	
	//Date�^������̐������\�b�h
	public String generateDate(String year, String month) {
		return year + "-" + month + "-%";
	}

}
