package gui.schedule;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import client.login.Login;

import beans.teacher.Schedule;


@SuppressWarnings("serial")
public class NotePad extends JPanel implements ActionListener
{
	JTextArea textArea;              
    JButton saveButton, deleteButton;           
    JLabel infoLabel;               
    int year, month, day;                            
    CalendarPad calendarPad;

    private int tag;  //0表示只能看（学生权限），1表示可编辑（老师权限）

    public  NotePad(CalendarPad calendarPad, int tag)
    {
    	this.tag = tag;
    	this.setOpaque(false);
    	this.calendarPad=calendarPad;
        year=calendarPad.getYear();
        month=calendarPad.getMonth();
        day=calendarPad.getDay();

        infoLabel=new JLabel(""+year+"年"+month+"月"+day+"日",JLabel.CENTER);
        infoLabel.setFont(new Font("TimesRoman",Font.BOLD,16));
        infoLabel.setForeground(Color.blue);
        textArea=new JTextArea(10,17);
        saveButton=new JButton("保存日志") {
        	{
				setContentAreaFilled(false);
				setOpaque(false);
			}
			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/buttonbg.png");
				g.drawImage(img.getImage(),0,0,null); 
				super.paintComponent(g);
		    }
        };
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton=new JButton("删除日志") {
        	{
				setContentAreaFilled(false);
				setOpaque(false);
			}
			public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/buttonbg.png");
				g.drawImage(img.getImage(),0,0,null); 
				super.paintComponent(g);
		    }
        };
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveButton.addActionListener(this);
        deleteButton.addActionListener(this);
        setLayout(new BorderLayout());
        JPanel pSouth=new JPanel();
        pSouth.setOpaque(false);
        add(infoLabel,BorderLayout.NORTH);
        pSouth.add(saveButton);
        pSouth.add(deleteButton);
        add(pSouth,BorderLayout.SOUTH);
        add(new JScrollPane(textArea),BorderLayout.CENTER);
        
        //如果是学生权限，屏蔽编辑
        if(this.tag == 0)
        {
        	saveButton.setVisible(false);
        	deleteButton.setVisible(false);
        	textArea.setEditable(false);
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==saveButton)
    	{
    		保存日志(year,month,day);
        }
    	else if(e.getSource()==deleteButton)
    	{
    		删除日志(year,month,day);
    	}
    }
    
    public void setYear(int year)
    {
    	this.year=year;
    }
    
    public int getYear(){
    	return year;
    }
    
    public void setMonth(int month){
    	this.month=month;
    } 
    public int getMonth(){
    	return month;
    } 
    
    public void setDay(int day){
    	this.day=day;
    }
    
    public int getDay(){
    	return day;
    }
    
    public void 设置信息条(int year,int month,int day)
    {
    	infoLabel.setText(""+year+"年"+month+"月"+day+"日");
    }
    
    public void 设置文本区(String s){
    	textArea.setText(s);
    }
    
    public void 获取日志内容(int year,int month,int day,String username){
        String content=calendarPad.getScheduleContent(year,month,day);
    	if(content!=null){
            	textArea.setText(content);
            } 
    	else
    	{
    		textArea.setText("");
    		} 
    	}
    
    public void 保存日志(int year,int month,int day){
    	String 日志内容=textArea.getText();
        
        String m=""+year+"年"+month+"月"+day+"保存日志吗?";
        int ok=JOptionPane.showConfirmDialog(this,m,"询问",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(ok==JOptionPane.YES_OPTION){
        	Schedule schedule=new Schedule();
        	schedule.setUsername(Login.person.getUsername());
        	schedule.setDay(day);
        	schedule.setYear(year);
        	schedule.setMonth(month);
        	schedule.setContent(日志内容);
        	schedule.setLocation(0);
        	
        	boolean flag=Login.person.saveSchedule(schedule);      //需要修改！！！
        	/*!!!!!!!!VERY IMPORTANT!!!!!
        	 * 
        	 * 若界面上本来为空，应该调用addSchedule方法
        	 * 
        	*/
        	if(flag){
        		System.out.println("修改成功!");
        		ArrayList<Schedule> array = new ArrayList<Schedule>();
        		
        		array=Login.person.getScheduleListByYearAndMonth(year, month);
        		
        		calendarPad.setScheduleArray(array);                      //在本地的scheduleList中更新
        		
        		calendarPad.setBackground(day-1, Color.green);
        	}
        	else
        		System.out.println("修改失败！");
        	}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }


    public void 删除日志(int year,int month,int day){
    	
        if(calendarPad.getScheduleContent(year,month,day)!=null){  
        	String m="删除"+year+"年"+month+"月"+day+"日的日志吗?";
            int ok=JOptionPane.showConfirmDialog(this,m,"询问",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(ok==JOptionPane.YES_OPTION){ 

            	boolean flag=Login.person.deleteSchedule(year, month, day);
            	if(flag){
            		System.out.println("删除成功!");
            		
            		ArrayList<Schedule> array = new ArrayList<Schedule>();
            		array=Login.person.getScheduleListByYearAndMonth(year, month);
            		calendarPad.setScheduleArray(array);   //更新当前列表
            		textArea.setText("");
            		
            		if(calendarPad.isToday(day))
            		    calendarPad.setBackground(day-1, Color.red);
            		else
            			calendarPad.setBackground(day-1, Color.black);			
            	}   		
            	else
            		System.out.println("删除失败！");
            	}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            	
            }
        else{
            String m=""+year+"年"+month+"月"+day+"无日志记录";
            JOptionPane.showMessageDialog(this,m,"提示",JOptionPane.WARNING_MESSAGE);
          }
   }
    
}
