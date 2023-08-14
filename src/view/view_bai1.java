package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.JDBCUtil;

public class view_bai1 {
	public static void main(String[] args) {
		int index = 1;
		try {
			Connection cns = JDBCUtil.getConnection();
			Statement st = cns.createStatement();
			String sql = "select * from students";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String masv = rs.getString("masv");
				String hoten = rs.getString("hoten");
				String email = rs.getString("email");
				String sdt = rs.getString("sdt");
				Boolean gt = rs.getBoolean("gioitinh");
				String diachi = rs.getString("diachi");
				System.out.println("Students: "+index+" MaSV: " + masv + " HoTen: " + hoten + " Email: " + email + " SDT: " + sdt
						+ " GTinh: " + gt + " DC: " + diachi);
				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
