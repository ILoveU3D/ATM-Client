package ui;
import java.io.*;
import java.net.*;
public class ATMClient{
	public static void main(String[] args) throws IOException {
		Socket socket=new Socket("127.0.0.1", 8890);
		BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter os=new PrintWriter(socket.getOutputStream());
		String line="ATMRunRequest";
		os.println(line);
		os.flush();
		String reString=is.readLine();
		System.out.println(reString);
		socket.close();
		os.close();
		is.close();
	}
}
