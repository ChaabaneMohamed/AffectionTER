package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.base.BaseWriter;
import Calcul.Calcul.bean.GroupOp;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;

/**
 * Servlet implementation class Eleve_choix
 */
@WebServlet("/Eleve_choix")
public class Eleve_choix extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eleve_choix() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
        
		request.setAttribute("options", basereader.getOptions(2018));
		
		ArrayList<GroupOp> tmp = basereader.getGroupOptions();
		
		request.setAttribute("groupOp", basereader.getGroupOPs(tmp));
        
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_choix.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
		BaseWriter bw = new BaseWriter();
		bw.initConnection();
		
		Option op = new Option(0, null,null, 0);
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
        
        ArrayList<GroupOp> tmp = basereader.getGroupOptions();
        
       
        
		Map<Integer, List<String>> choix = new HashMap<Integer, List<String>>();
		
		ArrayList<Option> options = basereader.getOptions(2018);
		
		 Map<Integer, List<Integer>> groupOps = basereader.getGroupOPs(tmp);
		
		for (int j = 1; j <= groupOps.size(); j++) {
			List<String> ops = new ArrayList<String>();
			for(int i = 0; i< groupOps.get(j).size(); i++) {
				ops.add(request.getParameter("" + (i + 1) + j));
			}
			choix.put(j, ops);
		}
		
		Map<Integer, List<Option>> prefs = new HashMap<Integer, List<Option>>();
		
		
		for (int i = 1; i <= groupOps.size(); i++) {
			List<Option> opPref = new ArrayList<Option>();
			for (String c : choix.get(i)) {
				opPref.add(op.nameToOption(options, c));
			}
			System.out.println(opPref.toString());
			prefs.put(i, opPref);
		}
		
		int numEtudiant = basereader.numEtudiantRequest(token);
        //System.out.println(prefs.toString());
		
		bw.writePreference(numEtudiant, prefs);
		
		request.setAttribute("prefs", prefs);
		request.setAttribute("options", options);
		request.setAttribute("choix", choix);
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_choix.jsp").forward(request, response);
	}

}
