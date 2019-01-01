package test;

import java.util.Random;

import sensor.Location;
import sensor.Sensor;
import sensor.SensorData;

public class RandomSensorTest {
	public static void main(String[] args) throws Exception
	{
		String sensorID = "sensor1";
		double temperature = getRandomTemperature(-10,30);
		SensorData sensorData = new SensorData(temperature,sensorID);
		
		//Location Xicheng, Beijing, China
		Location location = new Location(39.913818,116.363625);
		String hostName = "cloudcomputingprojekt-2018.appspot.com";
		String page = "/cloudcomputingprojekt";
		Sensor testSensor1 = new Sensor(sensorID,location,sensorData,hostName,page);
		new Thread(testSensor1).start();
	}

	public static double getRandomTemperature(double minVal, double maxVal)
	{
		Random r = new Random();
		return (minVal+r.nextDouble()*(maxVal-minVal));
	}
}
