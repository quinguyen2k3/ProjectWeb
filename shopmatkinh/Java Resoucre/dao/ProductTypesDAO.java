package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBConnection;
import entity.Loaisp;

public class ProductTypesDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<Loaisp> getType (){
		List<Loaisp> list = new ArrayList<>();
		String query = "select * from loaisp";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Loaisp(rs.getInt(1),rs.getString(2)));
			}
			conn.close();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}
}
