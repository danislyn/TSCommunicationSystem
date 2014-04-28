package gui.information;

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
import java.awt.Point;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import client.login.Login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RevisePasswordFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel oldLabel = null;
	private JLabel newLabel = null;
	private JLabel confirmLabel = null;
	private JPasswordField oldPasswordField = null;
	private JPasswordField newPasswordField = null;
	private JPasswordField confirmPasswordField = null;
	private JButton okButton = null;
	private JButton cancelButton = null;
	private JLabel infoLabel = null;
	private JButton exit=null;

	/**
	 * This is the default constructor
	 */
	public RevisePasswordFrame() {
		super();
		this.setUndecorated(true);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(345, 242);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			infoLabel = new JLabel();
			infoLabel.setBounds(new Rectangle(107, 88, 187, 17));
			infoLabel.setForeground(Color.red);
			infoLabel.setText("长度不得超过16位数字或字母");
			confirmLabel = new JLabel();
			confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
			confirmLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			confirmLabel.setLocation(new Point(17, 114));
			confirmLabel.setSize(new Dimension(70, 30));
			confirmLabel.setText("确认密码");
			newLabel = new JLabel();
			newLabel.setText("新密码");
			newLabel.setSize(new Dimension(70, 30));
			newLabel.setHorizontalAlignment(SwingConstants.CENTER);
			newLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			newLabel.setLocation(new Point(17, 57));
			oldLabel = new JLabel();
			oldLabel.setHorizontalAlignment(SwingConstants.CENTER);
			oldLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			oldLabel.setLocation(new Point(17, 16));
			oldLabel.setSize(new Dimension(70, 30));
			oldLabel.setText("原密码");
			jContentPane = new JPanel(){
				{
					setOpaque(false);
				}
				
				public void paintComponent(Graphics g){
			    	g.setColor(Color.blue); 
			        ImageIcon img=null;
					img = new ImageIcon("images/password.jpg");
					g.drawImage(img.getImage(),0,0,null); 
			    	
			    }
			};
			jContentPane.setLayout(null);
			jContentPane.add(oldLabel, null);
			jContentPane.add(newLabel, null);
			jContentPane.add(confirmLabel, null);
			jContentPane.add(getOldPasswordField(), null);
			jContentPane.add(getNewPasswordField(), null);
			jContentPane.add(getConfirmPasswordField(), null);
			jContentPane.add(getOkButton(), null);
			jContentPane.add(getCancelButton(), null);
			jContentPane.add(infoLabel, null);
			jContentPane.add(getexitButton(),null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes oldPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getOldPasswordField() {
		if (oldPasswordField == null) {
			oldPasswordField = new JPasswordField();
			oldPasswordField.setLocation(new Point(107, 16));
			oldPasswordField.setSize(new Dimension(190, 30));
		}
		return oldPasswordField;
	}

	/**
	 * This method initializes newPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getNewPasswordField() {
		if (newPasswordField == null) {
			newPasswordField = new JPasswordField();
			newPasswordField.setLocation(new Point(107, 57));
			newPasswordField.setSize(new Dimension(190, 30));
		}
		return newPasswordField;
	}

	/**
	 * This method initializes confirmPasswordField	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getConfirmPasswordField() {
		if (confirmPasswordField == null) {
			confirmPasswordField = new JPasswordField();
			confirmPasswordField.setLocation(new Point(107, 114));
			confirmPasswordField.setSize(new Dimension(190, 30));
		}
		return confirmPasswordField;
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
			okButton.setText("确定");
			okButton.setSize(new Dimension(80, 30));
			okButton.setLocation(new Point(74, 164));
			okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					String oldPassword = oldPasswordField.getText();
					String newPassword = newPasswordField.getText();
					String confirmPassword = confirmPasswordField.getText();
					
					if(oldPassword.length()==0 || newPassword.length()==0 || confirmPassword.length()==0)
					{
						JOptionPane.showMessageDialog(null, "输入不能为空", "提示",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					if(newPassword.equals(confirmPassword) == false)
					{
						JOptionPane.showMessageDialog(null, "两次密码输入不一致", "提示",JOptionPane.INFORMATION_MESSAGE);
						newPasswordField.setText("");
						confirmPasswordField.setText("");
						return;
					}
					
					if(oldPassword.equals(Login.person.getPassword()) == false)
					{
						JOptionPane.showMessageDialog(null, "原密码不正确", "提示",JOptionPane.INFORMATION_MESSAGE);
						oldPasswordField.setText("");
						return;
					}
					
					//以上检测都成功
					if(Login.person.revisePassword(oldPassword, newPassword, confirmPassword) == true)
					{
						JOptionPane.showMessageDialog(null, "修改成功", "提示",JOptionPane.INFORMATION_MESSAGE);
						dispose();
						return;
					}
					else
					{
						JOptionPane.showMessageDialog(null, "修改失败", "提示",JOptionPane.INFORMATION_MESSAGE);
						reset();
						return;
					}					
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
			cancelButton.setText("取消");
			cancelButton.setSize(new Dimension(80, 30));
			cancelButton.setLocation(new Point(178, 164));
			cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					//消失
					dispose();
				}
			});
		}
		return cancelButton;
	}
	
	private JButton getexitButton(){
		if(exit==null){
			exit=new JButton();
			exit.setIcon(new ImageIcon("images/exit.png"));
			exit.setBounds(new Rectangle(325,0,20,20));
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
	
	
	/**
	 * 重置
	 */
	public void reset()
	{
		oldPasswordField.setText("");
		newPasswordField.setText("");
		confirmPasswordField.setText("");
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
