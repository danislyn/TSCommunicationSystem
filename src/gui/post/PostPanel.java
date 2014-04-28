package gui.post;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.Rectangle;

public class PostPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tb_s = null;
	private JPanel gongzhong = null;
	private JPanel jingpin = null;
	private JPanel fenlei = null;
    private int tag = 0;
    //public static JPanel real = new PostList("学生","公众");
	/**
	 * This is the default constructor
	 */
	public PostPanel(String a) {
		super();
		if(a=="学生")
			tag=1;
		else{
			if(a=="任课老师")
				tag=2;
			else{
				if(a=="教务员")
					tag=3;
				else{
					if(a=="管理员")
						tag=4;
					else 
						tag=5;
				}
			}
		}
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//real.setLayout(null);
		this.setSize(710, 400);
		this.setLayout(null);
		this.add(getTb_s(), null);
	}

	/**
	 * This method initializes tb_s	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTb_s() {
		if (tb_s == null) {
			tb_s = new JTabbedPane();
			tb_s.setBounds(new Rectangle(0, 2, 710, 390));
			tb_s.addTab("公众", null, getGongzhong(), null);
			tb_s.addTab("精品", null, getJingpin(), null);
			tb_s.addTab("分类", null, getFenlei(), null);
		}
		return tb_s;
	}

	/**
	 * This method initializes gongzhong	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getGongzhong() {
		if (gongzhong == null) {
			gongzhong = new PostList(tag,1);//初始化；1表示公众
			gongzhong.setLayout(null);
			
		}
		return gongzhong;
	}

	/**
	 * This method initializes jingpin	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJingpin() {
		if (jingpin == null) {
			jingpin = new PostList(tag,2);//2表示精品
			jingpin.setLayout(null);
			//PostList p = new PostList(currentRole,"精品");
			//p.setBounds(0, 0, 704, 400);
			//jingpin.add(p);
			
		}
		return jingpin;
	}

	/**
	 * This method initializes fenlei	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFenlei() {
		if (fenlei == null) {
			
			fenlei = new PostList(tag,3);//3表示分类
			fenlei.setLayout(null);
			//PostList p = new PostList(currentRole,"分类");
			//p.setBounds(0, 0, 704, 400);
			//fenlei.add(p);
			
		}
		return fenlei;
	}
	
/*public static void main(String[] args){
	PostPanel a = new PostPanel("a");
	JFrame b = new JFrame();
	b.add(a);
	b.setVisible(true);
	b.setSize(710, 400);
	}*/
}  //  @jve:decl-index=0:visual-constraint="23,19"
