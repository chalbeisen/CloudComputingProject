package sensor;

import java.util.List;

public class SensorTest {
	private static int amount = 30;
	public static void main(String[] args)
	{
		Sensor s = new Sensor(2);
		s.setSensorData(10, 40, 8);
		List<Float> sensorData = s.getTemperature();
		for(int i=0; i<sensorData.size();i++)
			System.out.println(sensorData.get(i));
	}
}
