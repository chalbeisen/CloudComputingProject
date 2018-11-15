package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.FilterOperator;

import com.google.cloud.*;

import sensor.SensorData;
public class EntityRetriever {
	
	public Entity getEntity(long key) throws EntityNotFoundException
	{
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		//Map<Key,Entity>entities =ds.get(new ArrayList(Arrays.asList(key)));
		Key k = KeyFactory.createKey("Sensor", key);
		Entity e1 = ds.get(k);
		return e1;
	}
	@SuppressWarnings("unchecked")
	public List<Float> getTemperature(long key) throws EntityNotFoundException
	{
		Entity e1 = getEntity(key);
		List<Float> sensorData = (List<Float>) e1.getProperty("Temperatur");
		return sensorData;
	}
	
	public SensorData getSensorDataV2(long key) throws EntityNotFoundException
	{
		Entity e1 = getEntity(key);
		return (SensorData)e1.getProperty("Temperatur");
	}
}