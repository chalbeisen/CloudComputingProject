package sensor;
import java.io.Serializable;
import java.util.*;

public class Sensor implements Serializable{
	private SensorData sensordata;
	private String name;
	private List<Float> temperature;
	private long id;
	
	public Sensor(int id)
	{
		temperature = new ArrayList<Float>();
		this.id = id;
	}// Constructor
	
	public void setSensorData(int minVal, int maxVal, int amount)
	{
		for(int i=0; i<amount;i++)
		{
			SensorData s = new SensorData(minVal,maxVal);
			temperature.add(s.getValue());
		}
	}// setSensorData
	
	
	public long getID()
	{
		return id;
	}
	
	public List<Float> getTemperature()
	{
		return temperature;
	}// getSensorData
	
	public void setName(String name)
	{
		this.name = name;
	} // setName
	
	public String getName()
	{
		return name;
	} // getName	
}
