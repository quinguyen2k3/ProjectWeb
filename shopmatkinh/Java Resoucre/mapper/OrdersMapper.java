package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Donhang;

public class OrdersMapper implements RowMapper<Donhang> {
	@Override
	public Donhang mapRow(ResultSet rs) {
		try {
			Donhang donhang = new Donhang();
			donhang.setMadh(rs.getInt("MaDH"));
			donhang.setMakh(rs.getInt("MaKH"));
			donhang.setNgay(rs.getString("Ngay"));
			donhang.setThanhtoan(rs.getString("ThanhToan"));
			donhang.setMathe(rs.getString("MaThe"));
			donhang.setSoluong(rs.getInt("SoLuong"));
			donhang.setTongtien(rs.getDouble("TongTien"));
			donhang.setTrangthai(rs.getString("TrangThai"));
			return donhang;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
