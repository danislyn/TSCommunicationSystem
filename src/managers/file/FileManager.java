package managers.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import server.reflection.Call;
import client.login.Login;

import beans.file.FileBaseInfo;

public class FileManager {
	
	private ArrayList<FileBaseInfo> fileList;
	
	private final String username;
	
	
	/** ���췽��
	 * 
	 * @param username �û�����һ�����룬�����޸�
	 */
	public FileManager(String username)
	{
		this.username = username;
		update();
	}
	
	
	/** ���·���������fileList
	 * 
	 * @return true
	 */
	@SuppressWarnings("unchecked")
	public boolean update()
	{
		Call call = new Call("server.interfaces.FileOperationInterface", "downloadSharedFileList", 
							new Class[]{}, new Object[]{});
		Login.writeCall(call);
		Login.readCall();
		
		fileList = (ArrayList<FileBaseInfo>) Login.resultCall.getResult();
		
		return true;
	}
	
	
	/** �ϴ��ļ�
	 * 
	 * @return boolean
	 */
	public boolean uploadFile()
	{
		boolean result = false;
		boolean success = true;
		
		JFileChooser fileChooser = new JFileChooser();		
		fileChooser.setDialogTitle("ѡ���ļ�");
		int choice = fileChooser.showOpenDialog(null);
		
		if(choice == JFileChooser.APPROVE_OPTION)
		{
			int length=0;
			byte[] sendBytes=new byte[1024];
			
			File file = fileChooser.getSelectedFile();
			
			int fileSize = (int) (file.length() / 1024);
			if(fileSize*1024 < file.length())
			{
				fileSize++;
			}
			
			FileBaseInfo fileBaseInfo = new FileBaseInfo();
			fileBaseInfo.setContributorUsername(username);
			fileBaseInfo.setFileName(file.getName());  //
			fileBaseInfo.setFileSize(fileSize);
			
			//�����ļ�����socket
			Login.start2();
			
			try {
				java.io.FileInputStream fis = new FileInputStream(file);
		
				int count = 0;
				Login.resultCall=null;
				
				while((length = fis.read(sendBytes,0,sendBytes.length))>0)
				{
					count++;
					if(count == 1)
					{
						Call call = new Call("server.interfaces.FileOperationInterface", "uploadSharedFile", 1, fileBaseInfo);
						//Login.writeCall(call);
						Login.writeCall2(call);
						//Login.readCall();
						Login.readCall2();
						//if(Login.resultCall.getTag() == false)  //feedbcak fail
						if(Login.resultCall2.getTag() == false)  //feedbcak fail
						{
							success = false;
							break;
						}
					}
					
					try {
						//Login.output.write(sendBytes);
						//Login.output.flush();
						Login.output2.write(sendBytes);
						Login.output2.flush();
					} catch (IOException e) {
						System.out.println("Exception when output.write");
						e.printStackTrace();
					}
				}
	
				fis.close();
				
				if(success == true)
				{
					//�ȴ���Ӧ
					//Login.readCall();
					Login.readCall2();
					//if((Boolean)Login.resultCall.getResult() == true)
					if((Boolean)Login.resultCall2.getResult() == true)
					{
						//update
						fileList.add(fileBaseInfo);
						
						result = true;
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
				
			} finally {				
				//�ر��ļ�����socket
				Login.close2();
			}
		
		}
		return result;
	}
	
	
	/** �����ļ�
	 * 
	 * @param fileBaseInfo �ļ���Ϣ
	 * @return boolean
	 */
	public boolean downloadFile(FileBaseInfo fileBaseInfo)
	{	
		boolean result = false;
		
		JFileChooser fileChooser = new JFileChooser();		
		fileChooser.setDialogTitle("���Ϊ");
		fileChooser.setSelectedFile(new File(fileBaseInfo.getFileName()));
		int choice = fileChooser.showSaveDialog(null);
		
		if(choice == JFileChooser.APPROVE_OPTION)
		{
			File file = fileChooser.getSelectedFile();
		
			byte[] fileBytes = new byte[1024];
			int length=0;
			int count = fileBaseInfo.getFileSize();
			
			//�����ļ�����socket
			Login.start2();
			
			//�ļ�call
			Call call = new Call("server.interfaces.FileOperationInterface", "downloadSharedFile", 2, fileBaseInfo);			
			//Login.writeCall(call);
			Login.writeCall2(call);
			
			System.out.println ("��ʼ��������...");
	
			try {
				java.io.FileOutputStream fos = new FileOutputStream(file);
				
				//while(count>0 && (length = Login.input.read(fileBytes,0,fileBytes.length))>0)
				while(count>0 && (length = Login.input2.read(fileBytes,0,fileBytes.length))>0)
				{
					count--;
				
					fos.write(fileBytes);
					fos.flush();
				}			
				System.out.println("wait.....");
				fos.close();
				
				//�ȴ���Ӧ
				//Login.readCall();
				
				//if(Login.resultCall.getTag() == true)
				//{
					//System.out.println ("���سɹ�");
					//System.out.println("FileSize: " + fileBaseInfo.getFileSize() + "KB");
				//}
				
				//fos.close();
				
				//inputstream cruppted!!!!!!!!!!!!!!!!!!!!!!!
				//Login.input=Login.socket.getInputStream();
				//Login.objInput=new ObjectInputStream(Login.input);
				
				result = true;;
				
			} catch(Exception e) {
				e.printStackTrace();
				result = false;
				
			} finally {
				//�ر��ļ�����socket
				Login.close2();
			}
		
		}
		return result;
	}
	
	
	/** ��ȡ�����ļ���Ϣ�б���������
	 * 
	 * @return ArrayList<FileBaseInfo>
	 */
	public ArrayList<FileBaseInfo> showList()
	{
		return fileList;
	}
	
}
