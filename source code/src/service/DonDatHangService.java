package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import model.DonDatHangModel;
import model.KhachHangModel;
import model.PhieuNhapModel;

public class DonDatHangService extends SQLServerService{
	public int themDonDatHang(DonDatHangModel ddh)
	{
		try
		{
			String sql="insert into DonDatHang values(?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, ddh.getNgayDat());
			preStatement.setString(2, ddh.getNgayGiao());
			preStatement.setInt(3, 0);
			preStatement.setInt(4, ddh.getMaKH());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaDonDatHang(int maDDH, String ngayDatMoi, String ngayGiaoMoi, int MaKHMoi){
		try
		{
			String sql="update DonDatHang set NgayDat=?, NgayGiao=?, MaKH=? where MaDDH=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, ngayDatMoi);
			preStatement.setString(2, ngayGiaoMoi);
			preStatement.setInt(3, MaKHMoi);
			preStatement.setInt(4, maDDH);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaDonDatHang(int ma){
		try
		{
			String sql="update DonDatHang set DaXoa=? where MaDDH=?";
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
	public Vector<DonDatHangModel> docToanBoDonDatHang()
	{
		Vector<DonDatHangModel> vec = new Vector<DonDatHangModel>();
		try
		{
			String sql="select * from DonDatHang where DaXoa=0";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while(result.next())
			{
				DonDatHangModel ddh = new DonDatHangModel();
				ddh.setMaDDH(result.getInt(1));
				ddh.setNgayDat(result.getString(2));
				ddh.setNgayGiao(result.getString(3));
				ddh.setDaXoa(0);
				ddh.setMaKH(result.getInt(5));
				vec.add(ddh);
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return vec;
	}
	public Vector<DonDatHangModel> docDonDatHangTheoMaKhachHang(int maKH)
	{
		Vector<DonDatHangModel>dsddh = new Vector<DonDatHangModel>();
		try
		{
			String sql="select * from DonDatHang where MaKH=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maKH);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				DonDatHangModel ddh = new DonDatHangModel();
				ddh.setMaDDH(result.getInt(1));
				ddh.setNgayDat(result.getString(2));
				ddh.setNgayGiao(result.getString(3));
				ddh.setDaXoa(0);
				ddh.setMaKH(result.getInt(5));
				dsddh.add(ddh);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsddh;
	}
}
