package com.macqui;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.ontoservlet.test.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jena.ontology.*;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.query.* ;
import org.apache.jena.util.iterator.ExtendedIterator;

public class InnovationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response)
	           throws ServletException, IOException {
	
		response.setContentType("text/html"); 
    	PrintWriter out = response.getWriter();
    	
    	 HttpSession session=request.getSession(false);  
         String userCountry =(String)session.getAttribute("userCountry");  
         
	    	// accessing the readOntology() method in the Helper class
	    	
	    	OntModel model = Helper.readOntology();
	    	 
	    	String val1 = null;
	    	
	 		String queryString1 = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
	 				"PREFIX kb: <http://protege.stanford.edu/kb#>" +
	 				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
	 				"SELECT ?Tech_Readiness_Score \r\n" + 
	 				"WHERE {\r\n" + 
	 				"?competiveness kb:competiveness.Country_Name ?Country_Name .\r\n"+
	 				"?competiveness kb:competiveness.Tech_Readiness_Rank ?Tech_Readiness_Rank .\r\n"+
	 				"?competiveness kb:competiveness.Tech_Readiness_Score ?Tech_Readiness_Score .\r\n"+
	 				"FILTER (?Country_Name = '"+userCountry+"') .\r\n"+
	 				"}";
	 		Query query1 = QueryFactory.create(queryString1);
	 		  
	 		  try (QueryExecution qexec = QueryExecutionFactory.create(query1, model)) {
	 		    ResultSet results = qexec.execSelect() ;
	 		    /*ResultSetFormatter.out(System.out, results, query1);
	 		    System.out.println(results);*/
	 		    List l= results.getResultVars();
	 		    QuerySolution qs=results.nextSolution();
	 		    for(int i=0;i<l.size();i++){
	 		    	val1=qs.get(l.get(i).toString()).toString();
	 		    System.out.println("The value is :"+val1);
	 		    }

	 		  }
	 		  
	 		 String queryString = "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
						"PREFIX kb: <http://protege.stanford.edu/kb#>" +
						"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
						"SELECT ?Country_Name ?Capital ?Country_Size ?Population ?GDP ?Tech_Readiness_Rank ?Tech_Readiness_Score ?Higher_Education_Rank ?Higher_Education_Score \r\n" + 
						"WHERE {\r\n" + 
						"?competiveness kb:competiveness.Country_Name ?Country_Name .\r\n"+
						"?competiveness kb:competiveness.Capital ?Capital .\r\n"+
						"?competiveness kb:competiveness.Country_Size ?Country_Size .\r\n"+
						"?competiveness kb:competiveness.Population ?Population .\r\n"+
						"?competiveness kb:competiveness.GDP ?GDP .\r\n"+
						"?competiveness kb:competiveness.Tech_Readiness_Rank ?Tech_Readiness_Rank .\r\n"+
						"?competiveness kb:competiveness.Tech_Readiness_Score ?Tech_Readiness_Score .\r\n"+
						"?competiveness kb:competiveness.Higher_Education_Rank ?Higher_Education_Rank .\r\n"+
						"?competiveness kb:competiveness.Higher_Education_Score ?Higher_Education_Score .\r\n"+
						"FILTER (?Tech_Readiness_Score >= '"+val1+"') .\r\n"+
						"FILTER (?Country_Name != '"+userCountry+"') .\r\n"+
						"}";
	    	
			
	    	 
			Query query = QueryFactory.create(queryString);
			  
			// Execute the query and obtain results
			try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			    ResultSet results = qexec.execSelect() ;
			    List l= results.getResultVars();
			    
			    PrintWriter pw = response.getWriter();
			    response.getWriter().println("<html>");
		    	response.getWriter().println("<head>");
		    	response.getWriter().println("<meta charset='utf-8'>");
		    	response.getWriter().println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		    	response.getWriter().println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		    	response.getWriter().println("<title> Your Recommendation </title>");
		    	response.getWriter().println("<link href='bootstrap.min.css' rel='stylesheet'>");
		    	response.getWriter().println("<link rel='shortcut icon' href='favicon (2).ico' type='image/x-icon' />");
		    	response.getWriter().println("<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css' rel='stylesheet'>");
		    	response.getWriter().println("</head>");
		    	response.getWriter().println("<body>");
		    	response.getWriter().println("<link href='bootstrap.min.css' rel='stylesheet'>");
		    	response.getWriter().println("<nav class='navbar navbar-default navbar-static-top'>");
		    	response.getWriter().println("<div class='container-fluid'>");
		    	response.getWriter().println("<div class='navbar-header'>");
		    	response.getWriter().println("<a href='#' class='navbar-brand' ><img id='logo-image' alt='Brand' src='macquitr.png'></a>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("<div class='navbar-text pull-left'>");
		    	response.getWriter().println("<p id='macqui'>MACQUI</p>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("</nav>");
		    	response.getWriter().println("<div class='container'>");
		    	response.getWriter().println("<div class='main'>");
		    	response.getWriter().println("<h1>Recommendation panel</h1>");
		    	response.getWriter().println("<h4>Suitable countries according to your focus</h4>");
		    	response.getWriter().println("<div class='container'>");
		    	response.getWriter().println("<div class='row'>");
		    	response.getWriter().println("<div class='col-md-12 jumbotron jumbotron-special'>");;
		    	response.getWriter().println("<div class='container'>");
		    	response.getWriter().println("<div class='table-responsive'>");
		    	response.getWriter().println("<table class='table table-hover table-bordered'>");
		    	response.getWriter().println("<thead id='table' bgcolor='#CBE2EC'>");
			    pw.print("<tr bgcolor='#B0E0E6'>");
			    //taking length of result list for creating big enough table
			    for(int i=0;i<l.size();i++)
			    pw.print("<th><font size=3>"+l.get(i)+"</font></th>"); 
			    pw.write("</tr>");
			    response.getWriter().println("<tbody bgcolor='#fff' id='tablebody'>"); 
			    //going through result list for getting individual results
			    while(results.hasNext())
			    {
			    QuerySolution qs=results.nextSolution();
			    pw.print("<tr></font>");
			    for(int i=0;i<l.size();i++) {
			    //storing individual result values as String
			    String val=qs.get(l.get(i).toString()).toString();
			    val=qs.get(l.get(i).toString()).toString();
			    pw.print("<td>"+val+ "</td>"); }
			    pw.print("</tr>"); }
			    pw.print("</tbody>"); 
			    pw.print("</table>"); 
			    response.getWriter().println("</div>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("</body>");
		    	
		    	response.getWriter().println("<footer>");
		    	response.getWriter().println("<div class='navbar navbar-inverse navbar-fixed-bottom'>");
		    	response.getWriter().println("<div class='container'>");
		    	response.getWriter().println("<div class='navbar pull-left'>");
		    	response.getWriter().println("<a href='#' class='navbar-brand' ><img id='uni-logo' alt='Brand' src='trnspunilogo.png'></a>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("<div class='navbar-text pull-left'>");
		    	response.getWriter().println("<p><span id='unip'>UniMa Consulting Group LLC.</span> <span style='font:12px Arial;'>&copy; 2017  All rights reserved.</span></p>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("<div class='navbar-text pull-right'>");
		    	response.getWriter().println("<a class='docIcon' href'#'><i class='fa fa-facebook-square fa-2x'  aria-hidden='true'></i></a>");
		    	response.getWriter().println("<a href'#'><i class='fa fa-twitter-square fa-2x'  aria-hidden='true'></i></a>");
		    	response.getWriter().println("<a href'#'><i class='fa fa-linkedin-square fa-2x'  aria-hidden='true'></i></a>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("</div>");
		    	response.getWriter().println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
		    	response.getWriter().println("<script src='bootstrap.min.js'></script>");
		    	response.getWriter().println("</footer>");
		    	response.getWriter().println("</html>");
			    
			    pw.close();
			    
			   
			    
			    while ( results.hasNext() )
			    {
			      QuerySolution soln = results.nextSolution() ;
			      RDFNode x = soln.get("varName") ;       // Get a result variable by name.
			      Resource re = soln.getResource("VarR") ; // Get a result variable - must be a resource
			      Literal d = soln.getLiteral("VarL") ;   // Get a result variable - must be a literal
			     	
			      
			      }
			    
			    
			  }
	    	
	    	    	
	    	
	}
	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
			
	}
	
