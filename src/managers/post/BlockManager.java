package managers.post;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import client.login.Login;

import server.reflection.Call;

import beans.post.Comment;
import beans.post.Post;

public class BlockManager {
	
	private ArrayList<Post> postList;
	
	private final int tag;
	
	
	/** 构造方法
	 * 
	 * @param tag 版本标号，一旦传入，不可改变
	 */
	public BlockManager(int tag) 
	{
		this.tag = tag;
		update();
	}
	
	
	/** 更新方法，更新postList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.PostOperationInterface", "getPostList", 
							new Class[]{int.class}, new Object[]{tag});
		Login.writeCall(call);
		Login.readCall();
		
		postList = (ArrayList<Post>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** 阅读帖子
	 *  （浏览量+1）
	 * @param post 帖子
	 * @return boolean（不关心）
	 */
	public boolean readPost(Post post)
	{
		Call call = new Call("server.interfaces.PostOperationInterface", "readPost", 
							new Class[]{int.class, String.class, Calendar.class}, 
							new Object[]{tag, post.getSenderUsername(), post.getTime()});
		Login.writeCall(call);
		Login.readCall();
		
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update
			int index = getIndexOfPost(post);
			
			if(index != -1)
			{
				post.setReadSum(post.getReadSum()+1);
				postList.set(index, post);
			}		
			return true;
		}
		return false;
	}
	
	
	/** 回帖（评论）
	 * 
	 * @param username 回帖人（主语）
	 * @param post 帖子
	 * @param comment 评论
	 * @return boolean
	 */
	public boolean replyPost(String username, Post post, Comment comment)
	{
		Call call = new Call("server.interfaces.PostOperationInterface", "replyPost", 
							new Class[]{String.class, int.class, String.class, Calendar.class, Comment.class}, 
							new Object[]{username, tag, post.getSenderUsername(), post.getTime(), comment});
		Login.writeCall(call);
		Login.readCall();
		
		Calendar resultCalendar = (Calendar)Login.resultCall.getResult();
		//如果操作成功
		if(resultCalendar != null)
		{
			//update
			int index = getIndexOfPost(post);
			
			if(index != -1)
			{
				comment.setTime(resultCalendar);
				post.addComment(comment);
				postList.set(index, post);
			}
			return true;
		}
		return false;
	}
	
	
	/** 发帖
	 * 
	 * @param username 发帖人（主语）
	 * @param authority 发帖人权限
	 * @param post 帖子
	 * @return boolean
	 */
	public boolean sendPost(String username, int authority, Post post)
	{
		Call call = new Call("server.interfaces.PostOperationInterface", "sendPost", 
							new Class[]{String.class, int.class, Post.class}, 
							new Object[]{username, authority, post});
		Login.writeCall(call);
		Login.readCall();
		
		Calendar resultCalendar = (Calendar)Login.resultCall.getResult();
		//如果操作成功
		if(resultCalendar != null)
		{
			//update
			post.setTime(resultCalendar);
			postList.add(post);
			
			return true;
		}
		return false;
	}
	
	
	/** 修改自己发的帖子
	 *  前置条件：post不修改time
	 * @param username 发帖人（主语）
	 * @param authority 发帖人权限
	 * @param post 修改后的帖子
	 * @return boolean
	 */
	public boolean revisePost(String username, int authority, Post post)
	{
		Call call = new Call("server.interfaces.PostOperationInterface", "revisePost", 
							new Class[]{String.class, int.class, Post.class}, 
							new Object[]{username, authority, post});
		Login.writeCall(call);
		Login.readCall();
		
		if((Boolean)Login.resultCall.getResult() == true)
		{
			//update 
			int index = getIndexOfPost(post);
			
			if(index != -1)
			{
				postList.set(index, post);
			}
			return true;
		}
		return false;
	}
	
	
	/** 获取该板块所有帖子列表（已排序）
	 * 
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> showList()
	{
		sort();
		return postList;
	}
	
	public ArrayList<Post> getList()
	{
		return postList;
	}
	
	
	/** 获取指定分类的帖子列表
	 * 
	 * @param category 类别
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchByCategory(String category)
	{
		ArrayList<Post> searchList = new ArrayList<Post>();
		
		for(int i=0; i<postList.size(); i++)
		{
			Post post = postList.get(i);
			
			//set tag
			post.setTag(tag);

			if(post.getCategory().equals(category))
			{
				searchList.add(post);
			}
		}
		
		return searchList;
	}
	
	
	/** 获取指定发帖人姓名的帖子列表
	 * 
	 * @param senderName 发帖人姓名（是name）
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> searchBySenderName(String senderName)
	{
		ArrayList<Post> searchList = new ArrayList<Post>();
		
		for(int i=0; i<postList.size(); i++)
		{
			Post post = postList.get(i);
			
			//set tag
			post.setTag(tag);
			
			if(post.getSenderName().equals(senderName))
				searchList.add(post);
		}
		
		return searchList;
	}
	
	
	protected void sort()
	{
		ArrayList<Post> topList = new ArrayList<Post>();
		ArrayList<Post> nextList = new ArrayList<Post>();
		
		for(int i=0; i<postList.size(); i++)
		{
			Post post = postList.get(i);		
			
			if(post.getIsTop() == true)
				topList.add(post);
			else
				nextList.add(post);
		}
		
		sortByTime(topList);
		sortByTime(nextList);
		
		postList = topList;
		postList.addAll(nextList);
	}

/*	protected void sortByHotness()
	{
		
	}*/
	
	private void sortByTime(ArrayList<Post> list)
	{
		Collections.sort(list, new Comparator<Post>()
				{
					@Override
					public int compare(Post post1, Post post2) {
						// TODO Auto-generated method stub
						
						Calendar time1 = post1.getTime();
						Calendar time2 = post2.getTime();
						
						if(time1.after(time2) == true)
						{
							return -1;
						}
						else if(time1.before(time2) == true)
						{
							return 1;
						}
						else 
						{
							return 0;
						}
					}
				});
	}
	
	
	private int getIndexOfPost(Post post)
	{
		for(int i=0; i<postList.size(); i++)
		{
			Post post2 = postList.get(i);
			
			if(post2.getSenderUsername().equals(post.getSenderUsername()) && post2.getTime().equals(post.getTime()))
				return i;
		}
		return -1;
	}
	
}
