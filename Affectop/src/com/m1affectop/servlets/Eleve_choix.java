package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.base.BaseWriter;
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
        
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_choix.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
		BaseWriter bw = new BaseWriter();
		
		Option op = new Option(0, null, 0, 0);
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
		
		List<String> choix = new ArrayList<>();
		
		ArrayList<Student> students = basereader.getStudents(2018);
		ArrayList<Option> options = basereader.getOptions(2018);
		
		for(int i = 0; i< options.size(); i++) {
			choix.add(request.getParameter("" + (i + 1) ));
		}
		
		ArrayList<Option> prefs = new ArrayList<>();
		
		for (String c : choix) {
			prefs.add(op.nameToOption(options, c));
		}
		
		int numEtudiant = basereader.numEtudiantRequest(token);
        
		bw.writePreference(numEtudiant, prefs);
		
		request.setAttribute("prefs", prefs);
		request.setAttribute("options", options);
		request.setAttribute("choix", choix);
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_choix.jsp").forward(request, response);
	}

}
