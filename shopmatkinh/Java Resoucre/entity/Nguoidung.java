package entity;

public class Nguoidung {
	private int id;
	private String email;
	private String matkhau;
	private String tenkh;
	private String dienthoai;
	private String diachi;
	private int admin;

	public Nguoidung(int id, String email, String matkhau, String tenkh, String dienthoai, String diachi, int admin) {
		super();
		this.id = id;
		this.email = email;
		this.matkhau = matkhau;
		this.tenkh = tenkh;
		this.dienthoai = dienthoai;
		this.diachi = diachi;
		this.admin = admin;
	}
	
	
	public String getDienthoai() {
		return dienthoai;
	}


	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}


	public String getTenkh() {
		return tenkh;
	}

	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	@Override
	public String toString() {
		return "Nguoidung [id=" + id + ", email=" + email + ", matkhau=" + matkhau + ", tenkh=" + tenkh + ", dienthoai="
				+ dienthoai + ", diachi=" + diachi + ", admin=" + admin + "]";
	}

}
