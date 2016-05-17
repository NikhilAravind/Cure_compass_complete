package com.semantic.web.curecompass.online;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

import com.semantic.web.curecompass.RDFDataAccess;

/**
 * Servlet implementation class HospitalSearch
 */
/**
 * @author Nikhil Aravind
 *
 */
@WebServlet("/HospitalSearch")
public class HospitalSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HospitalSearch() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RDFDataAccess rdfDataAccess = new RDFDataAccess();
		
		//Retrives a list of available disorders from the RDF data source
		LinkedHashSet allDisorders = rdfDataAccess.getDisorderRDFData();
		
		System.out.println(allDisorders);
		
		request.setAttribute("disorders", allDisorders);
		request.getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	

}
