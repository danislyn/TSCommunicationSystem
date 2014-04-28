package gui.selfInfo;

import gui.data.DataSet;
import gui.schedule.CalendarPad;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.login.Login;

import beans.information.Information;
import beans.teacher.Schedule;

/**
 * 显示信息的面板
 */
public class SelfInfoPane extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	
	private JTextField classfield;
	private JLabel grade;
	private JTextField gradefield;
	
	private JLabel hobby;
	private JTextArea hobbyArea;
	private JLabel klass;
	
	private JLabel name;
	private JTextField namefield;
	private JLabel sign;
	private JTextField signfield;
	private JLabel skill;
	private JTextArea skillArea;
	
	private JButton more;
	private JButton mailButton;
	private JButton calendarButton;
	private boolean state=false;
	
	//暂存scheduleList
	private ArrayList<Schedule> scheduleList;
	
	private Information otherInfo;
	
	
	public SelfInfoPane() {
		this.setOpaque(false);
		initComponents();
	}
	
	public SelfInfoPane(Information info, boolean isOther){
		this.setOpaque(false);
		initComponents();
		this.otherInfo = info;
		initInfo(info, isOther);
	}

	
	/**
	 * 初始化information对象及标记
	 * @param info
	 * @param isOther 是否为看他人信息
	 */
	public void initInfo(Information info, boolean isOther) {
		
		namefield.setText(info.getName());
		gradefield.setText(String.valueOf(info.getGrade()));
		classfield.setText(String.valueOf(info.getKlass()));
		signfield.setText(info.getSignature());
		
		String temp1=" ";
		for(int i=0;i<info.getHobbyList().size();i++)
			temp1=temp1+info.getHobbyList().get(i)+" ";
		hobbyArea.setText(temp1);
		
		String temp2="";
		for(int i=0;i<info.getAdeptnessList().size();i++)
			temp2=temp2+info.getAdeptnessList().get(i)+" ";
		skillArea.setText(temp2);
		
		
		
		if(isOther == true)
		{
			more.setVisible(false);
			
			//如果是老师，可以看日程
			if(info.getGrade() == 0)
			{
				scheduleList = Login.person.updateTeacherInfo(info.getUsername()).getScheduleList();
				
				calendarButton.setVisible(true);	
			}
		}
		else
		{
			mailButton.setVisible(false);
		}
		
	}

	public boolean getState(){
		state=!state;
		return state;
	}
	public JButton getButton(){
		return this.more;
	}
	
	
	/**
	 * 初始化组件
	 */
	private void initComponents() {

		name = new JLabel();
		namefield = new JTextField();
		namefield.setEditable(false);
		gradefield = new JTextField();
		gradefield.setEditable(false);
		grade = new JLabel();
		classfield = new JTextField();
		classfield.setEditable(false);
		klass = new JLabel();
		sign = new JLabel();
		signfield = new JTextField();
		signfield.setEditable(false);
		hobby = new JLabel();
		hobbyArea=new JTextArea();
		hobbyArea.setEditable(false);
		skill = new JLabel();
		skillArea=new JTextArea();
		skillArea.setEditable(false);
		
		
		//more action
		more=new JButton(){
			/**
			 * 重绘按钮
			 */
			private static final long serialVersionUID = 1L;
			{
    			setMargin(new Insets(2,2,2,2));
    			setBounds(230, 160, 52, 20);
    			setContentAreaFilled(false);
				setOpaque(false);
    		}
            protected void paintComponent(Graphics g){
            	ImageIcon imageicon=new ImageIcon("images/more.png");
				g.drawImage(imageicon.getImage(), 0, 0,null);
				super.paintComponent(g);	
				
			}
		};
		more.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(more);
		
		
		//快捷发信
		mailButton=new JButton(){
			/**
			 * 重绘按钮
			 */
			private static final long serialVersionUID = 1L;
			{
    			setMargin(new Insets(2,2,2,2));  			
    			setBounds(230, 160, 52, 20);
    			setContentAreaFilled(false);
				setOpaque(false);
				setText("发信！");
    		}
            protected void paintComponent(Graphics g){
            	ImageIcon imageicon=new ImageIcon("images/buttonback.png");
				g.drawImage(imageicon.getImage(), 0, 0,null);
				super.paintComponent(g);	
				
			}
		};
		mailButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(mailButton);
		
		mailButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				DataSet.mainFrame.mailBoxFrame.setVisible(true);
				DataSet.mainFrame.mailBoxFrame.changeToSmallWidth();
				DataSet.mainFrame.mailBoxFrame.centerPanel.removeAll();
				DataSet.mainFrame.mailBoxFrame.writeMailPanel.reset();  //claer
				DataSet.mainFrame.mailBoxFrame.writeMailPanel.setShortCut(otherInfo.getUsername());  //set
				DataSet.mainFrame.mailBoxFrame.centerPanel.add(DataSet.mainFrame.mailBoxFrame.writeMailPanel, null);
				DataSet.mainFrame.mailBoxFrame.writeMailPanel.updateUI();
				DataSet.mainFrame.mailBoxFrame.repaint();
			}
		});
		
		
		//看日程的
		calendarButton=new JButton(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{
    			setMargin(new Insets(2,2,2,2));
    			setBounds(170, 160, 52, 20);
    			setContentAreaFilled(false);
				setOpaque(false);
				setText("日程");
				setVisible(false);
    		}
            protected void paintComponent(Graphics g){
            	ImageIcon imageicon=new ImageIcon("images/buttonback.png");
				g.drawImage(imageicon.getImage(), 0, 0,null);
				super.paintComponent(g);	
				
            }                
		};
		
		//监听
		calendarButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				CalendarPad calendarPad = new CalendarPad(0, scheduleList);
			    calendarPad.setVisible(true);
			}
		});
		calendarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(calendarButton);

		
		setMaximumSize(new java.awt.Dimension(300, 170));
		setMinimumSize(new java.awt.Dimension(300, 170));
		setPreferredSize(new java.awt.Dimension(300, 170));
		setLayout(null);

		name.setFont(new java.awt.Font("黑体", 0, 14));
		name.setText("\u59d3\u540d\uff1a");
		add(name);
		name.setBounds(10, 5, 50, 20);

		
		add(namefield);
		namefield.setBounds(50, 5, 70, 20);
		add(gradefield);
		gradefield.setBounds(130, 5, 50, 20);

		grade.setFont(new java.awt.Font("黑体", 0, 14));
		grade.setText("\u5e74");
		add(grade);
		grade.setBounds(190, 5, 20, 20);

		
		add(classfield);
		classfield.setBounds(210, 5, 50, 20);

		klass.setFont(new java.awt.Font("黑体", 0, 14));
		klass.setText("\u73ed");
		add(klass);
		klass.setBounds(270, 5, 20, 20);

		sign.setFont(new java.awt.Font("黑体", 0, 14));
		sign.setText("\u7b7e\u540d\uff1a");
		add(sign);
		sign.setBounds(10, 30, 50, 20);
		add(signfield);
		signfield.setBounds(50, 30, 240, 40);

		hobby.setFont(new java.awt.Font("黑体", 0, 14));
		hobby.setText("\u5174\u8da3\uff1a");
		add(hobby);
		hobby.setBounds(10, 75, 50, 20);
        
		hobbyArea.setBounds(50, 75, 240, 30);
		hobbyArea.setLineWrap(true);
		add(hobbyArea);

		skill.setFont(new java.awt.Font("黑体", 0, 14));
		skill.setText("\u7279\u957f\uff1a");
		add(skill);
		skill.setBounds(10, 120, 50, 20);
		
		skillArea.setBounds(50, 120, 240, 30);
		skillArea.setLineWrap(true);
        add(skillArea);
		

		
		
	}

	

}
