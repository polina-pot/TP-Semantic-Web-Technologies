
package com.ontoservlet.test;


//import java.io.FileInputStream;
//import java.io.FileNotFoundException;


//import org.apache.jena.ontology.OntModel;
//import org.apache.jena.ontology.OntModelSpec;
//import org.apache.jena.rdf.model.ModelFactory;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public MainForm() {
		 super();
	}
	
	public void doPost(HttpServletRequest request, 
		    	HttpServletResponse resp) 
		              throws ServletException, IOException {
		
	        resp.setContentType("text/html");
	        PrintWriter out = resp.getWriter();
	        
	        //ServletContext context = getServletContext().getContext("/SalesServlet");

	    	String userCountry = request.getParameter("userCountry");
	    	String focus = request.getParameter("focus");
	    	
	            
	          //building http session for providing variables in other classes
	          HttpSession session=request.getSession();  
	          session.setAttribute("userCountry", userCountry);
	          session.setAttribute("focus", focus);
	 
	        
	        
		    	//check for null and empty values.
		    	if(focus == null || focus.equals("")){
		    		out.print("Please enter  " +
		    				" Focus. <br/><br/>");
		    		RequestDispatcher requestDispatcher = 
		    			request.getRequestDispatcher("/SalesServlet");
		    		requestDispatcher.forward(request, resp);
		    	}//Check for valid username and password.
		    	else if(focus.equals("Sales")){
		    		
		    		resp.sendRedirect("SalesServlet");
		    		
		    		
		    		/*RequestDispatcher requestDispatcher = 
			    			request.getRequestDispatcher("/SalesServlet");
			    		requestDispatcher.forward(request, resp);*/
		    		
		    		/*RequestDispatcher rd = request.getRequestDispatcher("SalesServlet");
			    	rd.forward(request,resp);*/
		    		
		    		//sending countryName value to SalesServlet class
		    		
		    		return;
		    	} else if(focus.equals("Education")){
		    		resp.sendRedirect("EducationServlet");
		    	
		    		
		    		return;
		    	} else if(focus.equals("Innovation")){
		    		resp.sendRedirect("InnovationServlet");
		    		
		    		return;
		    	}else{
		    	
		    		out.print("Wrong username or password. <br/><br/>");
		    		RequestDispatcher requestDispatcher = 
		    			request.getRequestDispatcher("/index.html");
		    		requestDispatcher.forward(request, resp);
		    		
		    		/*if(userCountry == null || userCountry.equals("") 
		    		|| focus == null || focus.equals("")){
		    		out.print("Please enter both Country " +
		    				"and Focus. <br/><br/>");
		    		RequestDispatcher requestDispatcher = 
		    			request.getRequestDispatcher("/index.html");
		    		requestDispatcher.include(request, response);
		    	}//Check for valid username and password.
		    	else if(userCountry.equals("Germany") && focus.equals("Sales")){
		    		response.sendRedirect("SalesServlet");
		    	}else{
		    		out.print("Wrong username or password. <br/><br/>");
		    		RequestDispatcher requestDispatcher = 
		    			request.getRequestDispatcher("/index.html");
		    		requestDispatcher.include(request, response);
		    		 * 
		    		 * 
		    		 */
		    	}
		    	

                
		          out.close();  
			}


	 
}
