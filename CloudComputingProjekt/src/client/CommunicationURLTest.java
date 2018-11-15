package client;

import java.util.List;

import sensor.Sensor;
import sensor.SensorData;

public class CommunicationURLTest {
	public static void main(String[] args) throws Exception
	{
		ClientsideCommunicationURL com = new ClientsideCommunicationURL();
		com.setHostName( "cloudcomputingprojekt-2018.appspot.com");
		//com.setPort(8888);
		int id = 2;
		Sensor sensor = new Sensor(id);
		sensor.setSensorData(10, 40, 3);
		com.sendSensorDataV2(sensor);
	}
}
