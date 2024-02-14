package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBConnection;
import entity.Nguoidung;

public class UsersDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	public boolean checkEmail(String email) {
		String query = "select Email from nguoidung\n"
				+"WHERE Email = ?\n";
		try {
			 conn = new DBConnection().getConnection();
			 ps = conn.prepareStatement(query);
			 ps.setString(1, email);
			 rs = ps.executeQuery();
			 int count = 0;
			 while(rs.next()) {
				 count++;
			 }
			 if(count >= 1)
				 return false;
			 conn.close();
		}catch(Exception e){
			e.getStackTrace();
		}
		return true;
	}
	
	public boolean checkPassword(String email, String password) {
		String sql = "SELECT Matkhau FROM Nguoidung WHERE Email = ?";
		String t_pwd = "";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while(rs.next()) {
				t_pwd = rs.getString(1);
			}
			conn.close();
			if(password.equals(t_pwd)) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Nguoidung checkAccount(String email, String matkhau) {
		String query = "SELECT * FROM nguoidung \n"+
						"WHERE Email = ? AND Matkhau = ?";
		try {
			 conn = new DBConnection().getConnection();
			 ps = conn.prepareStatement(query);
			 ps.setString(1, email);
			 ps.setString(2, matkhau);
			 rs = ps.executeQuery();
			 while(rs.next()) {
				 return new Nguoidung(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
			 }
			 conn.close();
		}catch(Exception e){
			e.getStackTrace();
		}
		return null;
	}
	
	public void signUp (String email, String matkhau) {
		String query = "INSERT INTO nguoidung (Email, Matkhau, isAdmin) VALUES (?,?,0)";
		try {
			 conn = new DBConnection().getConnection();
			 ps = conn.prepareStatement(query);
			 ps.setString(1, email);
			 ps.setString(2, matkhau);
			 ps.executeUpdate();
			 conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateInforCus(String ten, String dienthoai, String diachi, String email)  {
		String sql = "UPDATE nguoidung\n"
				+ "SET TenKH = ?, DienThoai = ?, DiaChi = ?\n"
				+ "WHERE Email = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ten);
			ps.setString(2, dienthoai);
			ps.setString(3, diachi);
			ps.setString(4, email);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updatePassword(String email, String password) {
		String sql = "UPDATE nguoidung SET MatKhau = ? WHERE Email = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, email);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getAllCustomerNameOfOrder(){
		String sql = "SELECT TenKH \n"
				+ "FROM donhang dh, nguoidung nd\n"
				+"WHERE dh.MaKH = nd.ID";
		List<String> list = new ArrayList<>();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			conn.close();
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		UsersDAO usersdao = new UsersDAO();
		usersdao.updatePassword("nguyenddqui@gmail.com", "0931368945");
	}
}
