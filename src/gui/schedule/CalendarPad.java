package gui.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;

import client.login.Login;

import beans.teacher.Schedule;

import java.awt.*;
import java.awt.event.*;


public class CalendarPad extends JFrame implements MouseListener
{
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   
   private int year,month,today,day;
  
   private ArrayList<Schedule> allScheduleList;
   private ArrayList<Schedule> scheduleList;
   
   private final int tag;  //0表示只能看（学生权限），1表示可编辑（老师权限）
	
   JTextField showDay[];             
   JLabel title[];                   
   Calendar calendar;
   int 星期几; 
   NotePad notepad;             
   Month monthModule;
   Year  yearModule;
   String weekString[]={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
   JPanel leftPanel, rightPanel;    
   
   
   /** 构造方法
    * 
    * @param tag 0表示只能看（学生权限），1表示可编辑（老师权限）
    * @param scheduleList 当tag=0时，务必传入想要看的人的scheduleList
    */
   public CalendarPad(int tag, ArrayList<Schedule> scheduleList)
   { 
	 this.setResizable(false);  
	 this.tag = tag;
	 
	 //初始化scheduleList
	 //学生权限
	 if(tag == 0)
	 {
		 this.allScheduleList = scheduleList;
	 }
	 //老师权限
	 else
	 {
		 this.allScheduleList = Login.person.getAllScheduleList();
	 }
	   
	   
     leftPanel=new JPanel();
     leftPanel.setOpaque(false);
     JPanel leftCenter=new JPanel();
     JPanel leftNorth=new JPanel();
     leftCenter.setOpaque(false);
     leftNorth.setOpaque(false);
     leftCenter.setLayout(new GridLayout(7,7));   
                                                  
     rightPanel=new JPanel();
     rightPanel.setOpaque(false);
     
     calendar = Calendar.getInstance();    
     //设置当前年月日
     this.year = calendar.get(Calendar.YEAR);
     this.month = calendar.get(Calendar.MONTH)+1;
     this.day = calendar.get(Calendar.DAY_OF_MONTH);
     this.today = this.day;

     yearModule=new Year(this);
     yearModule.setYear(year);
     monthModule=new Month(this);
     monthModule.setMonth(month);
  
     title=new JLabel[7];                         
     showDay=new JTextField[42];                   
     for(int j=0;j<7;j++)                         
       {
         title[j]=new JLabel();
         title[j].setText(weekString[j]);
         title[j].setBorder(BorderFactory.createRaisedBevelBorder());
         leftCenter.add(title[j]);
       } 


     for(int i=0;i<42;i++)                        
       {
         showDay[i]=new JTextField();
         showDay[i].addMouseListener(this);
         showDay[i].setEditable(false);
         leftCenter.add(showDay[i]);
         

       }
         
     
     Box box=Box.createHorizontalBox();          
     box.add(yearModule);
     box.add(monthModule);
     leftNorth.add(box);
     leftPanel.setLayout(new BorderLayout());
     leftPanel.add(leftNorth,BorderLayout.NORTH);
     leftPanel.add(leftCenter,BorderLayout.CENTER);
     leftPanel.add(new Label("请在年份输入框输入所查年份(负数表示公元前),并回车确定"),
                  BorderLayout.SOUTH) ;
     leftPanel.validate();
     Container con=getContentPane();
     JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                     leftPanel,rightPanel){
    	 /**重绘背景
										 * 
										 */
										private static final long serialVersionUID = 1L;
		{
    		 setOpaque(false);
    	 }
    	 public void paintComponent(Graphics g) { 
		        g.setColor(Color.blue); 
		        ImageIcon img=null;
				img = new ImageIcon("images/b3.jpg");
				g.drawImage(img.getImage(),0,0,null); 
		    }
     };
     
     con.add(split,BorderLayout.CENTER);
     con.validate();
    
    
     notepad=new NotePad(this, tag);                                      
     rightPanel.add(notepad);
     
     设置日历牌(year,month);
     
     
    //addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
    setVisible(true);
    setSize(550,285);
    setLocationRelativeTo(null);
    validate();
   }
  public void 设置日历牌(int year,int month)
   {
     calendar.set(year,month-1,1);              
     
     星期几=calendar.get(Calendar.DAY_OF_WEEK)-1;
     if(month==1||month==3||month==5||month==7
                        ||month==8||month==10||month==12)
        {
            排列号码(星期几,31,year,month);         
        }
     else if(month==4||month==6||month==9||month==11)
        {
            排列号码(星期几,30,year,month);
        }
     else if(month==2)
        {
         if((year%4==0&&year%100!=0)||(year%400==0))  
           {
             排列号码(星期几,29,year,month);
           }
         else
           {
             排列号码(星期几,28,year,month);
           }
       }
   } //设置从哪里开始排
  
  public void 排列号码(int 星期几,int 月天数,int year, int month){
	  
	  if(tag == 0)
	  {
		  scheduleList = searchScheduleByYearAndMonth(year, month);
	  }
	  else
	  {
		  scheduleList = Login.person.getScheduleListByYearAndMonth(year, month);
	  }
	  
	  for(int i=星期几,n=1;i<星期几+月天数;i++,n++){
		  
		  showDay[i].setText(""+n);//设置日历上每月日期
		  if(n==today)
                 {
			       showDay[i].setForeground(Color.red);
                   showDay[i].setFont(new Font("TimesRoman",Font.BOLD,20));
                 }
               else
                 { 
                  showDay[i].setFont(new Font("TimesRoman",Font.BOLD,12));
                  showDay[i].setForeground(Color.black);
                 }

		  for(int index=0; index<scheduleList.size(); index++){
			  if(scheduleList.get(index).getDay()==n){
	 			  showDay[i].setForeground(Color.green);
	 		  }
		  }
		  
	  }
       for(int i=0;i<星期几;i++)
             {
                showDay[i].setText("");
             }
       for(int i=星期几+月天数;i<42;i++)
             {
                showDay[i].setText("");
             }
       
     
   }
 public String getScheduleContent(int year,int month,int day) {
	 String content=null;
	 for(int index=0;index<scheduleList.size();index++){
		 if(scheduleList.get(index).getYear()==year&&
				 scheduleList.get(index).getMonth()==month&&
				 scheduleList.get(index).getDay()==day)
			 content=scheduleList.get(index).getContent();
	 }
	 return content;
 }
 public boolean isToday(int day){
	 return today==day;
 }
 public void setBackground(int num,Color color){
	 showDay[num+星期几].setForeground(color);
 }
 
 public ArrayList<Schedule> getScheduleArray(){
	 return scheduleList;
 }
 public void setScheduleArray(ArrayList<Schedule> scheduleList){
	 this.scheduleList=scheduleList;
 }
 public int getYear()
   {
    return year;
   } 
 public void setYear(int y)
   {
     year=y;
     notepad.setYear(year);
   }
 public int getMonth()
   {
    return month;
   }
 public void setMonth(int m)
   {
     month=m;
     notepad.setMonth(month); 
   }
 public int getDay()
   {
    return day;
   }
 public void setDay(int d)
   {
    day=d;
    notepad.setDay(day);
   }

 public void mousePressed(MouseEvent e)             
   {
     JTextField source=(JTextField)e.getSource();
     try{
         day=Integer.parseInt(source.getText());
         notepad.setDay(day);
         notepad.设置信息条(year,month,day);
         notepad.设置文本区(null);
         notepad.获取日志内容(year,month,day,Login.person.getUsername());
        } 
      catch(Exception ee)
        {
        }
   }
 public void mouseClicked(MouseEvent e)
   {
   }
 public void mouseReleased(MouseEvent e)
   {
   }
 public void mouseEntered(MouseEvent e)
   {
   }
 public void mouseExited(MouseEvent e)
   {
   }

 	
 	private ArrayList<Schedule> searchScheduleByYearAndMonth(int year, int month)
	{
		ArrayList<Schedule> searchList = new ArrayList<Schedule>();
		
		for(int i=0; i<allScheduleList.size(); i++)
		{
			Schedule schedule = allScheduleList.get(i);
			
			if(schedule.getYear() == year && schedule.getMonth() == month)
			{
				searchList.add(schedule);
			}
		}
		
		return searchList;
	}
 
 
}  
