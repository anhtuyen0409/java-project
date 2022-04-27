package model;

public class PhieuNhapModel {
	private int maPN;
	private String ngayNhap;
	private int daXoa;
	private int maNV;
	private int maNCC;
	//private NhanVienModel nhanVien;
	//private NhaCungCapModel nhaCungCap;
	/*public NhanVienModel getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVienModel nhanVien) {
		this.nhanVien = nhanVien;
	}*/
	public int getMaPN() {
		return maPN;
	}
	public void setMaPN(int maPN) {
		this.maPN = maPN;
	}
	public String getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(String ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public int getDaXoa() {
		return daXoa;
	}
	public void setDaXoa(int daXoa) {
		this.daXoa = daXoa;
	}
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public int getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(int maNCC) {
		this.maNCC = maNCC;
	}
	/*public NhaCungCapModel getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCapModel nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}*/
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return maPN+"";
	}
}
