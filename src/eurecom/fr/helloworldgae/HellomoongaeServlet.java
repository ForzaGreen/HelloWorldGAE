package eurecom.fr.helloworldgae;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HellomoongaeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		resp.setContentType("text/plain");
//		resp.getWriter().println("Hello, moon");
		resp.setContentType("text/plain");
		String msg = "Hello, moon";
		// Check for an argument
		if (req.getParameter("name") != null) {
			msg += " from " + req.getParameter("name");
		}
		resp.getWriter().println(msg);
		}
	
	
}
