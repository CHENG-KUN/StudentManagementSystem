
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.*;

public class StuModel extends AbstractTableModel {
	
	Vector rowData, columnNames;
	
	//add students
	public boolean updStu(String sql, String[] paras) {
		//create SqlHelper
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}
	
	public void queryStu(String sql, String[] paras) {
		
		SqlHelper sqlHelper = null;
		
		
		columnNames = new Vector();
		//set column names
		columnNames.add("stuId");
		columnNames.add("stuFirstName");
		columnNames.add("stuLastName");
		columnNames.add("stuSex");
		columnNames.add("stuAge");
		columnNames.add("stuDept");
		
		rowData = new Vector();
		
		try {
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.queryExecute(sql, paras);
			
			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getInt(5));
				row.add(rs.getString(6));
				
				rowData.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			sqlHelper.close();
		}
	}
	
	
	//constructor, instantialize table model

	//get column number
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	//get row number
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	//get specific data from specific column&row
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(column);
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(arg0);
	}

}
