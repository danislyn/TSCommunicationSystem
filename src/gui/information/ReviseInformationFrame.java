package gui.information;

import gui.data.DataSet;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import client.login.Login;

import beans.information.Information;

public class ReviseInformationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel usernameLabel = null;
	private JTextField usernameTextField = null;
	private JLabel nameLabel = null;
	private JTextField nameTextField = null;
	private JLabel nameInfoLabel = null;
	private JLabel gradeLabel = null;
	private JComboBox gradeComboBox = null;
	private JLabel classLabel = null;
	private JComboBox classComboBox = null;
	private JLabel signatureLabel = null;
	private JTextArea signatureTextArea = null;
	private JLabel signatureInfoLabel = null;
	private JLabel hobbyLabel = null;
	private JLabel adeptnessLabel = null;
	private JButton okButton = null;
	private JButton cancelButton = null;
	private JScrollPane hobbyScrollPane = null;
	private JScrollPane adeptnessScrollPane = null;
	private JScrollPane signatureScrollPane = null;
	private JButton exit=null;
	
	private HobbySelectionPanel hobbySelectionPanel;
	private AdeptnessSelectionPanel adeptnessSelectionPanel;

	private Information myInformation;
	
	private ArrayList<String> hobbyDefinitionList;
	private ArrayList<String> adeptnessDefinitionList;
	
	/**
	 * This is the default constructor
	 */
	public ReviseInformationFrame() {
		super();
		this.setUndecorated(true);
		hobbyDefinitionList = Login.person.getHobbyList();
		adeptnessDefinitionList = Login.person.getAdeptnessList();
		
		hobbySelectionPanel = new HobbySelectionPanel();
		adeptnessSelectionPanel = new AdeptnessSelectionPanel();
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(319, 660);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			adeptnessLabel = new JLabel();
			adeptnessLabel.setHorizontalAlignment(SwingConstants.CENTER);
			adeptnessLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			adeptnessLabel.setLocation(new Point(186, 304));
			adeptnessLabel.setSize(new Dimension(65, 25));
			adeptnessLabel.setText("特长");
			hobbyLabel = new JLabel();
			hobbyLabel.setText("兴趣");
			hobbyLabel.setSize(new Dimension(65, 25));
			hobbyLabel.setHorizontalAlignment(SwingConstants.CENTER);
			hobbyLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			hobbyLabel.setLocation(new Point(40, 304));
			signatureInfoLabel = new JLabel();
			signatureInfoLabel.setBounds(new Rectangle(97, 271, 187, 20));
			signatureInfoLabel.setForeground(Color.red);
			signatureInfoLabel.setText("不得超过50个汉字");
			signatureLabel = new JLabel();
			signatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
			signatureLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			signatureLabel.setSize(new Dimension(65, 25));
			signatureLabel.setLocation(new Point(16, 197));
			signatureLabel.setText("个人签名");
			classLabel = new JLabel();
			classLabel.setHorizontalAlignment(SwingConstants.CENTER);
			classLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			classLabel.setLocation(new Point(16, 155));
			classLabel.setSize(new Dimension(65, 25));
			classLabel.setText("班级");
			gradeLabel = new JLabel();
			gradeLabel.setHorizontalAlignment(SwingConstants.CENTER);
			gradeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			gradeLabel.setSize(new Dimension(65, 25));
			gradeLabel.setLocation(new Point(16, 113));
			gradeLabel.setText("年级");
			nameInfoLabel = new JLabel();
			nameInfoLabel.setForeground(Color.red);
			nameInfoLabel.setSize(new Dimension(156, 17));
			nameInfoLabel.setLocation(new Point(98, 82));
			nameInfoLabel.setText("请用实名,不得超过4个汉字");
			nameLabel = new JLabel();
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			nameLabel.setLocation(new Point(16, 54));
			nameLabel.setSize(new Dimension(65, 25));
			nameLabel.setText("姓名");
			usernameLabel = new JLabel();
			usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			usernameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			usernameLabel.setLocation(new Point(16, 14));
			usernameLabel.setSize(new Dimension(65, 25));
			usernameLabel.setText("用户名(ID)");
			jContentPane = new JPanel(){
				{
					setOpaque(false);
				}
				public void paintComponent(Graphics g){
			    	g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/reInfo.jpg");
					g.drawImage(img.getImage(),0,0,null); 
			    	
			    }
			};
			jContentPane.setLayout(null);
			jContentPane.add(usernameLabel, null);
			jContentPane.add(getUsernameTextField(), null);
			jContentPane.add(nameLabel, null);
			jContentPane.add(getNameTextField(), null);
			jContentPane.add(nameInfoLabel, null);
			jContentPane.add(gradeLabel, null);
			jContentPane.add(getGradeComboBox(), null);
			jContentPane.add(classLabel, null);
			jContentPane.add(getClassComboBox(), null);
			jContentPane.add(signatureLabel, null);
			jContentPane.add(getSignatureScrollPane(), null);
			jContentPane.add(signatureInfoLabel, null);
			jContentPane.add(hobbyLabel, null);
			jContentPane.add(adeptnessLabel, null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getCancelButton(), null);
			jContentPane.add(getHobbyScrollPane(), null);
			jContentPane.add(getAdeptnessScrollPane(), null);
			jContentPane.add(getexitButton(),null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes usernameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUsernameTextField() {
		if (usernameTextField == null) {
			usernameTextField = new JTextField();
			usernameTextField.setEditable(false);
			usernameTextField.setLocation(new Point(98, 14));
			usernameTextField.setSize(new Dimension(167, 25));
		}
		return usernameTextField;
	}

	/**
	 * This method initializes nameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNameTextField() {
		if (nameTextField == null) {
			nameTextField = new JTextField();
			nameTextField.setLocation(new Point(98, 54));
			nameTextField.setSize(new Dimension(153, 25));
		}
		return nameTextField;
	}

	/**
	 * This method initializes gradeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getGradeComboBox() {
		if (gradeComboBox == null) {
			gradeComboBox = new JComboBox();
			gradeComboBox.setSize(new Dimension(100, 25));
			gradeComboBox.setLocation(new Point(98, 113));
			
			gradeComboBox.addItem("老师");  //0
			gradeComboBox.addItem("大一");  //1
			gradeComboBox.addItem("大二");  //2
			gradeComboBox.addItem("大三");  //3
			gradeComboBox.addItem("大四");  //4
			
			gradeComboBox.setSelectedIndex(-1);
		}
		return gradeComboBox;
	}

	/**
	 * This method initializes classComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getClassComboBox() {
		if (classComboBox == null) {
			classComboBox = new JComboBox();
			classComboBox.setLocation(new Point(98, 155));
			classComboBox.setSize(new Dimension(100, 25));
			
			classComboBox.addItem("老师");  //0
			classComboBox.addItem("1班");  //1
			classComboBox.addItem("2班");  //2
			classComboBox.addItem("3班");  //3
			classComboBox.addItem("4班");  //4
			
			classComboBox.setSelectedIndex(-1);
		}
		return classComboBox;
	}
	
	private JScrollPane getSignatureScrollPane()
	{
		if(signatureScrollPane == null)
		{
			signatureScrollPane = new JScrollPane(getSignatureTextArea());
			signatureScrollPane.setBounds(new Rectangle(98, 197, 185, 70));
		}
		return signatureScrollPane;
	}

	/**
	 * This method initializes signatureTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getSignatureTextArea() {
		if (signatureTextArea == null) {
			signatureTextArea = new JTextArea();
			signatureTextArea.setBounds(new Rectangle(98, 197, 185, 70));
		}
		return signatureTextArea;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton(){
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
			okButton.setText("确认修改");
			okButton.setSize(new Dimension(90, 30));
			okButton.setLocation(new Point(105, 578));
			okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					String username = usernameTextField.getText();
					String name = nameTextField.getText();
					String signature = signatureTextArea.getText();
					
					int grade = gradeComboBox.getSelectedIndex();
					int klass = classComboBox.getSelectedIndex();
					
					ArrayList<Integer> hobbyNumList = hobbySelectionPanel.getSelectedHobbyNumList();
					ArrayList<Integer> adeptnessNumList = adeptnessSelectionPanel.getSelectedAdeptnessNumList();
					
					//转成String
					ArrayList<String> hobbyList = new ArrayList<String>();
					ArrayList<String> adeptnessList = new ArrayList<String>();
					
					for(int i=0; i<hobbyNumList.size(); i++)
					{
						hobbyList.add(hobbyDefinitionList.get(hobbyNumList.get(i)-1));
					}
					
					for(int i=0; i<adeptnessNumList.size(); i++)
					{
						adeptnessList.add(adeptnessDefinitionList.get(adeptnessNumList.get(i)-1));
					}
					
					Information info = new Information();
					info.setUsername(username);
					info.setName(name);
					info.setGrade(grade);
					info.setKlass(klass);
					info.setSignature(signature);
					info.setHobbyNumList(hobbyNumList);
					info.setAdeptnessNumList(adeptnessNumList);
					info.setHobbyList(hobbyList);
					info.setAdeptnessList(adeptnessList);

					
					if(Login.person.reviseInformation(info) == true)
					{
						JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						
						//update
						//setInformation(Login.person.getMyInformation());
						DataSet.mainFrame.left.selfp.initInfo(Login.person.getMyInformation(), false);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "修改失败，请检查输入是否合法", "提示", JOptionPane.INFORMATION_MESSAGE);
						
						//恢复			
						//setInformation(myInformation);
					}					
					
					repaint();					
				}
			});
		}
		return okButton;
	}

	/**
	 * This method initializes cancelButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton(){
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
			cancelButton.setLocation(new Point(200, 578));
			cancelButton.setText("取消修改");
			cancelButton.setSize(new Dimension(90, 30));
			cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					//恢复
					setInformation(myInformation);
					repaint();
				}
			});
		}
		return cancelButton;
	}

	/**
	 * This method initializes hobbyScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getHobbyScrollPane() {
		if (hobbyScrollPane == null) {
			hobbyScrollPane = new JScrollPane(hobbySelectionPanel);			
			hobbyScrollPane.setBounds(new Rectangle(19, 336, 116, 220));
		}
		return hobbyScrollPane;
	}

	/**
	 * This method initializes adeptnessScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getAdeptnessScrollPane() {
		if (adeptnessScrollPane == null) {
			adeptnessScrollPane = new JScrollPane(adeptnessSelectionPanel);
			adeptnessScrollPane.setOpaque(false);
			adeptnessScrollPane.setLocation(new Point(161, 336));
			adeptnessScrollPane.setSize(new Dimension(116, 220));
		}
		return adeptnessScrollPane;
	}
	
	
	/**
	 * 设置information对象（用于reset）
	 * @param info
	 */
	public void setInformation(Information info)
	{
		myInformation = info;
		
		String username = info.getUsername();
		String name = info.getName();
		int grade = info.getGrade();
		int klass = info.getKlass();
		String signature = info.getSignature();
		
		ArrayList<Integer> hobbyNumList = info.getHobbyNumList();
		ArrayList<Integer> adeptnessNumList = info.getAdeptnessNumList();
		
		usernameTextField.setText(username);
		nameTextField.setText(name);
		signatureTextArea.setText(signature);
		
		gradeComboBox.setSelectedIndex(grade);
		classComboBox.setSelectedIndex(klass);
		
		hobbySelectionPanel.setSelectedHobbyNumList(hobbyNumList);
		adeptnessSelectionPanel.setSelectAdeptnessNumList(adeptnessNumList);
	}
	
	private JButton getexitButton(){
		if(exit==null){
			exit=new JButton();
			exit.setIcon(new ImageIcon("images/exit.png"));
			exit.setBounds(new Rectangle(300,0,20,20));
		    exit.setBorder(null);
		    exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		    exit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
			    });
		}
		return exit;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
