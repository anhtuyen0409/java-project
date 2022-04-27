package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.ChiTietDonDatHangModel;
import model.ChiTietPhieuNhapModel;

public class ChiTietDonDatHangService extends SQLServerService{
	public int themChiTietDonDatHang(ChiTietDonDatHangModel ctddh)
	{
		try
		{
			String sql="insert into ChiTietDonDatHang values(?,?,?,?,?,?,?)";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, ctddh.getMaDDH());
			preStatement.setInt(2, ctddh.getMaSP());
			preStatement.setInt(3, ctddh.getSoLuong());
			preStatement.setInt(4, ctddh.getUuDai());
			preStatement.setInt(5, ctddh.getThanhTien());
			preStatement.setInt(6, 0);
			preStatement.setInt(7, ctddh.getTongThanhToan());
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int suaChiTietDonDatHang(int maCTDDH, int maDDHMoi, int MaSPMoi, int soLuongMoi){
		try
		{
			String sql="update ChiTietDonDatHang set MaDDH=?, MaSP=?, SoLuong=? where MaCTDDH=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maDDHMoi);
			preStatement.setInt(2, MaSPMoi);
			preStatement.setInt(3, soLuongMoi);
			preStatement.setInt(4, maCTDDH);
			return preStatement.executeUpdate();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return -1;
	}
	public int xoaChiTietDonDatHang(int ma){
		try
		{
			String sql="update ChiTietDonDatHang set DaXoa=? where MaCTDDH=?";
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
	public Vector<ChiTietDonDatHangModel> docCTDDHTheoMaDDH(int maDDH)
	{
		Vector<ChiTietDonDatHangModel>dsCTDDH = new Vector<ChiTietDonDatHangModel>();
		try
		{
			String sql="select * from ChiTietDonDatHang where MaDDH=? and DaXoa=0";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setInt(1, maDDH);
			ResultSet result=preStatement.executeQuery();
			while(result.next())
			{
				ChiTietDonDatHangModel ctddh = new ChiTietDonDatHangModel();
				ctddh.setMaCTDDH(result.getInt(1));
				ctddh.setMaDDH(result.getInt(2));
				ctddh.setMaSP(result.getInt(3));
				ctddh.setSoLuong(result.getInt(4));
				ctddh.setThanhTien(result.getInt(6));
				ctddh.setUuDai(result.getInt(5));
				ctddh.setTongThanhToan(result.getInt(8)); //phải tương tứng với cột trong database
				ctddh.setDaXoa(0);
				dsCTDDH.add(ctddh);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dsCTDDH;
	}
}
