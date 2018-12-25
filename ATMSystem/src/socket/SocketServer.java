package socket;
import java.io.*;
import java.net.*;
import ui.MainPanel;
public class SocketServer extends Thread{
	public static void main(String[] args) throws IOException {
		System.out.println("·þÎñÆ÷µÈ´ý...");
		ServerSocket serverSocket=new ServerSocket(8890);
		while(true) {
			Socket socket=serverSocket.accept();
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			String line = in.readLine();
			if(line.equals("ATMRunRequest")) {
				new SocketServer().start();
				os.println("Accepted");
				os.flush();
			}
		}
	}
	@Override
	public void run() {
		new MainPanel();
	}
}
