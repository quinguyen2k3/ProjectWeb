package entity;

public class Donhang {
	private int madh;
	private String ngay;
	private int makh;
	private String thanhtoan;
	private String mathe;
	private int soluong;
	private double tongtien;
	private String trangthai;
	
	
    public Donhang() {
    	
    }
	
	public Donhang(int mahd, String ngay, int makh, String thanhtoan, String mathe, int soluong, double tongtien,
			String trangthai) {
		super();
		this.madh = mahd;
		this.ngay = ngay;
		this.makh = makh;
		this.thanhtoan = thanhtoan;
		this.mathe = mathe;
		this.soluong = soluong;
		this.tongtien = tongtien;
		this.trangthai = trangthai;
	}

	public int getMadh() {
		return madh;
	}

	public void setMadh(int madh) {
		this.madh = madh;
	}

	

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public int getMakh() {
		return makh;
	}

	public void setMakh(int makh) {
		this.makh = makh;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public double getTongtien() {
		return tongtien;
	}

	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}

	public String getThanhtoan() {
		return thanhtoan;
	}

	public void setThanhtoan(String thanhtoan) {
		this.thanhtoan = thanhtoan;
	}

	public String getMathe() {
		return mathe;
	}

	public void setMathe(String mathe) {
		this.mathe = mathe;
	}

	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	@Override
	public String toString() {
		return "Hoadon [mahd=" + madh + ", ngay=" + ngay + ", makh=" + makh + ", thanhtoan=" + thanhtoan + ", mathe="
				+ mathe + ", soluong=" + soluong + ", tongtien=" + tongtien + ", trangthai=" + trangthai + "]";
	}
		
}
