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

import sensor.Location;
import sensor.Sensor;
import sensor.SensorData;

public class HttpCommunication {

	private static final int     HTTP_PORT     = 80;
	private static final String  HTTP_PROTOCOL = "http";
	

	private  int     port     = HTTP_PORT;
	private  String  hostName = "cloudcomputingprojekt-2018.appspot.com";
	private  String  page     = "/cloudcomputingprojekt";
	private  HttpURLConnection con;

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
	
	public void connect() throws IOException
	{
		URL url = new URL (HTTP_PROTOCOL, hostName, port, page);
		con = (HttpURLConnection)url.openConnection();
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
	
	public String readResponse() throws IOException
	{
		BufferedReader in = new BufferedReader (new InputStreamReader (con.getInputStream ()));
		String line = in.readLine ();
		while (line != null) {
			line = in.readLine ();
		}
		return line;
	}
	
	public String setParameters(Sensor sensor) throws UnsupportedEncodingException
	{
		Map<String,String> parameters = new HashMap<>();
		parameters.put("sensorID", sensor.getId());
		parameters.put("longitude", Double.toString(sensor.getLocation().getLongitude()));
		parameters.put("latitude", Double.toString(sensor.getLocation().getLatitude()));
		parameters.put("sensorDataID", sensor.getSensorData().getID());
		parameters.put("currentTimeMillis", Long.toString(sensor.getSensorData().getCurrentTimeMillis()));
		parameters.put("temperature", Double.toString(sensor.getSensorData().getTemperature()));
		
		return setParameterString(parameters);
	}
	
	public String setParameterString(Map<String,String> parameters) throws UnsupportedEncodingException
	{
	    StringBuilder result = new StringBuilder();
	    	 
	    for (Map.Entry<String, String> entry : parameters.entrySet()) {
	    	 result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	    	 result.append("=");
	    	 result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	    	 result.append("&");
	    }
	    	 
	    String resultString = result.toString();
	    
	    return resultString;
	}
	
	public void printDebugInformation(int responseCode, String URL, String parameters)
	{
		System.out.println("Communication DEBUG Information");
		System.out.println("URL: "+URL);
		System.out.println("Response Code: "+responseCode);
		System.out.println("parameter "+ parameters);
	}
}
