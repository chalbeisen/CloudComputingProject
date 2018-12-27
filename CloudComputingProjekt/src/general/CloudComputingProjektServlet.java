package general;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import com.google.appengine.api.datastore.*;

import dataStore.DataStoreManager;

import org.omg.CORBA.portable.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sensor.*;

import java.util.*;

//https://cloud.google.com/appengine/docs/standard/java/datastore/entities

public class CloudComputingProjektServlet extends HttpServlet {
	
	DataStoreManager ds;

	
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
		
		//get data from request
		int id = Integer.parseInt(req.getParameter("id"));
		int temperature = Integer.parseInt(req.getParameter("temperature"));
		long timestamp = Long.parseLong(req.getParameter("timestamp"));
		
		//DEBUG
		resp.getWriter().println(temperature);
		resp.getWriter().println(id);
		
		//write data to DataStore
		ds = new DataStoreManager();
		Key parentKey = ds.createNewEntity(id);
		Key dataKey = ds.addChild(temperature, id, timestamp, parentKey);
	}
}
