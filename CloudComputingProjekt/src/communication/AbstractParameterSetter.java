package communication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import sensor.Sensor;

public abstract class AbstractParameterSetter {

	public String setParameters(Sensor sensor) throws UnsupportedEncodingException
	{
		Map<String,String> parameters = new HashMap<>();
		parameters.put("sensorID", sensor.getId());
		parameters.put("longitude", Double.toString(sensor.getLocation().getLongitude()));
		parameters.put("latitude", Double.toString(sensor.getLocation().getLatitude()));
		parameters.put("sensorDataID", sensor.getSensorData().getID());
		parameters.put("currentTimeMillis", Long.toString(sensor.getSensorData().getCurrentTimeMillis()));
		parameters.put("temperature", Double.toString(sensor.getSensorData().getTemperature()));
		
		return setParameterString(parameters);
	}
	
	public String setParameterString(Map<String,String> parameters) throws UnsupportedEncodingException
	{
	    StringBuilder result = new StringBuilder();
	    	 
	    for (Map.Entry<String, String> entry : parameters.entrySet()) {
	    	 result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	    	 result.append("=");
	    	 result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	    	 result.append("&");
	    }
	    	 
	    String resultString = result.toString();
	    
	    return resultString;
	}
	
}
