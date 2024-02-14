package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import context.DBConnection;
import dao.impl.IProductsDAO;
import entity.Loaisp;
import entity.Sanpham;
import mapper.ProductsMapper;

public class ProductsDAO extends AbstractDAO<Sanpham> implements IProductsDAO{
	
	
	public Sanpham getProductbyId(int productId) {
		String query = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong, s.MaLoai, TenLoai\n"
				+ "FROM sanpham s, loaisp l\n"
				+ "WHERE MaSP = ? AND s.MaLoai = l.MaLoai";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Loaisp loaisp = new Loaisp(rs.getInt(7),rs.getString(8));
				return new Sanpham(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getInt(6),
						loaisp);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getTotalItems(Long category, String action, Map<String, String> conditions) {
		String sql = "SELECT COUNT(*) FROM sanpham";
		String where = "";
		String from = "";
		if(action != null || !action.isEmpty()) {
			for(String condition : conditions.keySet()) {
				String value = conditions.get(condition);
				if(value.isEmpty() == false) {
					switch (condition){
					     case "name":
					         where += (where.isEmpty()) ? "TenSP LIKE '%"+value+"%'" : " AND TenSP LIKE '%"+value+"%'";
					         break;
					     case "min":
					    	 String min = conditions.get("min");
					    	 String max = conditions.get("max");
					    	 where += (where.isEmpty()) ? "GiaSP BETWEEN " +min+ " AND " + max : " AND GiaSP BETWEEN " +min+ " AND " + max;
					         break;
					     case"loai":
					    	 from += ", loaisp";
					    	 where += (where.isEmpty()) ? "loaisp.MaLoai = sanpham.MaLoai AND TenLoai = '"+value+"'" : " AND loaisp.MaLoai = sanpham.MaLoai AND TenLoai = '"+value+"'";
					    	 break;
					}
				}
			}
		}
		if(!where.isEmpty()) {
			if(conditions.get("loai") != null) {
				sql += from + " WHERE " + where;
			}else
				sql += " WHERE " + where;
				
		}else {
			if(category != null)
				sql += "\n MaLoai = "+category;		
		}
		return count(sql);
	}
	
	public List<Sanpham> filterProduct(Map<String, String> conditions , int index) {
		String where = "";
		String orderby = "";

		for(String condition : conditions.keySet()) {
			String value = conditions.get(condition);
			if(value.isEmpty() == false) {
				switch (condition){
				   	case "thutu":
				    	 orderby += (value.equals("1")) ? "ORDER BY GiaSP ASC" : "ORDER BY GiaSP DESC";
				         break;
				     case "name":
				         where += (where.isEmpty()) ? "TenSP LIKE '%"+value+"%'" : " AND TenSP LIKE '%"+value+"%'";
				         break;
				     case "min":
				    	 String min = conditions.get("min");
				    	 String max = conditions.get("max");
				    	 where += (where.isEmpty()) ? "GiaSP BETWEEN " +min+ " AND " + max : "AND GiaSP BETWEEN " +min+ " AND " + max;
				         break;
				     case"loai":
				    	 where += (where.isEmpty()) ?"MaLoai = " + value  : " AND MaLoai = " + value;
				    	 break;
				}
			}
		}
		
		String query = "";
		
		if(where.isEmpty()) {
			if(orderby.isEmpty()) {
				query  = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong\n"
						+ "FROM sanpham \n"
						+ "LIMIT ?, 6";
			}else {
				query  = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong\n"
						+ "FROM sanpham \n"
						+ orderby + "\n"
						+ "LIMIT ?, 6";
			}
		}else {
			if(orderby.isEmpty()) {
				query  = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong\n"
						+ "FROM sanpham \n"
						+ "WHERE " + where +"\n"
						+ "LIMIT ?, 6";
			}else {
				query  = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong\n"
						+ "FROM sanpham \n"
						+ "WHERE " + where +"\n"
						+ orderby + "\n"
						+ "LIMIT ?, 6";
			}
		}
		
		List<Sanpham> list = new ArrayList<>();
		
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, (index-1)*6);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Sanpham(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getInt(6)));
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateSanpham(int masp, String tensp, String motasp, double giasp, Object myfile, int soluong, int loaisp) {
		String hinhsp = (String) myfile;
		String query_1 = "UPDATE sanpham\r\n"
				+ "SET TenSP = ?, MotaSP = ?, GiaSP = ?, HinhSP = ?, SoLuong = ?, MaLoai = ?\r\n"
				+ "WHERE MaSP = ?";
		String query_2 = "UPDATE sanpham\r\n"
				+ "SET TenSP = ?, MotaSP = ?, GiaSP = ?, SoLuong = ?,MaLoai = ?\r\n"
				+ "WHERE MaSP = ?";
		try {
			conn = new DBConnection().getConnection();
			if(hinhsp != null){
				ps = conn.prepareStatement(query_1);
				ps.setString(1, tensp);
				ps.setString(2, motasp);
				ps.setDouble(3, giasp);
				ps.setString(4, hinhsp);
				ps.setInt(5, loaisp);
				ps.setInt(6, loaisp);
				ps.setInt(7, masp);
				ps.executeUpdate();
			}else {
				ps = conn.prepareStatement(query_2);
				ps.setString(1, tensp);
				ps.setString(2, motasp);
				ps.setDouble(3, giasp);
				ps.setInt(4, soluong);
				ps.setInt(5, loaisp);
				ps.setInt(6, masp);
				ps.executeUpdate();
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteSanpham(int masp) {
		String query = "DELETE FROM sanpham WHERE MaSP = ?";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, masp);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void  insertProduct(String tensp, String motasp, double giasp, String hinhsp, int soluong, int loaisp) {
		String query = "INSERT INTO sanpham\n"+
						"(TenSP, MotaSP, GiaSP, HinhSP, SoLuong, MaLoai)\n"+
						"VALUES (?,?,?,?,?,?)";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, tensp);
			ps.setString(2, motasp);
			ps.setDouble(3, giasp);
			ps.setString(4,hinhsp);
			ps.setInt(5, soluong);
			ps.setInt(6, loaisp);
			ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<Sanpham> getAllProduct(){
		List<Sanpham> list = new ArrayList<>();
		String query = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong	FROM sanpham";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Sanpham(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getDouble(4),
						rs.getString(5),
						rs.getInt(6)));
			}
			conn.close();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return list;
	}	
	public List<Sanpham> findAll(int offset, int limit) {
		String sql = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong\n"
				+ "FROM sanpham\n"
				+ "LIMIT ?, ?";
		return query(sql,new ProductsMapper(),offset,limit);
	}
	
	public List<Sanpham> findAll(){
		String sql = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong\n"
				+ "FROM sanpham\n";
		return query(sql, new ProductsMapper());
	}
	
	public Sanpham findOne(Long id) {
		String sql = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong, s.MaLoai, TenLoai\n"
				+ "FROM sanpham s, loaisp l\n"
				+ "WHERE MaSP = ? AND s.MaLoai = l.MaLoai";
		List<Sanpham> sanpham = query(sql, new ProductsMapper(), id);
		return sanpham.isEmpty() ? null : sanpham.get(0);
		
	}
	
	public List<Sanpham> findByCategoryId(Long id, Integer offset, Integer limit) {
		String sql = "SELECT MaSP, TenSP, MotaSP, GiaSP, HinhSP, SoLuong FROM sanpham WHERE MaLoai = ? \n";
		if(offset != null) {
			sql += "LIMIT ?, ?";
		}
		return query (sql, new ProductsMapper(), id , offset, limit);
	}
	public static void main(String[] args) {
		ProductsDAO dao = new ProductsDAO();
		System.out.println(dao.getProductbyId(1));
	}
}
