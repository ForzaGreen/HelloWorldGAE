package eurecom.fr.helloworldgae;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class ContactsListServlet extends HttpServlet {
	
	private String getXML(List<Entity> contacts, HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
		}
		private String getJSON(List<Entity> contacts, HttpServletRequest req, HttpServletResponse resp) {
		// Create a JSON array that will contain all the entities converted in a JSON version
		JSONArray results = new JSONArray();
		for (Entity contact: contacts) {
		JSONObject contactJSON = new JSONObject();
		try {
		contactJSON.put("name", contact.getProperty("name"));
		contactJSON.put("phone", contact.getProperty("phone"));
		contactJSON.put("id", KeyFactory.keyToString(contact.getKey()));
		contactJSON.put("email", contact.getProperty("email"));
		} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		results.put(contactJSON);
		}
		return results.toString();
		}

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		// Take the list of contacts ordered by name
		Query query = new Query("Contact").addSort("name", Query.SortDirection.ASCENDING);
		List<Entity> contacts = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		// Let's output the basic HTML headers
		PrintWriter out = resp.getWriter();
		
		/** Different response type? */
		String responseType = req.getParameter("respType");
		if (responseType != null) {
		if (responseType.equals("json")) {
		// Set header to JSON output
		resp.setContentType("application/json");
		out.println(getJSON(contacts, req, resp));
		return;
		} else if (responseType.equals("xml")) {
		resp.setContentType("application/json");
		out.println(getXML(contacts, req, resp));
		return;
		}
		}
		
		resp.setContentType("text/html");
		out.println("<html><head><title>Contacts list</title></head><body>");
		if (contacts.isEmpty()) {
		out.println("<h1>Your list is empty!</h1>");
		} else {
		// Let's build the table headers
		out.println("<table style=\"border: 1px solid black; width: 100%; text-align: center;\">"
		+ "<tr><th>Name</th><th>Phone Number</th><th>Details</th></tr>");
		for (Entity contact: contacts) {
		out.println("<tr><td>" + contact.getProperty("name") + "</td>"
		+ "<td>" + contact.getProperty("phone") + "</td>"
		+ "<td><a href=\"contactdetails?id="
		+ KeyFactory.keyToString(contact.getKey())
		+ "\">details</a></td>"
		+ "</tr>");
		}
		out.println("</table>");
		}
		out.println("</body></html>");
		
		}
}
