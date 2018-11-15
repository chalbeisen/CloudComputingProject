package general;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.omg.CORBA.portable.InputStream;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import sensor.*;

import java.util.*;

//https://cloud.google.com/appengine/docs/standard/java/datastore/entities

@SuppressWarnings("serial")
public class CloudComputingProjektServlet extends HttpServlet {
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		//read Sensor data
		ObjectInputStream in = new ObjectInputStream(req.getInputStream());
		try {
			Sensor sensor = (Sensor)in.readObject();
			List<Float> sensorData = sensor.getTemperature();
			for(int i=0;i<sensorData.size();i++)
			{
				resp.getWriter().println(sensorData.get(i));
			}
			createNewEntity(sensor);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void createNewEntity(Sensor sensor)
	{
		Entity sensorEntity = new Entity("Sensor",sensor.getID());
		sensorEntity.setProperty("Temperatur", sensor.getTemperature());
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key sensorKey = ds.put(sensorEntity);
	}
	
	/*public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		Sensor sensor = new Sensor(30,10,40);
		Entity sensorEntity = new Entity(sensor.getName());
		buildEntity(sensor,sensorEntity);
		datastore.put(sensorEntity);
	}*/
	
	/*public void buildEntity(Sensor sensor, Entity sensorEntity)
	{
		float[] data = sensor.getSensorData();
		for(int i=0; i<sensor.getAmount();i++)
		{
			sensorEntity.setProperty("temperature",data[i]);
		}
	}*/
}
