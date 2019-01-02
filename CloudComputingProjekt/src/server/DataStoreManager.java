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

import com.google.api.client.util.DateTime;

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
	
	public String retrieveSensor(String sensorID) throws UnsupportedEncodingException
	{
		Query q = new Query("sensor").setFilter(new Query.FilterPredicate("sensorID", FilterOperator.EQUAL, sensorID));
		PreparedQuery pq = ds.prepare(q);
		
		Map<String,String> parameters = new HashMap<>();
		for (Entity result : pq.asIterable())
		{
			parameters.put("sensorID", (String)result.getProperty("sensorID"));
			parameters.put("latitude",String.valueOf(result.getProperty("latitude")));
			parameters.put("longitude", String.valueOf(result.getProperty("longitude")));
		}
		return setParameterString(parameters);
	}
	
	public String retrieveSensorData(String sensorDataID)
	{
		return null;
	}
}
