package sensor;

import java.io.Serializable;
import java.util.Random;

public class SensorData implements Serializable{

	private Random r;
	private double value;
	
	public SensorData(float minVal, float maxVal)
	{
		r = new Random();
		value =  (minVal+(maxVal-minVal)*r.nextDouble());
	}//Constructor
	
	public void setValue(double value)
	{
		this.value =  value;
	} // setValue
	
	public double getValue()
	{
		return value;
	} // getValue
}
