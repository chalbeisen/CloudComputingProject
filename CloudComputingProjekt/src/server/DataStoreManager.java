package server;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

import communication.AbstractParameterSetter;

import sensor.Location;

public class DataStoreManager extends AbstractParameterSetter{

	private DatastoreService ds;
	
	public DataStoreManager()
	{
		ds=DatastoreServiceFactory.getDatastoreService();
	} 
	
	public Key createSensorEntity(String sensorID, Location location)
	{
		Entity sensorEntity = new Entity("sensor",sensorID);
		
		sensorEntity.setProperty("sensorID",sensorID);
		sensorEntity.setProperty("latitude",location.getLatitude());
		sensorEntity.setProperty("longitude", location.getLongitude());
		
		Key sensorKey = ds.put(sensorEntity);
		return sensorKey;
	}
	
	public void createSensorDataEntity(Key parentKey, String sensorDataID, Date date, double temperature)
	{
		Entity sensorDataEntity = new Entity("sensorData",parentKey);
		

		sensorDataEntity.setProperty("sensorDataID", sensorDataID);
		sensorDataEntity.setProperty("date",date);
		sensorDataEntity.setProperty("temperature",temperature);
		
		ds.put(sensorDataEntity);
	}
	
	public String retrieveSensor()
	{
		Query q = new Query("sensor");
		PreparedQuery pq = ds.prepare(q);
		
		StringBuilder sb = new StringBuilder();
		for (Entity result : pq.asIterable())
		{
			sb.append(String.valueOf(result.getProperty("latitude")));
			sb.append(",");
			sb.append(String.valueOf(result.getProperty("longitude")));
			sb.append(";");
		}
		return sb.toString();
	}
	
	public String retrieveSensorData(String sensorDataID)
	{
		return null;
	}
}
