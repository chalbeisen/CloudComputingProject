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
import com.google.appengine.api.datastore.EntityNotFoundException;
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
		
		//get Sensor Object from Client
		ObjectInputStream in = new ObjectInputStream(req.getInputStream());
		try {
			Sensor sensor = (Sensor)in.readObject();
			//get Temperature of Sensor
			List<Double> sensorData = sensor.getTemperature();
			//save Keys in a list
			List<Key> keys = new ArrayList<Key>();
			for(int i=0;i<sensorData.size();i++)
			{
				//add keys to list
				keys.add(createNewEntity(sensorData.get(i)));
				resp.getWriter().println(sensorData.get(i));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	public Key createNewEntity(double temperature)
	{
		Entity sensorEntity = new Entity("Sensor");
		sensorEntity.setProperty("Temperatur", temperature);
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key sensorKey = ds.put(sensorEntity);
		return sensorKey;
	}
}
