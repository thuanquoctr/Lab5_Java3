package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.controller_bai2;
import model.JDBCUtil;

public class view_bai2 {
	public JLabel lbltitle, lblmasv, lblhoten, lblemail, lblsodt, lblgioitinh, lbldiachi, lblsearch;
	public JTextField txtmasv, txthoten, txtemail, txtsdt, txtsearch;
	public JRadioButton nam, nu;
	public ButtonGroup gr1;
	public JTextArea txtdiachi;
	public JScrollPane diachi;
	public JButton btnsearch, btnnew, btnsave, btndelete, btnupdate, btnlui, btntien, btndau, btncuoi;
	public JTableHeader header;
	public JTable tb;
	public DefaultTableModel model;
	public JFrame frame = new JFrame();
	public int indexx = 0;
	private controller_bai2 ctlbai2;

	public view_bai2() {
		GUI();
	}

	public void GUI() {
		frame.setTitle("Quản Lí Users");
		frame.setSize(650, 750);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(64, 224, 208));
		frame.setLayout(null);
		lbltitle = new JLabel("Quản Lí Users");
		lbltitle.setFont(new Font("Serif", Font.PLAIN, 40));
		lbltitle.setBounds(200, 5, 700, 70);

		lblsearch = new JLabel("Tìm Theo Mã SV:");
		lblsearch.setForeground(Color.RED);
		lblsearch.setFont(new Font("Serif", Font.BOLD, 20));
		lblsearch.setBounds(40, 100, 160, 30);

		lblmasv = new JLabel("Mã SV:");
		lblmasv.setForeground(Color.BLUE);
		lblmasv.setFont(new Font("Serif", Font.BOLD, 20));
		lblmasv.setBounds(40, 150, 100, 30);

		lblhoten = new JLabel("Họ Tên:");
		lblhoten.setForeground(Color.BLUE);
		lblhoten.setFont(new Font("Serif", Font.BOLD, 20));
		lblhoten.setBounds(40, 200, 100, 30);

		lblemail = new JLabel("Email:");
		lblemail.setForeground(Color.BLUE);
		lblemail.setFont(new Font("Serif", Font.BOLD, 20));
		lblemail.setBounds(40, 250, 100, 30);

		lblsodt = new JLabel("Số ĐT:");
		lblsodt.setForeground(Color.BLUE);
		lblsodt.setFont(new Font("Serif", Font.BOLD, 20));
		lblsodt.setBounds(40, 300, 100, 30);

		lblgioitinh = new JLabel("Giới Tính:");
		lblgioitinh.setForeground(Color.BLUE);
		lblgioitinh.setFont(new Font("Serif", Font.BOLD, 20));
		lblgioitinh.setBounds(40, 350, 100, 30);

		lbldiachi = new JLabel("Địa Chỉ:");
		lbldiachi.setForeground(Color.BLUE);
		lbldiachi.setFont(new Font("Serif", Font.BOLD, 20));
		lbldiachi.setBounds(40, 400, 100, 30);

		txtsearch = new JTextField();
		txtsearch.setPreferredSize(new Dimension(250, 35));
		txtsearch.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		txtsearch.setBounds(220, 97, 185, 40);

		txtmasv = new JTextField();
		txtmasv.setPreferredSize(new Dimension(250, 35));
		txtmasv.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		txtmasv.setBounds(150, 147, 255, 40);

		txthoten = new JTextField();
		txthoten.setPreferredSize(new Dimension(250, 35));
		txthoten.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		txthoten.setBounds(150, 197, 255, 40);

		txtemail = new JTextField();
		txtemail.setPreferredSize(new Dimension(250, 35));
		txtemail.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		txtemail.setBounds(150, 247, 255, 40);

		txtsdt = new JTextField();
		txtsdt.setPreferredSize(new Dimension(250, 35));
		txtsdt.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		txtsdt.setBounds(150, 297, 255, 40);

		nam = new JRadioButton("Nam");
		nam.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
		nam.setOpaque(false);
		nam.setBounds(160, 345, 80, 40);

		nu = new JRadioButton("Nữ");
		nu.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
		nu.setOpaque(false);
		nu.setBounds(250, 345, 80, 40);

		gr1 = new ButtonGroup();
		gr1.add(nam);
		gr1.add(nu);

		txtdiachi = new JTextArea(10, 0);
		txtdiachi.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		txtdiachi.setLineWrap(true);
		diachi = new JScrollPane(txtdiachi);
		diachi.setBounds(150, 385, 435, 120);

		ImageIcon icsearch = new ImageIcon(
				new ImageIcon("D:\\searchlab5.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		btnsearch = new JButton("Search", icsearch);
		btnsearch.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		btnsearch.setBounds(450, 97, 132, 40);

		ImageIcon icnew = new ImageIcon(
				new ImageIcon("D:\\newlab5.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		btnnew = new JButton("New", icnew);
		btnnew.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		btnnew.setBounds(450, 147, 132, 40);

		ImageIcon icsave = new ImageIcon(
				new ImageIcon("D:\\savelab5.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		btnsave = new JButton("Save", icsave);
		btnsave.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		btnsave.setBounds(450, 197, 132, 40);

		ImageIcon icdelete = new ImageIcon(
				new ImageIcon("D:\\deletelab5.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		btndelete = new JButton("Delete", icdelete);
		btndelete.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		btndelete.setBounds(450, 247, 132, 40);

		ImageIcon icupdate = new ImageIcon(
				new ImageIcon("D:\\updatelab5.png").getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
		btnupdate = new JButton("Update", icupdate);
		btnupdate.setFont(new Font("Serif", Font.TRUETYPE_FONT, 20));
		btnupdate.setBounds(450, 297, 132, 40);

		String[] colunm = { "Mã SV", "Họ Tên", "Email", "Số ĐT", "Giới Tính", "Địa Chỉ" };
		Object[][] data = { {} };
		model = new DefaultTableModel(data, colunm);
		tb = new JTable(model);
		tb.setFont(new Font("Serif", Font.TRUETYPE_FONT, 17));
		JTableHeader header = tb.getTableHeader();
		Font font = new Font("Serif", Font.TRUETYPE_FONT, 20);
		header.setFont(font);
		header.setForeground(Color.RED);
		tb.setPreferredScrollableViewportSize(new Dimension(600, 170));
		JScrollPane scrollPane = new JScrollPane(tb);
		scrollPane.setBounds(18, 560, 600, 145);

		ImageIcon lui = new ImageIcon(
				new ImageIcon("D:\\lui.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		btnlui = new JButton(lui);
		btnlui.setBounds(270, 515, 70, 40);

		ImageIcon tien = new ImageIcon(
				new ImageIcon("D:\\tien.png").getImage().getScaledInstance(33, 33, java.awt.Image.SCALE_SMOOTH));
		btntien = new JButton(tien);
		btntien.setBounds(390, 515, 70, 40);

		ImageIcon dau = new ImageIcon(
				new ImageIcon("D:\\dau.png").getImage().getScaledInstance(33, 33, java.awt.Image.SCALE_SMOOTH));
		btndau = new JButton(dau);
		btndau.setBounds(150, 515, 70, 40);

		ImageIcon cuoi = new ImageIcon(
				new ImageIcon("D:\\cuoi.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		btncuoi = new JButton(cuoi);
		btncuoi.setBounds(514, 515, 70, 40);
		
		MouseListener mc = new controller_bai2(this);
		tb.addMouseListener(mc);

		ActionListener ac = new controller_bai2(this);
		
		btnsearch.addActionListener(ac);
		btnnew.addActionListener(ac);
		btnsave.addActionListener(ac);
		btndelete.addActionListener(ac);
		btnupdate.addActionListener(ac);
		btndau.addActionListener(ac);
		btncuoi.addActionListener(ac);
		btntien.addActionListener(ac);
		btnlui.addActionListener(ac);

		frame.add(lbltitle);
		frame.add(lblsearch);
		frame.add(lblmasv);
		frame.add(lblhoten);
		frame.add(lblemail);
		frame.add(lblsodt);
		frame.add(lblgioitinh);
		frame.add(lbldiachi);
		frame.add(txtsearch);
		frame.add(txtmasv);
		frame.add(txthoten);
		frame.add(txtemail);
		frame.add(txtsdt);
		frame.add(nam);
		frame.add(nu);
		frame.add(diachi);
		frame.add(btnsearch);
		frame.add(btnnew);
		frame.add(btnsave);
		frame.add(btndelete);
		frame.add(btnupdate);
		frame.add(scrollPane);
		frame.add(btnlui);
		frame.add(btntien);
		frame.add(btndau);
		frame.add(btncuoi);
		frame.setVisible(true);
	}
}
