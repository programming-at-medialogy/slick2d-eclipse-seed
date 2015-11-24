import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkHelper {
	
	public NetworkHelper() {
		System.out.println("Checking hosts");
		String localIp = null;
		
		try {
			localIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(localIp);
		String[] ipTemp = localIp.split("\\."); 
		//System.out.println(ipTemp[0]);
		String ip = "";
		
		for(int i = 0; i < ipTemp.length - 1; i++) {
			
			ip += ipTemp[i] + ".";
			
		}
		
		//System.out.println(ip);
		
		checkHosts(ip);
	}

	public static void main(String[] args) {
		NetworkHelper net = new NetworkHelper();
	}
	
	public void checkHosts(String subnet){
	   for (int i=0;i<255;i++){
	       String host=subnet + i;
	       Thread thread = new Thread(new HostChecker(host));
	       thread.start();
       }
   }
	
	public class HostChecker implements Runnable {
		boolean isReal;
		int timeout;
		String host;
		
		HostChecker(String host) {
			isReal = true;
			timeout = 5000;
			this.host = host;
		}
		
		@Override
		public void run() {
			Socket socket = new Socket();
		    try {
				socket.connect(new InetSocketAddress(host, 80), timeout);
				socket.setSoTimeout(100);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				if (e.getMessage().equals("connect timed out"))
					isReal = false;
				else if (e.getMessage().equals("Connection refused: connect"))
					isReal = false;
				else e.printStackTrace();
			} 
		      
		    if (isReal) 
		    	System.out.println("Found host: " + host);
		    
		    try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}