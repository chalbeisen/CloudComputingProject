package server;

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
import com.google.api.client.util.DateTime;

import sensor.Location;

public class DataStoreManager {

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
}
