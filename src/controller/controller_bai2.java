package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.JDBCUtil;
import view.view_bai2;

public class controller_bai2 extends JFrame implements ActionListener,MouseListener {
	private view_bai2 viewbai2;
	public int indexx = 0;

	public controller_bai2(view_bai2 viewbai2) {
		this.viewbai2 = viewbai2;
		filll();
	}
	public void filll() {
		viewbai2.model.setRowCount(0);
		try {
			Connection cns = JDBCUtil.getConnection();
			String sql = "select * from students";
			Statement st = cns.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String masv = rs.getString("masv");
				String hoten = rs.getString("hoten");
				String email = rs.getString("email");
				String sdt = rs.getString("sdt");
				Boolean gt = rs.getBoolean("gioitinh");
				String diachi = rs.getString("diachi");
				String blgtinh = "";
				if (gt==true) {
					blgtinh = "Nam";
				} else {
					blgtinh = "Nữ";
				}
				Object[] dtnew = {masv,hoten,email,sdt,blgtinh,diachi};
				viewbai2.model.addRow(dtnew);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean checknull() {
		if (viewbai2.txtmasv.getText().trim().equals("")) {
			return false;
		}
		if (viewbai2.txthoten.getText().trim().equals("")) {
			return false;
		}
		if (viewbai2.txtemail.getText().trim().equals("")) {
			return false;
		}
		if (viewbai2.txtsdt.getText().trim().equals("")) {
			return false;
		}
		if (viewbai2.txtdiachi.getText().trim().equals("")) {
			return false;
		}
		if (!viewbai2.nam.isSelected() && !viewbai2.nu.isSelected()) {
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		if (e.getSource() == viewbai2.btnsearch) {
			if (!viewbai2.txtsearch.getText().equals("")) {
				try {
					Connection cns = JDBCUtil.getConnection();
					String sql = "select * from students where masv = ?";
					PreparedStatement st = cns.prepareStatement(sql);
					st.setString(1, viewbai2.txtsearch.getText());
					ResultSet rs = st.executeQuery();
					while (rs.next()) {
						String masinhvien = rs.getString("masv");
						String hotensinhvien = rs.getString("hoten");
						String email = rs.getString("email");
						String sodienthoai = rs.getString("sdt");
						Boolean gttt = rs.getBoolean("gioitinh");
						String diachi = rs.getString("diachi");
						viewbai2.txtmasv.setText(masinhvien);
						viewbai2.txthoten.setText(hotensinhvien);
						viewbai2.txtemail.setText(email);
						viewbai2.txtsdt.setText(sodienthoai);
						if (gttt == true) {
							viewbai2.nam.setSelected(true);
						} else {
							viewbai2.nu.setSelected(true);
						}
						viewbai2.txtdiachi.setText(diachi);
						JOptionPane.showMessageDialog(this, "Tìm thấy !");
						return;
					}
					JOptionPane.showMessageDialog(this, "Không tìm thấy !");
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(this, "Không tìm thấy !");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng điền MaSV muốn tìm !");
			}
		}
		if (e.getSource() == viewbai2.btnnew) {
			viewbai2.txtmasv.setText("");
			viewbai2.txthoten.setText("");
			viewbai2.txtemail.setText("");
			viewbai2.txtsdt.setText("");
			viewbai2.txtdiachi.setText("");
			viewbai2.gr1.clearSelection();
		}
		if (e.getSource() == viewbai2.btnsave) {
			if (checknull()) {
				try {
					Connection cns = JDBCUtil.getConnection();
					String sql = "insert into students values(?,?,?,?,?,?)";
					PreparedStatement st = cns.prepareStatement(sql);
					st.setString(1, viewbai2.txtmasv.getText());
					st.setString(2, viewbai2.txthoten.getText());
					st.setString(3, viewbai2.txtemail.getText());
					st.setString(4, viewbai2.txtsdt.getText());
					Boolean blgtinh;
					if (viewbai2.nam.isSelected()) {
						blgtinh = true;
					} else {
						blgtinh = false;
					}
					st.setBoolean(5, blgtinh);
					st.setString(6, viewbai2.txtdiachi.getText());
					int kq = st.executeUpdate();
					if (kq > 0) {
						filll();
						JOptionPane.showMessageDialog(this, "Save thành công !");
					} else {
						JOptionPane.showMessageDialog(this, "Save không thành công !");
					}
				} catch (SQLException e2) {

					JOptionPane.showMessageDialog(this, "MaSV tồn tại, Save không thành công !");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin !");
			}

		}
		if (e.getSource() == viewbai2.btndelete) {
			if (checknull()) {
				try {
					Connection cns = JDBCUtil.getConnection();
					String sql = "delete from students where masv=? and hoten=? and email=? and sdt=? and gioitinh=? and diachi=?";
					PreparedStatement st = cns.prepareStatement(sql);
					st.setString(1, viewbai2.txtmasv.getText());
					st.setString(2, viewbai2.txthoten.getText());
					st.setString(3, viewbai2.txtemail.getText());
					st.setString(4, viewbai2.txtsdt.getText());
					Boolean blgtinh;
					if (viewbai2.nam.isSelected()) {
						blgtinh = true;
					} else {
						blgtinh = false;
					}
					st.setBoolean(5, blgtinh);
					st.setString(6, viewbai2.txtdiachi.getText());
					int kq = st.executeUpdate();
					if (kq > 0) {
						filll();
						JOptionPane.showMessageDialog(this, "Delete thành công !");
						viewbai2.txtmasv.setText("");
						viewbai2.txthoten.setText("");
						viewbai2.txtemail.setText("");
						viewbai2.txtsdt.setText("");
						viewbai2.txtdiachi.setText("");
						viewbai2.gr1.clearSelection();
					} else {
						JOptionPane.showMessageDialog(this, "Delete không thành công !");
					}
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(this, "Thông tin sai ! Delete không thành công !");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin của SV muốn xóa !");
			}
		}
		if (e.getSource() == viewbai2.btnupdate) {
			if (!viewbai2.txtmasv.getText().trim().equals("")) {
				try {
					Connection cns = JDBCUtil.getConnection();
					String sql = "update students set hoten=? , email=? , sdt=? , gioitinh=? , diachi=? where masv = ?";
					PreparedStatement st = cns.prepareStatement(sql);
					st.setString(1, viewbai2.txthoten.getText());
					st.setString(2, viewbai2.txtemail.getText());
					st.setString(3, viewbai2.txtsdt.getText());
					Boolean blgtinh;
					if (viewbai2.nam.isSelected()) {
						blgtinh = true;
					} else {
						blgtinh = false;
					}
					st.setBoolean(4, blgtinh);
					st.setString(5, viewbai2.txtdiachi.getText());
					st.setString(6, viewbai2.txtmasv.getText());
					int kq = st.executeUpdate();
					if (kq > 0) {
						filll();
						JOptionPane.showMessageDialog(this, "Update thành công !");
					} else {
						JOptionPane.showMessageDialog(this, "Update không thành công !");
					}

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(this, "Update không thành công , MaSV không thể thay đổi !");
				}
			} else {
				JOptionPane.showMessageDialog(this, "MaSV trống !");
			}
		}
		if (e.getSource() == viewbai2.btntien) {
			try {
				Connection cns = JDBCUtil.getConnection();
				String sql = "select count(*) from students";
				PreparedStatement st = cns.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int length = Integer.parseInt(rs.getString("count(*)"));
					indexx = viewbai2.tb.getSelectedRow() + 1;
					if (indexx == length) {
						indexx = 0;
					}
					viewbai2.txtmasv.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 0)));
					viewbai2.txthoten.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 1)));
					viewbai2.txtemail.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 2)));
					viewbai2.txtsdt.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 3)));
					String gioit = String.valueOf(viewbai2.tb.getValueAt(indexx, 4));
					if (gioit.equals("Nam")) {
						viewbai2.nam.setSelected(true);
					} else {
						viewbai2.nu.setSelected(true);
					}
					viewbai2.txtdiachi.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 5)));
					viewbai2.tb.setRowSelectionInterval(indexx, indexx);
				}
			} catch (SQLException e2) {

			}
		}
		if (e.getSource() == viewbai2.btnlui) {
			try {
				Connection cns = JDBCUtil.getConnection();
				String sql = "select count(*) from students";
				PreparedStatement st = cns.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int length = Integer.parseInt(rs.getString("count(*)"));
					indexx = viewbai2.tb.getSelectedRow() - 1;
					if (indexx < 0) {
						indexx = length - 1;
					}
					viewbai2.txtmasv.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 0)));
					viewbai2.txthoten.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 1)));
					viewbai2.txtemail.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 2)));
					viewbai2.txtsdt.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 3)));
					String gioit = String.valueOf(viewbai2.tb.getValueAt(indexx, 4));
					if (gioit.equals("Nam")) {
						viewbai2.nam.setSelected(true);
					} else {
						viewbai2.nu.setSelected(true);
					}
					viewbai2.txtdiachi.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 5)));
					viewbai2.tb.setRowSelectionInterval(indexx, indexx);
				}
			} catch (SQLException e2) {

			}

		}
		if (e.getSource() == viewbai2.btndau) {
			indexx = 0;
			viewbai2.txtmasv.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 0)));
			viewbai2.txthoten.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 1)));
			viewbai2.txtemail.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 2)));
			viewbai2.txtsdt.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 3)));
			String gioit = String.valueOf(viewbai2.tb.getValueAt(indexx, 4));
			if (gioit.equals("Nam")) {
				viewbai2.nam.setSelected(true);
			} else {
				viewbai2.nu.setSelected(true);
			}
			viewbai2.txtdiachi.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 5)));
			viewbai2.tb.setRowSelectionInterval(indexx, indexx);
		}
		if (e.getSource() == viewbai2.btncuoi) {
			try {
				Connection cns = JDBCUtil.getConnection();
				String sql = "select count(*) from students";
				PreparedStatement st = cns.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					int length = Integer.parseInt(rs.getString("count(*)"));
					indexx = length - 1;
					viewbai2.txtmasv.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 0)));
					viewbai2.txthoten.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 1)));
					viewbai2.txtemail.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 2)));
					viewbai2.txtsdt.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 3)));
					String gioit = String.valueOf(viewbai2.tb.getValueAt(indexx, 4));
					if (gioit.equals("Nam")) {
						viewbai2.nam.setSelected(true);
					} else {
						viewbai2.nu.setSelected(true);
					}
					viewbai2.txtdiachi.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 5)));
					viewbai2.tb.setRowSelectionInterval(indexx, indexx);
				}
			} catch (SQLException e2) {
			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		indexx = viewbai2.tb.getSelectedRow();
		viewbai2.txtmasv.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 0)));
		viewbai2.txthoten.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 1)));
		viewbai2.txtemail.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 2)));
		viewbai2.txtsdt.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 3)));
		String gioit = String.valueOf(viewbai2.tb.getValueAt(indexx, 4));
		if (gioit.equals("Nam")) {
			viewbai2.nam.setSelected(true);
		} else {
			viewbai2.nu.setSelected(true);
		}
		viewbai2.txtdiachi.setText(String.valueOf(viewbai2.tb.getValueAt(indexx, 5)));
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
