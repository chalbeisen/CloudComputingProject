package client;

import java.io.*;
import java.net.*;
import java.util.List;

import sensor.*;

public class ClientsideCommunicationURL {

	private static final int     HTTP_PORT     = 80;
	private static final String  HTTP_PROTOCOL = "http";
	

	private  int     port     = HTTP_PORT;
	private  String  hostName = "1-dot-cloudcomputingprojekt-2018.appspot.com";
	private  String  page     = "/cloudcomputingprojekt";

	public int getPort () {
		return port;
	} // getPort


	public void setPort (int port) {
		this.port = port;
	} // setPort


	public String getHostName () {
		return hostName;
	} // getHostName


	public void setHostName (String hostName) {
		this.hostName = hostName;
	} // setHostName


	public String getPage () {
		return page;
	} // getPage


	public void setPage (String page) {
		this.page = page;
	}	// setPage
	
	
	public void sendSensorDataV2(Sensor sensor) throws IOException {
		// make a POST request
		URL url = new URL (HTTP_PROTOCOL, hostName, port, page);
		HttpURLConnection con = (HttpURLConnection)url.openConnection ();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		
		// send the POST request to the server
		ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
		// send Sensor data
		out.writeObject(sensor);
		
		//read answer from server
		BufferedReader in = new BufferedReader (new InputStreamReader (con.getInputStream ()));
		String line = in.readLine ();
		while (line != null) {
			System.out.println (line);
			line = in.readLine ();
		} // while
		
		out.close();
	}
}
