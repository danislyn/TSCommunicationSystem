package gui.login;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * LoginFrame是登录用的Frame容器
 * 
 */
public class LoginFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;


	/**
	 * 构造
	 */
	LoginFrame(){
		super("TSCS");
	    this.setTitle("TSCS_欢迎您");//框架标题
		this.setIconImage(new ImageIcon("images/icon.png").getImage());//框架icon
	    
	    LoginPane panel =new LoginPane(this);//登录的panel
	    panel.setOpaque(false);//登录的panel设置透明
	    
	    this.getContentPane( ).add("Center",panel);
	    this.setSize(600,400);
	    
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    
	}
	
	/**
	 * 主函数入口
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