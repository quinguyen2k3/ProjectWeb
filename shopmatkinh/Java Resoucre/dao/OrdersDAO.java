package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

import entity.Cart;
import entity.Donhang;
import entity.Item;
import entity.Nguoidung;
import mapper.OrdersMapper;

public class OrdersDAO extends AbstractDAO<Donhang> {
	
	public void checkOut(Cart cart, Nguoidung user, String thanhtoan, String mathe) {

		String sql1 = "INSERT INTO donhang(Ngay, MaKH, ThanhToan, MaThe, SoLuong, TongTien, TrangThai) "
				+ "VALUES (?,?,?,?,?,?,?)";
		LocalDateTime currentDate = LocalDateTime.now();
		String date = currentDate.toString();

		Long orderid = insert(sql1, date, user.getId(), thanhtoan, mathe, cart.getSize(), cart.getTotalMoney(), "processing");

		String sql2 = "INSERT INTO chitietdonhang (MaDH, MaSP, LuongMua, Gia)\n"
				+ "VALUES (?,?,?,?)";

		for(Item i : cart.getItems()) {
			insert(sql2, orderid, i.getSanpham().getMasp(), i.getLuongMua(), i.getGia());
		}

	}
	
	public void updateStatus(int oid, String trangthai) {
		String sql = "UPDATE donhang SET TrangThai = ? WHERE MaDH = ?";
		update(sql, trangthai , oid);
	}
	
	public List<Donhang> getOrders(){
		String sql = "SELECT * \n"
				+ "FROM donhang dh\n";
		return query (sql, new OrdersMapper());
		
	}
	
	public List<Donhang> getOrdersById(int id){
		String sql = "SELECT * FROM donhang  "
				+ "WHERE MaKH = ? "
				+ "AND Year(Ngay) = ? "
				+ "AND Month(Ngay) = ? "
				+ "AND TrangThai = ? OR TrangThai = ?";
		LocalDate myObj = LocalDate.now();
	    int month = myObj.getMonthValue();
	    int year = myObj.getYear();
		return query(sql, new OrdersMapper(), id,year,month,"processing","shipped");
	}
}
