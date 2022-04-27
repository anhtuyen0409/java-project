package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import model.NhanVienModel;
import model.SanPhamModel;


public class NhanVienService extends SQLServerService{
	public int themNhanVien(NhanVienModel nv)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String sql="insert into NhanVien values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, nv.getTenNV());
			preStatement.setString(2, nv.getNamSinh());
			preStatement.setString(3, nv.getDiaChi());
			preStatement.setString(4, nv.getSDT());
			preStatement.setString(5, nv.getEmail());
			preStatement.setString(6, nv.getNgayVaoLamViec());
			preStatement.setInt(7, 0);
			preStatement.setInt(8, nv.getMaLoaiNV());
			preStatement.setString(9, nv.getTenDangNhap());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaNhanVien(int ma, String tenMoi, String namSinhMoi, String diaChiMoi, String SDTMoi, String emailMoi, String ngayVaoLamViecMoi){
		try
		{
			String sql="update NhanVien set TenNV=?, NamSinh=?, DiaChi=?, SDT=?, Email=?, NgayVaoLamViec=? where MaNV=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenMoi);
			preStatement.setString(2, namSinhMoi);
			preStatement.setString(3, diaChiMoi);
			preStatement.setString(4, SDTMoi);
			preStatement.setString(5, emailMoi);
			preStatement.setString(6, ngayVaoLamViecMoi);
			preStatement.setInt(7, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaNhanVien(int ma){
		try
		{
			String sql="update NhanVien set DaXoa=? where MaNV=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, 1);
			preStatement.setInt(2, ma);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public Vector<NhanVienModel>timNhanVienTheoTen(String tenNV)
	{
		Vector<NhanVienModel> vecnv = new Vector<NhanVienModel>();
		try
		{
			String sql="select * from NhanVien where TenNV=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenNV);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				NhanVienModel nv = new NhanVienModel();
				nv.setMaNV(result.getInt(1));
				nv.setTenNV(result.getString(2));
				nv.setNamSinh(result.getString(3));
				nv.setDiaChi(result.getString(4));
				nv.setSDT(result.getString(5));
				nv.setEmail(result.getString(6));
				nv.setNgayVaoLamViec(result.getString(7));
				//bảng có cột nào thì thao tác cột tương ứng trong database
				nv.setMaLoaiNV(result.getInt(9));
				nv.setTenDangNhap(result.getString(10));
				vecnv.add(nv);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vecnv; //trả về danh sách tìm kiếm
	}
	public Vector<NhanVienModel> docToanBoNhanVien()
	{
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Vector<NhanVienModel>dsNV = new Vector<NhanVienModel>();
		try
		{
			String sql="select * from NhanVien where DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				NhanVienModel nv = new NhanVienModel();
				nv.setMaNV(result.getInt(1));
				nv.setTenNV(result.getString(2));
				nv.setNamSinh(result.getString(3));
				nv.setDiaChi(result.getString(4));
				nv.setSDT(result.getString(5));
				nv.setEmail(result.getString(6));
				nv.setNgayVaoLamViec(result.getString(7));
				nv.setDaXoa(0);
				nv.setMaLoaiNV(result.getInt(9));
				nv.setTenDangNhap(result.getString(10));
				dsNV.add(nv);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsNV;
	}
	public Vector<NhanVienModel> docNhanVienTheoLoai(int maLoai)
	{
		Vector<NhanVienModel>dsNV = new Vector<NhanVienModel>();
		try
		{
			String sql="select * from NhanVien where MaLoaiNV=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maLoai);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				NhanVienModel nv = new NhanVienModel();
				nv.setMaNV(result.getInt(1));
				nv.setTenNV(result.getString(2));
				nv.setNamSinh(result.getString(3));
				nv.setDiaChi(result.getString(4));
				nv.setSDT(result.getString(5));
				nv.setEmail(result.getString(6));
				nv.setNgayVaoLamViec(result.getString(7));
				nv.setDaXoa(0);
				nv.setMaLoaiNV(result.getInt(9));
				nv.setTenDangNhap(result.getString(10));
				dsNV.add(nv);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsNV;
	}
	public Vector<NhanVienModel> docNhanVienTheoTenDangNhap(String tenDangNhap)
	{
		Vector<NhanVienModel>dsNV = new Vector<NhanVienModel>();
		try
		{
			String sql="select * from NhanVien where TenDangNhap=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenDangNhap);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				NhanVienModel nv = new NhanVienModel();
				nv.setMaNV(result.getInt(1));
				nv.setTenNV(result.getString(2));
				nv.setNamSinh(result.getString(3));
				nv.setDiaChi(result.getString(4));
				nv.setSDT(result.getString(5));
				nv.setEmail(result.getString(6));
				nv.setNgayVaoLamViec(result.getString(7));
				nv.setDaXoa(0);
				nv.setMaLoaiNV(result.getInt(9));
				nv.setTenDangNhap(result.getString(10));
				dsNV.add(nv);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsNV;
	}
	public Vector<NhanVienModel> docToanBoNhanVienQLKho()
	{
		Vector<NhanVienModel>dsNV = new Vector<NhanVienModel>();
		try
		{
			String sql="select * from NhanVien where MaLoaiNV=1 and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				NhanVienModel nv = new NhanVienModel();
				nv.setMaNV(result.getInt(1));
				nv.setTenNV(result.getString(2));
				nv.setNamSinh(result.getString(3));
				nv.setDiaChi(result.getString(4));
				nv.setSDT(result.getString(5));
				nv.setEmail(result.getString(6));
				nv.setNgayVaoLamViec(result.getString(7));
				nv.setDaXoa(0);
				nv.setMaLoaiNV(result.getInt(9));
				nv.setTenDangNhap(result.getString(10));
				dsNV.add(nv);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsNV;
	}
	
	/*public NhanVienModel docNhanVienTheoTenDangNhap(String tenDangNhap)
	{
		NhanVienModel nv = new NhanVienModel();
		try
		{
			String sql="select * from NhanVien where TenDangNhap=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, tenDangNhap);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				//NhanVienModel nvmd = new NhanVienModel();
				nv.setMaNV(result.getInt(1));
				nv.setTenNV(result.getString(2));
				nv.setNamSinh(result.getString(3));
				nv.setDiaChi(result.getString(4));
				nv.setSDT(result.getString(5));
				nv.setEmail(result.getString(6));
				nv.setNgayVaoLamViec(result.getString(7));
				nv.setDaXoa(0);
				nv.setMaLoaiNV(result.getInt(9));
				nv.setTenDangNhap(result.getString(10));
				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return nv;
	}*/
}
