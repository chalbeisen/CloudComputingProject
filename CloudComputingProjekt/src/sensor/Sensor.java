package sensor;

import java.time.LocalTime;
import java.util.Random;

public class Sensor{
	private String name;
	private int temperature;
	private int id;
	private long timestamp;
	
	public Sensor(int id)
	{
		this.id = id;
	}// Constructor
	
	public int getTemperature()
	{
		return temperature;
	} //setTemperature
	
	public void setTemperature(int temperature)
	{
		this.temperature = temperature;
	} //setTemperature
	
	public int getID()
	{
		return id;
	}// getID
	
	public void setName(String name)
	{
		this.name = name;
	} // setName
	
	public String getName()
	{
		return name;
	} // getName
	
	public void setTimestamp()
	{
		this.timestamp = System.currentTimeMillis();
	} // setTimestamp
	
	public long getTimestamp()
	{
		return timestamp;
	}
	
	public void setRandomTemperature(int minVal, int maxVal)
	{
		Random r = new Random();
		temperature = (minVal+r.nextInt(maxVal-minVal));
		System.out.println(temperature);
	}
}
