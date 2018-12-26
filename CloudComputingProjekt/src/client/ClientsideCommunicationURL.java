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
	
	
	public void sendSensorData(Sensor sensor) throws IOException {
		String request = "id="+sensor.getID()+"&temp="+sensor.getTemperature();
		// send GET request to Server 
		URL url = new URL (HTTP_PROTOCOL, hostName, port, page);
		//System.out.println(url.toString());
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		System.out.println(con.getRequestMethod());

		// send the request to the server
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		// send Sensor data
		out.writeBytes(request);
		out.flush();
		
		System.out.println(con.getResponseCode());
		//read answer from server
		BufferedReader in = new BufferedReader (new InputStreamReader (con.getInputStream ()));
		String line = in.readLine ();
		while (line != null) {
			System.out.println (line);
			line = in.readLine ();
		} // while

		out.close();
		in.close();
	}
}
