package sensor;

import java.io.Serializable;
import java.util.Random;

public class SensorData implements Serializable{

	private Random r;
	private float value;
	
	public SensorData(float minVal, float maxVal)
	{
		r = new Random();
		value =  (minVal+(maxVal-minVal)*r.nextFloat());
	}//Constructor
	
	public void setValue(float value)
	{
		this.value =  value;
	} // setValue
	
	public float getValue()
	{
		return value;
	} // getValue
}
