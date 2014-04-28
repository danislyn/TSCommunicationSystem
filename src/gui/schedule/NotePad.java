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

    private int tag;  //0��ʾֻ�ܿ���ѧ��Ȩ�ޣ���1��ʾ�ɱ༭����ʦȨ�ޣ�

    public  NotePad(CalendarPad calendarPad, int tag)
    {
    	this.tag = tag;
    	this.setOpaque(false);
    	this.calendarPad=calendarPad;
        year=calendarPad.getYear();
        month=calendarPad.getMonth();
        day=calendarPad.getDay();

        infoLabel=new JLabel(""+year+"��"+month+"��"+day+"��",JLabel.CENTER);
        infoLabel.setFont(new Font("TimesRoman",Font.BOLD,16));
        infoLabel.setForeground(Color.blue);
        textArea=new JTextArea(10,17);
        saveButton=new JButton("������־") {
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
        deleteButton=new JButton("ɾ����־") {
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
        
        //�����ѧ��Ȩ�ޣ����α༭
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
    		������־(year,month,day);
        }
    	else if(e.getSource()==deleteButton)
    	{
    		ɾ����־(year,month,day);
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
    
    public void ������Ϣ��(int year,int month,int day)
    {
    	infoLabel.setText(""+year+"��"+month+"��"+day+"��");
    }
    
    public void �����ı���(String s){
    	textArea.setText(s);
    }
    
    public void ��ȡ��־����(int year,int month,int day,String username){
        String content=calendarPad.getScheduleContent(year,month,day);
    	if(content!=null){
            	textArea.setText(content);
            } 
    	else
    	{
    		textArea.setText("");
    		} 
    	}
    
    public void ������־(int year,int month,int day){
    	String ��־����=textArea.getText();
        
        String m=""+year+"��"+month+"��"+day+"������־��?";
        int ok=JOptionPane.showConfirmDialog(this,m,"ѯ��",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(ok==JOptionPane.YES_OPTION){
        	Schedule schedule=new Schedule();
        	schedule.setUsername(Login.person.getUsername());
        	schedule.setDay(day);
        	schedule.setYear(year);
        	schedule.setMonth(month);
        	schedule.setContent(��־����);
        	schedule.setLocation(0);
        	
        	boolean flag=Login.person.saveSchedule(schedule);      //��Ҫ�޸ģ�����
        	/*!!!!!!!!VERY IMPORTANT!!!!!
        	 * 
        	 * �������ϱ���Ϊ�գ�Ӧ�õ���addSchedule����
        	 * 
        	*/
        	if(flag){
        		System.out.println("�޸ĳɹ�!");
        		ArrayList<Schedule> array = new ArrayList<Schedule>();
        		
        		array=Login.person.getScheduleListByYearAndMonth(year, month);
        		
        		calendarPad.setScheduleArray(array);                      //�ڱ��ص�scheduleList�и���
        		
        		calendarPad.setBackground(day-1, Color.green);
        	}
        	else
        		System.out.println("�޸�ʧ�ܣ�");
        	}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }


    public void ɾ����־(int year,int month,int day){
    	
        if(calendarPad.getScheduleContent(year,month,day)!=null){  
        	String m="ɾ��"+year+"��"+month+"��"+day+"�յ���־��?";
            int ok=JOptionPane.showConfirmDialog(this,m,"ѯ��",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(ok==JOptionPane.YES_OPTION){ 

            	boolean flag=Login.person.deleteSchedule(year, month, day);
            	if(flag){
            		System.out.println("ɾ���ɹ�!");
            		
            		ArrayList<Schedule> array = new ArrayList<Schedule>();
            		array=Login.person.getScheduleListByYearAndMonth(year, month);
            		calendarPad.setScheduleArray(array);   //���µ�ǰ�б�
            		textArea.setText("");
            		
            		if(calendarPad.isToday(day))
            		    calendarPad.setBackground(day-1, Color.red);
            		else
            			calendarPad.setBackground(day-1, Color.black);			
            	}   		
            	else
            		System.out.println("ɾ��ʧ�ܣ�");
            	}//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            	
            }
        else{
            String m=""+year+"��"+month+"��"+day+"����־��¼";
            JOptionPane.showMessageDialog(this,m,"��ʾ",JOptionPane.WARNING_MESSAGE);
          }
   }
    
}
