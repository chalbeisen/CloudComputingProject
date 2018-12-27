package sensor;

import java.util.Random;

public class Sensor{
	private String name;
	private int temperature;
	private long id;
	
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
	
	public void setRandomTemperature(int minVal, int maxVal)
	{
		Random r = new Random();
		temperature = (minVal+r.nextInt(maxVal-minVal));
		System.out.println(temperature);
	}
}
