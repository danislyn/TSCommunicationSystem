package managers.post;

import java.util.ArrayList;
import java.util.HashMap;

import beans.post.Comment;
import beans.post.Post;


public class PostManager {
	
	private BlockManager studentBlockManager;
	
	private BlockManager majorTeacherBlockManager;
	
	private BlockManager departmentTeacherBlockManager;
	
	private BlockManager adminTeacherBlockManager;
	
	private HashMap<Integer, BlockManager> tagToBlockMap;
	
	private HashMap<Integer, Integer> authorityToTagMap;
	
	private final String username;
	
	private final int authority;
	
	
	/** ���췽��
	 * 
	 * @param username �û�����һ�����룬���ɸı�
	 * @param authority Ȩ�ޣ�һ�����룬���ɸı�
	 */
	public PostManager(String username, int authority)
	{
		this.username = username;
		this.authority = authority;
		
		studentBlockManager = new BlockManager(1);
		majorTeacherBlockManager = new BlockManager(2);
		departmentTeacherBlockManager = new BlockManager(3);
		adminTeacherBlockManager = new BlockManager(4);
		
		tagToBlockMap = new HashMap<Integer, BlockManager>();
		tagToBlockMap.put(1, studentBlockManager);
		tagToBlockMap.put(2, majorTeacherBlockManager);
		tagToBlockMap.put(3, departmentTeacherBlockManager);
		tagToBlockMap.put(4, adminTeacherBlockManager);	
		
		authorityToTagMap = new HashMap<Integer, Integer>();
		authorityToTagMap.put(0, 1);
		authorityToTagMap.put(1, 2);
		authorityToTagMap.put(2, 3);
		authorityToTagMap.put(3, 3);
		authorityToTagMap.put(4, 3);
		authorityToTagMap.put(5, 4);
	}
	
	
	/** �������а��
	 * 
	 * @return true
	 */
	public boolean updateAllBlock()
	{
		studentBlockManager.update();
		majorTeacherBlockManager.update();
		departmentTeacherBlockManager.update();
		adminTeacherBlockManager.update();
		return true;
	}
	
	
	/** ����ָ�����
	 * 
	 * @param tag �����
	 * @return true
	 */
	public boolean updateOneBlock(int tag)
	{
		tagToBlockMap.get(tag).update();
		return true;
	}
	
	
	/** �Ķ�����
	 *  �������+1��
	 * @param tag �����
	 * @param post ����
	 * @return boolean�������ģ�
	 */
	public boolean readPost(int tag, Post post)
	{
		return tagToBlockMap.get(tag).readPost(post);
	}
	
	
	/** ���������ۣ�
	 * 
	 * @param tag �����
	 * @param post ����
	 * @param comment ����
	 * @return boolean
	 */
	public boolean replyPost(int tag, Post post, Comment comment)
	{
		return tagToBlockMap.get(tag).replyPost(username, post, comment);
	}
	
	
	/** ����
	 * 
	 * @param post ����
	 * @return boolean
	 */
	public boolean sendPost(Post post)
	{
		int tag = authorityToTagMap.get(authority);
		
		return tagToBlockMap.get(tag).sendPost(username, authority, post);
	}
	
	
	/** �޸��Լ���������
	 *  ǰ��������post���޸�time
	 * @param post �޸ĺ������
	 * @return boolean
	 */
	public boolean revisePost(Post post)
	{
		int tag = authorityToTagMap.get(authority);
		
		return tagToBlockMap.get(tag).revisePost(username, authority, post);
	}
	
	
	/** ��ȡָ�����������б�������
	 * 
	 * @param tag �����
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> showList(int tag)
	{
		return tagToBlockMap.get(tag).showList();
	}
	
	public ArrayList<Post> getList(int tag)
	{
		return tagToBlockMap.get(tag).getList();
	}
	
	
	/** ��ȡĳ�����ָ������������б�
	 * 
	 * @param tag �����
	 * @param category ���
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchByCategory(int tag, String category)
	{
		return tagToBlockMap.get(tag).searchByCategory(category);
	}
	
	/** ��ȡ���а����ָ������������б�
	 * 
	 * @param category ���
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchGlobalByCategory(String category)
	{
		ArrayList<Post> resultList = studentBlockManager.searchByCategory(category);
		resultList.addAll(majorTeacherBlockManager.searchByCategory(category));
		resultList.addAll(departmentTeacherBlockManager.searchByCategory(category));
		resultList.addAll(adminTeacherBlockManager.searchByCategory(category));
		return resultList;
	}
		
	
	/** ��ȡĳ�����ָ�������������������б�
	 * 
	 * @param tag �����
	 * @param senderName ��������������name��
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchBySenderName(int tag, String senderName)
	{
		return tagToBlockMap.get(tag).searchBySenderName(senderName);
	}
	
	/** ��ȡ���а����ָ�������������������б�
	 * 
	 * @param senderName ��������������name��
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchGlobalBySenderName(String senderName)
	{
		ArrayList<Post> resultList = studentBlockManager.searchBySenderName(senderName);
		resultList.addAll(majorTeacherBlockManager.searchBySenderName(senderName));
		resultList.addAll(departmentTeacherBlockManager.searchBySenderName(senderName));
		resultList.addAll(adminTeacherBlockManager.searchBySenderName(senderName));
		return resultList;
	}

	
}
