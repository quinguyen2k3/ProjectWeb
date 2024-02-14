package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Sanpham;

public class ProductsMapper implements RowMapper<Sanpham> {
	@Override
	public Sanpham mapRow(ResultSet rs) {
		try {
			Sanpham sanpham = new Sanpham();
			sanpham.setMasp(rs.getInt("MaSP"));
			sanpham.setTensp(rs.getString("TenSP"));
			sanpham.setHinhsp(rs.getString("HinhSP"));
			sanpham.setGiasp(rs.getDouble("GiaSP"));
			sanpham.setMotasp(rs.getString("MotaSP"));
			sanpham.setSoluong(rs.getInt("SoLuong"));
			return sanpham;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
