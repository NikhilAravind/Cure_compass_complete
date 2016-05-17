package com.semantic.web.curecompass.online;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semantic.web.curecompass.RDFDataAccess;

/**
 * Servlet implementation class HospitalInfo
 */
@WebServlet("/getHospitals")
public class HospitalInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HospitalInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RDFDataAccess access = new RDFDataAccess();
		String disorder = request.getParameter("selectedDisorder");
		System.out.println(disorder);
					
		Hashtable<String, Object> hospitalsAndCost = access.getHospitalsForDisorder("\""+disorder+"\"");
		LinkedHashSet<String> hospitalNames = (LinkedHashSet<String>) hospitalsAndCost.get("hospitalNames");
		ArrayList<String> payments = (ArrayList<String>) hospitalsAndCost.get("payments");
				
		request.setAttribute("disorder",disorder);
		request.setAttribute("hospitalNames", hospitalNames);
		request.setAttribute("payments", payments);
				
		ArrayList<HospitalBean> withoutRatings = new ArrayList<HospitalBean>();
		ArrayList<HospitalBean> results = new ArrayList<HospitalBean>();
		HospitalBean bean = null;
		
		Iterator<String> iter = hospitalNames.iterator();
		int i = 0;
		while (iter.hasNext()) {
			bean = new HospitalBean();
			bean.setCost(payments.get(i));
			bean.setName(iter.next());
			withoutRatings.add(bean);
			i+=1;
		}
		
		
		ArrayList<ArrayList> allHospitalRatings = (ArrayList<ArrayList>)access.getHospitalRatingsAndOwnership(hospitalNames);
		
		int j = 0;
		HospitalBean beanWithRatings = null;
		
		for (ArrayList arrayListWithRatings : allHospitalRatings) {
			
			double averageRating = 0.0;
			String eachRating = "";
			
			int numberOfIndividualRatingsAvailable = 0;
			
			/*Set<String> hs = new LinkedHashSet<>();
			hs.addAll(arrayListWithRatings);
			arrayListWithRatings.clear();
			arrayListWithRatings.addAll(hs);*/
			
			
			for (Object ratings : arrayListWithRatings) {
				eachRating = ratings+"";
				if(eachRating.equalsIgnoreCase("Not Available")){
					continue;
				}
				averageRating += Double.parseDouble(eachRating);
				numberOfIndividualRatingsAvailable += 1;
				
			}
			
			beanWithRatings = withoutRatings.get(j);
			System.out.println(averageRating +"/"+numberOfIndividualRatingsAvailable);
			if(numberOfIndividualRatingsAvailable!=0)
				beanWithRatings.setRating(round((averageRating/numberOfIndividualRatingsAvailable),2));
			else
				beanWithRatings.setRating(round((0.0000),2));
			results.add(beanWithRatings);
			j+=1;
		}
		
		request.setAttribute("result", results);
		if(request.getParameter("cost")!=null){
			request.setAttribute("cost", "Yes");
		}
		
		if(request.getParameter("rating")!=null){
			request.setAttribute("rating", "Yes");
		}
		
		request.setAttribute("ratings", allHospitalRatings);
		request.getRequestDispatcher("/WEB-INF/HospitalInfo.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
