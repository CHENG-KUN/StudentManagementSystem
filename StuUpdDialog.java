
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StuUpdDialog extends JDialog implements ActionListener{
	//define needed swing parts
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3;
	
	public StuUpdDialog(Frame owner,String title,boolean modal, StuModel sm, int rowNums) {
		super(owner,title,modal);
		jl1=new JLabel("stuId");
		jl2=new JLabel("stuFirstName");
		jl3=new JLabel("stuLastName");
		jl4=new JLabel("stuSex");
		jl5=new JLabel("stuAge");
		jl6=new JLabel("stuDept");
		
		jtf1=new JTextField();
		//instantialize data
		jtf1.setText((String)sm.getValueAt(rowNums, 0));
		//jtf1 should be non-updatable
		jtf1.setEditable(false);
		jtf2=new JTextField();
		jtf2.setText((String)sm.getValueAt(rowNums, 1));
		jtf3=new JTextField();
		jtf3.setText((String)sm.getValueAt(rowNums, 2));
		jtf4=new JTextField();
		jtf4.setText((String)sm.getValueAt(rowNums, 3));
		jtf5=new JTextField();
		jtf5.setText(sm.getValueAt(rowNums, 4).toString());
		jtf6=new JTextField();
		jtf6.setText((String)sm.getValueAt(rowNums, 5));
		
		jb1=new JButton("update");
		jb2=new JButton("cancel");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		this.setSize(300, 250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jb1){
			//sql
			String strsql="update stu set stuFirstName=?, "
					+ "stuLastName=?, stuSex=?, stuAge=?, stuDept=? where stuId=?";
			String[] paras = {jtf2.getText(),jtf3.getText(),
					jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf1.getText()};
			StuModel temp = new StuModel();
			temp.updStu(strsql, paras);
			this.dispose();
		}
	}
}
