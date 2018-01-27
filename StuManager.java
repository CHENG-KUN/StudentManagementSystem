import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class StuManager extends JFrame implements ActionListener{
	
	JPanel jp1, jp2;
	JLabel jl1;
	JButton jb1, jb2, jb3, jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
		
	public static void main (String[] args) {
		StuManager test = new StuManager();
	}
	

	public StuManager () {
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("Search");
		
		jb1.addActionListener(this);
		jl1 = new JLabel("Please input the name");
		

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2 = new JPanel();
		jb2 = new JButton("add");
		jb2.addActionListener(this);
		jb3 = new JButton("update");
		jb3.addActionListener(this);
		jb4 = new JButton("delete");
		jb4.addActionListener(this);
		

		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		sm = new StuModel();
		String[] paras = {"1"};
		sm.queryStu("select * from stu where 1 = ?", paras);
		//instantialize JTable
		jt = new JTable(sm);
		
		//instantialize jsp
		jsp = new JScrollPane(jt);
		
		//put jsp into jframe
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//which button is pressed
		if (e.getSource() == jb1) {
			System.out.println("");
			
			//because table is encapsulated into StuModel, we can write DML DDL  fairly easily.
			
			String name = this.jtf.getText();
			//write a sql query
			String sql = "select * from stu where stuFirstName = ?;";
			String paras[] = {name};
			
			sm = new StuModel();
			sm.queryStu(sql, paras);
			//update JTable
			jt.setModel(sm);
		} else if (e.getSource() == jb2) {
			StuAddDialog sa = new StuAddDialog(this, "add a student", true);
			
			sm = new StuModel();
			String[] paras2 = {"1"};
			sm.queryStu("select * from stu where 1 = ?", paras2);
			jt.setModel(sm);
			//update JTable
			jt.setModel(sm);

		} else if (e.getSource() == jb3) {
			//update
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please select a student");
				return;
			}
			//update dialogue
			new StuUpdDialog (this, "Update Student Info", true, sm, rowNum);
			sm = new StuModel();
			String[] paras2 = {"1"};
			sm.queryStu("select * from stu where 1 = ?", paras2);
			jt.setModel(sm);
		}
		else if (e.getSource() == jb4) {
			//delete record
			//1. get stuid
			//getSelectedRow returns row the user selects
			//if user picks no row, it returns -1
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please select a student");
				return;
			} //get stuid
			String stuId = (String)sm.getValueAt(rowNum, 0);
			
			//instantialize sql
			String sql = "delete from stu where stuId = ?";
			String[] paras = {stuId};
			StuModel temp = new StuModel();
			temp.updStu(sql, paras);
			
			//update
			sm = new StuModel();
			String[] paras2 = {"1"};
			sm.queryStu("select * from stu where 1 = ?", paras2);
			jt.setModel(sm);
		}
	}
}
