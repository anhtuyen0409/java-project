package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import model.NhaCungCapModel;
import model.PhieuNhapModel;
import model.SanPhamModel;

public class PhieuNhapService extends SQLServerService{
	public int themPhieuNhap(PhieuNhapModel pn)
	{
		try
		{
			String sql="insert into PhieuNhap values(?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, pn.getNgayNhap());
			preStatement.setInt(2, 0);
			preStatement.setInt(3, pn.getMaNV());
			preStatement.setInt(4, pn.getMaNCC());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaPhieuNhap(int maPN, String ngayNhapMoi, int maNVMoi, int MaNCCMoi){
		try
		{
			String sql="update PhieuNhap set NgayNhap=?, MaNV=?, MaNCC=? where MaPN=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, ngayNhapMoi);
			preStatement.setInt(2, maNVMoi);
			preStatement.setInt(3, MaNCCMoi);
			preStatement.setInt(4, maPN);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaPhieuNhap(int ma){
		try
		{
			String sql="update PhieuNhap set DaXoa=? where MaPN=?";
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
	public Vector<PhieuNhapModel> docToanBoPhieuNhap()
	{
		Vector<PhieuNhapModel> vec = new Vector<PhieuNhapModel>();
		try
		{
			String sql="select * from PhieuNhap where DaXoa=0";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				PhieuNhapModel pn = new PhieuNhapModel();
				pn.setMaPN(result.getInt(1));
				pn.setNgayNhap(result.getString(2));
				pn.setDaXoa(0);
				pn.setMaNV(result.getInt(4));
				pn.setMaNCC(result.getInt(5));
				vec.add(pn);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<PhieuNhapModel> docPhieuNhapTheoNhanVien(int maNV)
	{
		Vector<PhieuNhapModel>dspn = new Vector<PhieuNhapModel>();
		try
		{
			String sql="select * from PhieuNhap where MaNV=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maNV);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				PhieuNhapModel pn = new PhieuNhapModel();
				pn.setMaPN(result.getInt(1));
				pn.setNgayNhap(result.getString(2));
				pn.setDaXoa(0);
				pn.setMaNV(result.getInt(4));
				pn.setMaNCC(result.getInt(5));
				dspn.add(pn);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dspn;
	}
	public Vector<PhieuNhapModel> docPhieuNhapTheoNhaCungCap(int maNCC)
	{
		Vector<PhieuNhapModel>dspn = new Vector<PhieuNhapModel>();
		try
		{
			String sql="select * from PhieuNhap where MaNCC=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maNCC);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				PhieuNhapModel pn = new PhieuNhapModel();
				pn.setMaPN(result.getInt(1));
				pn.setNgayNhap(result.getString(2));
				pn.setDaXoa(0);
				pn.setMaNV(result.getInt(4));
				pn.setMaNCC(result.getInt(5));
				dspn.add(pn);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dspn;
	}
}
