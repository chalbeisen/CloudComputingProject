package general;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import com.google.appengine.api.datastore.*;

import org.omg.CORBA.portable.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sensor.*;

import java.util.*;

//https://cloud.google.com/appengine/docs/standard/java/datastore/entities

public class CloudComputingProjektServlet extends HttpServlet {
	
	//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		int id = Integer.parseInt(req.getParameter("id"));
		float temperature = Integer.parseInt(req.getParameter("temperature"));
		resp.getWriter().println(temperature);
		resp.getWriter().println(id);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("POST");
		
		/*BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String request = in.readLine();
		resp.getWriter().println(request);*/
		int id = Integer.parseInt(req.getParameter("id"));
		float temperature = Integer.parseInt(req.getParameter("temperature"));
		resp.getWriter().println(temperature);
		resp.getWriter().println(id);
		Key key = createNewEntity(id, temperature);
		
		/*resp.setContentType("text/plain");
		
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
		} */
	}

	
	public Key createNewEntity(int id, float temperature)
	{
		Entity sensorEntity = new Entity("Sensor",id);
		sensorEntity.setProperty("Temperatur",temperature);
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key sensorKey = ds.put(sensorEntity);
		return sensorKey;
	}
}
