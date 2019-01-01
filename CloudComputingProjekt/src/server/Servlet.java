package server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;

import sensor.Location;

public class Servlet extends HttpServlet{

	DataStoreManager ds =  new DataStoreManager();

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		String sensorID = req.getParameter("sensorID");
		double latitude = Double.parseDouble(req.getParameter("latitude"));
		double longitude = Double.parseDouble(req.getParameter("longitude"));
		String sensorDataID = req.getParameter("sensorDataID");
		double temperature = Double.parseDouble(req.getParameter("temperature"));
		long currentTimeMillis = Long.parseLong(req.getParameter("currentTimeMillis"));
		Date date = new Date(currentTimeMillis);
		
		Key parentKey = ds.createSensorEntity(sensorID, new Location(latitude, longitude));
		ds.createSensorDataEntity(parentKey, sensorDataID, date, temperature);
		try {
			writeResponse(resp,sensorID,latitude,longitude,sensorDataID,date,temperature);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//DEBUG
	public void writeResponse(HttpServletResponse resp, String sensorID, double latitude, double longitude, String sensorDataID, Date date, double temperature) throws IOException
	{
		resp.getWriter().println("SERVER REPSONSE:");
		resp.getWriter().println("sensorID: "+ sensorID);
		resp.getWriter().println("latitude: "+ latitude);
		resp.getWriter().println("longitude: "+ longitude);
		resp.getWriter().println("sensorDataID: "+ sensorDataID);
		resp.getWriter().println("date: "+ date);
		resp.getWriter().println("temperature: "+ temperature);
	}
}
