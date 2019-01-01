package sensor;

import communication.HttpCommunication;

public abstract class AbstractSensor implements Runnable{

	protected String id;
	protected Location location;
	protected HttpCommunication com;
	protected SensorData data;
	
	public AbstractSensor(String id, Location location, SensorData data, String hostName, String page)
	{
		this.id = id;
		this.location = location;
		this.data = data;
		com = new HttpCommunication();
		com.setHostName(hostName);
		com.setPage(page);
	}
	
	public String getId(){
		return id;
	}
	
	public SensorData getSensorData()
	{
		return data;
	}
	
	public Location getLocation()
	{
		return location;
	}
}
