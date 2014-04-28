package gui.login;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * LoginFrame�ǵ�¼�õ�Frame����
 * 
 */
public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;


	/**
	 * ����
	 */
	LoginFrame(){
		super("TSCS");
	    this.setTitle("TSCS_��ӭ��");//��ܱ���
		this.setIconImage(new ImageIcon("images/icon.png").getImage());//���icon
	    
	    LoginPane panel =new LoginPane(this);//��¼��panel
	    panel.setOpaque(false);//��¼��panel����͸��
	    
	    this.getContentPane( ).add("Center",panel);
	    this.setSize(600,400);
	    
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    
	}
	
	/**
	 * ���������
	 * @param args
	 */
	public static void main(String args[]){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginFrame frame =new LoginFrame();
		frame.setVisible(true);
	}

}