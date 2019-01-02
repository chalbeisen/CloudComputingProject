package sensor;

import java.io.IOException;

public class Sensor extends AbstractSensor{
	
	public Sensor(String id, Location location, SensorData data, String hostName, String page)
	{
		super(id,location,data, hostName, page);
	}

	@Override
	public void run() {
		//while(true)
		{
			try {
				//com.sendSensorData(this);
				//com.disconnect();
				com.retrieveSensor(this.getId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
