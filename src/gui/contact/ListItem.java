package gui.contact;

public class ListItem {
	
	/**
	 * ÿ��list���
	 */
	private String isOnicon;
	private String userno;
	private String name;
	private String checkicon;
	
	/**
	 * ���캯��
	 * @param iO  �Ƿ����ߵ�ͼƬ��·��
	 * @param un  �û��ʺ�
	 * @param n   �û�����
	 * @param ck  �鿴��ͼƬ��·��
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
