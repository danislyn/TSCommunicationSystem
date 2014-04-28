package client.login;

import java.io.IOException;

import server.reflection.Call;


public class ReadingThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Login.resultCall = (Call) Login.objInput.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
