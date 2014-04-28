package gui.log;

import gui.data.DataSet;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import beans.admin.Log;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Calendar;

public class LogPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList jList = null;
	private JScrollPane jScrollPane = null;

	/**
	 * This is the default constructor
	 */
	public LogPanel() {
		super();
		this.setOpaque(false);
		initialize();
		
		displayList();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(682, 481);
		this.setLayout(null);
		this.add(getJScrollPane(), null);
	}
	
	private JScrollPane getJScrollPane()
	{
		if(jScrollPane == null)
		{
			jScrollPane = new JScrollPane(getJList());
			jScrollPane.setBounds(new Rectangle(6, 6, 668, 468));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setBounds(new Rectangle(6, 6, 668, 468));
		}
		return jList;
	}

	
	/**
	 * 重新载入list
	 */
	public void displayList()
	{
		DefaultListModel listModel = new DefaultListModel();
		
		DataSet.updateLogList();
			
		for(int i=0; i<DataSet.logList.size(); i++)
		{	
			Log log = DataSet.logList.get(i);
			
			Calendar calendar = log.getOperationTime();
			
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			
			String tempsString = "时间：    " + year + "-" + month + "-" + day + " " + hour + ":" + minute + " ---------------- " + log.getOperationDescription();
			
			listModel.addElement(tempsString);
		}
		
		jList.setModel(listModel);		
	}
	
	
	/**
	 * 重绘
	 */
	public void paintComponent(Graphics g){
    	g.setColor(Color.blue); 
        ImageIcon img=null;
		img = new ImageIcon("images/file.png");
		g.drawImage(img.getImage(),0,0,null); 
    	
    }
}
