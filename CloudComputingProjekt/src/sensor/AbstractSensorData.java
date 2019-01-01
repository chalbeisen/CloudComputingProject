package sensor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public abstract class AbstractSensorData {
	//TODO ID????
	protected String id;
	protected long currentTimeMillis;
	protected double temperature;
	
	public AbstractSensorData(double temperature, String sensorID)
	{
		this.temperature = temperature;
		setCurrentTimeMillis();
		setID(sensorID);
	}
	
	public void setCurrentTimeMillis()
	{
		currentTimeMillis = System.currentTimeMillis();
	}
	
	public long getCurrentTimeMillis()
	{
		return currentTimeMillis;
	}
	
	public double getTemperature()
	{
		return temperature;
	}
	
	public String getID()
	{
		return id;
	}
	
	public void setID(String sensorID)
	{
		id = sensorID+getCurrentTimeMillis();
	}
}
