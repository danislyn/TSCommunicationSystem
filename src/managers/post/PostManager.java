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
	
	
	/** 构造方法
	 * 
	 * @param username 用户名，一旦传入，不可改变
	 * @param authority 权限，一旦传入，不可改变
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
	
	
	/** 更新所有板块
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
	
	
	/** 更新指定板块
	 * 
	 * @param tag 板块标号
	 * @return true
	 */
	public boolean updateOneBlock(int tag)
	{
		tagToBlockMap.get(tag).update();
		return true;
	}
	
	
	/** 阅读帖子
	 *  （浏览量+1）
	 * @param tag 板块标号
	 * @param post 帖子
	 * @return boolean（不关心）
	 */
	public boolean readPost(int tag, Post post)
	{
		return tagToBlockMap.get(tag).readPost(post);
	}
	
	
	/** 回帖（评论）
	 * 
	 * @param tag 板块标号
	 * @param post 帖子
	 * @param comment 评论
	 * @return boolean
	 */
	public boolean replyPost(int tag, Post post, Comment comment)
	{
		return tagToBlockMap.get(tag).replyPost(username, post, comment);
	}
	
	
	/** 发帖
	 * 
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean sendPost(Post post)
	{
		int tag = authorityToTagMap.get(authority);
		
		return tagToBlockMap.get(tag).sendPost(username, authority, post);
	}
	
	
	/** 修改自己发的帖子
	 *  前置条件：post不修改time
	 * @param post 修改后的帖子
	 * @return boolean
	 */
	public boolean revisePost(Post post)
	{
		int tag = authorityToTagMap.get(authority);
		
		return tagToBlockMap.get(tag).revisePost(username, authority, post);
	}
	
	
	/** 获取指定板块的帖子列表（已排序）
	 * 
	 * @param tag 板块标号
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
	
	
	/** 获取某板块中指定分类的帖子列表
	 * 
	 * @param tag 板块标号
	 * @param category 类别
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchByCategory(int tag, String category)
	{
		return tagToBlockMap.get(tag).searchByCategory(category);
	}
	
	/** 获取所有板块中指定分类的帖子列表
	 * 
	 * @param category 类别
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
		
	
	/** 获取某板块中指定发帖人姓名的帖子列表
	 * 
	 * @param tag 板块标号
	 * @param senderName 发帖人姓名（是name）
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchBySenderName(int tag, String senderName)
	{
		return tagToBlockMap.get(tag).searchBySenderName(senderName);
	}
	
	/** 获取所有板块中指定发帖人姓名的帖子列表
	 * 
	 * @param senderName 发帖人姓名（是name）
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
