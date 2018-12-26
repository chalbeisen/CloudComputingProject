package sensor;

import java.util.Random;

public class Sensor{
	private String name;
	private float temperature;
	private long id;
	
	public Sensor(int id)
	{
		this.id = id;
	}// Constructor
	
	public float getTemperature()
	{
		return temperature;
	} //setTemperature
	
	public void setTemperature(float temperature)
	{
		this.temperature = temperature;
	} //setTemperature
	
	public long getID()
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
	
	public void setRandomTemperature(float minVal, float maxVal)
	{
		Random r = new Random();
		temperature = (minVal+(maxVal-minVal)*r.nextFloat());
	}
}
