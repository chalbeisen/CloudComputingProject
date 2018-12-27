package client;

import java.util.List;

import sensor.Sensor;
import sensor.SensorData;

public class CommunicationURLTest {
	public static void main(String[] args) throws Exception
	{
		ClientsideCommunicationURL com = new ClientsideCommunicationURL();
		//com.setHostName("localhost");
		//com.setPort(8888);
		int id = 2;
		Sensor sensor = new Sensor(id);
		sensor.setRandomTemperature(10,40);
		com.sendSensorData(sensor);
		sensor.setRandomTemperature(10,40);
		com.sendSensorData(sensor);
	}
}
