package gui.contact;

public class ListItem {
	
	/**
	 * 每个list项定义
	 */
	private String isOnicon;
	private String userno;
	private String name;
	private String checkicon;
	
	/**
	 * 构造函数
	 * @param iO  是否在线的图片的路径
	 * @param un  用户帐号
	 * @param n   用户姓名
	 * @param ck  查看的图片的路径
	 */
	public ListItem(String iO,String un,String n,String ck){
		this.isOnicon=iO;
		this.userno=un;
		this.name=n;
		this.checkicon=ck;
	}
	
	/**
	 * 
	 * @param iO
	 */
	public void setisOnicon(String iO){
		this.isOnicon=iO;
	}
	
	/**
	 * 
	 * @param un
	 */
	public void setuserno(String un){
		this.userno=un;
	}
	
	/**
	 * 
	 * @param n
	 */
	public void setname(String n){
		this.name=n;
	}
	
	/**
	 * 
	 * @param ck
	 */
	public void setcheckicon(String ck){
		this.checkicon=ck;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getisOnicon(){
		return this.isOnicon;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getuserno(){
		return this.userno;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getname(){
		return this.name;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getcheckicon(){
		return this.checkicon;
	}
	
	/**
	 * 
	 */
	public String toString(){
		return this.userno+"  "+this.name;
	}
}
