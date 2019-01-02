package communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import sensor.Location;
import sensor.Sensor;
import sensor.SensorData;

public class HttpCommunication extends AbstractParameterSetter{

	private static final int     HTTP_PORT     = 80;
	private static final String  HTTP_PROTOCOL = "http";
	

	private  int     port     = HTTP_PORT;
	private  String  hostName = "cloudcomputingprojekt-2018.appspot.com";
	private  String  page     = "/cloudcomputingprojekt";
	private  HttpURLConnection con;
	private  boolean isConnected = false;

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
	
	//TODO: disconnect?
	public void connect() throws IOException
	{
		if(!isConnected)
		{
			URL url = new URL (HTTP_PROTOCOL, hostName, port, page);
			con = (HttpURLConnection)url.openConnection();
			isConnected = true;
		}
	}
	
	public void disconnect()
	{
		if(isConnected)
		{
			con.disconnect();
			isConnected = false;
		}
	}
	
	public void sendSensorData(Sensor sensor) throws IOException{
		String query = "";
		try {
			query = setParameters(sensor);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// set POST request
		connect();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		
		// send the request to the server
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(query);
		out.flush();
		
		//DEBUG 
		printDebugInformation(con.getResponseCode(),con.getURL().toString(),query);
		
		out.close();
	}
	
	//TODO : löschen??? 
	//TODO: UnsupportedEncodingException
	public void retrieveSensors() throws IOException 
	{
		Map<String,String> parameters = new HashMap<>();
		parameters.put("option", "retrieveSensor");
		String query="";
		try {
			query = setParameterString(parameters);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// set GET request
		connect();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
				
		// send the request to the server
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(query);
		out.flush();
		
		System.out.println("Communication DEBUG Information");
		System.out.println("Response Code: "+con.getResponseCode());
		System.out.println("parameter "+ query);
				
		//DEBUG 
		readResponse();
				
		//out.close();
	}
	
	public void readResponse() throws IOException
	{
		BufferedReader in = new BufferedReader (new InputStreamReader (con.getInputStream ()));
		String line = in.readLine ();
		while (line != null) {
			System.out.println(line);
			line = in.readLine ();
		}
	}
	
	//TODO : löschen???
	public void printDebugInformation(int responseCode, String URL, String parameters)
	{
		System.out.println("Communication DEBUG Information");
		System.out.println("URL: "+URL);
		System.out.println("Response Code: "+responseCode);
		System.out.println("parameter "+ parameters);
	}
}
