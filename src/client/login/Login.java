package client.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


import server.reflection.Call;

import client.users.Person;


public class Login {
	
	public static Socket socket = null;
	
	public static OutputStream output = null;
	
	public static ObjectOutputStream objOutput = null;
	
	public static InputStream input = null;
	
	public static ObjectInputStream objInput = null;
	
	public static Call resultCall = null;
	
	//public static Thread readingThread = null;
	
	public static Person person = null;	
	
	
	public static String ip = null;
	
	static
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("connect.ini")));
			
			ip = br.readLine();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("connect.ini not found");
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("read from connect.ini exception");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ������socket
	 */
	public static void start(){
		//connect to server
		try
		{
			socket=new Socket(ip,8091);
			
			output=socket.getOutputStream();
			objOutput=new ObjectOutputStream(output);
			input=socket.getInputStream();
			objInput=new ObjectInputStream(input);
			
			//readingThread=new Thread(new ReadingThread());
			//readingThread.start();
			
			System.out.println("Connected successfully!");
		}
		catch(IOException ex)
		{
			System.out.println("Connecting error !");
			//terminate
			//ex.printStackTrace();
		}
	}
	
	/**
	 * ���͵�������
	 * @param call
	 */
	public static void writeCall(Call call)
	{
		try
		{
			objOutput.writeObject(call);
			//while(Login.resultCall==null){}
		}
		catch(IOException ex){}
	}
	
	/**
	 * ��ȡ���ý��������ֵ��
	 */
	public static void readCall()
	{
		resultCall=null;
		try {
			resultCall = (Call) objInput.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////
	//�����ļ�����
	public static Socket socket2 = null;
	
	public static OutputStream output2 = null;
	
	public static ObjectOutputStream objOutput2 = null;
	
	public static InputStream input2 = null;
	
	public static ObjectInputStream objInput2 = null;
	
	public static Call resultCall2 = null;
	
	/**
	 * �����ļ�����ר��socket
	 */
	public static void start2()
	{
		try {
			socket2=new Socket(ip,8091);
		
			output2=socket2.getOutputStream();
			objOutput2=new ObjectOutputStream(output2);
			input2=socket2.getInputStream();
			objInput2=new ObjectInputStream(input2);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ���ͣ��ļ�����ר�ã���������
	 * @param call
	 */
	public static void writeCall2(Call call)
	{
		try
		{
			objOutput2.writeObject(call);
		}
		catch(IOException ex){}
	}
	
	/**
	 * ��ȡ���ļ�����ר�ã����ý��������ֵ��
	 */
	public static void readCall2()
	{
		resultCall2=null;
		try {
			resultCall2 = (Call) objInput2.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر��ļ�����socket
	 */
	public static void close2()
	{
		try {						
			objOutput2.close();
			output2.close();
			objInput2.close();
			input2.close();			
			socket2.close();
			
			objOutput2 = null;
			output2 = null;
			objInput2 = null;
			input2 = null;
			socket2 = null;
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	//����������ʾ
	public static Socket socket3 = null;
	
	public static OutputStream output3 = null;
	
	public static ObjectOutputStream objOutput3 = null;
	
	public static InputStream input3 = null;
	
	public static ObjectInputStream objInput3 = null;
	
	public static Call resultCall3 = null;
	
	/**
	 * ����������ʾר��socket
	 */
	public static void start3()
	{
		try {
			socket3=new Socket(ip,8091);
		
			output3=socket3.getOutputStream();
			objOutput3=new ObjectOutputStream(output3);
			input3=socket3.getInputStream();
			objInput3=new ObjectInputStream(input3);
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ���ͣ�������ʾר�ã���������
	 * @param call
	 */
	public static void writeCall3(Call call)
	{
		try
		{
			objOutput3.writeObject(call);
		}
		catch(IOException ex){}
	}
	
	/**
	 * ��ȡ��������ʾר�ã����ý��������ֵ��
	 */
	public static void readCall3()
	{
		resultCall3=null;
		try {
			resultCall3 = (Call) objInput3.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر�������ʾsocket
	 */
	public static void close3()
	{
		try {						
			objOutput3.close();
			output3.close();
			objInput3.close();
			input3.close();			
			socket3.close();
			
			objOutput3 = null;
			output3 = null;
			objInput3 = null;
			input3 = null;			
			socket3 = null;
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
