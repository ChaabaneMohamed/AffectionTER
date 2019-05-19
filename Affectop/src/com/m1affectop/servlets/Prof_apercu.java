package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.base.BaseReader;

import Calcul.Calcul.bean.Student;
import Calcul.Calcul.bean.GroupOp;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Preference;

/**
 * Servlet implementation class Prof_apercu
 */
@WebServlet("/Prof_apercu")
public class Prof_apercu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_apercu() {
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
        
        int numEtudiant = basereader.numEtudiantRequest(token);
        request.setAttribute("numEtudiant", numEtudiant);
		
        request.setAttribute("options", basereader.getOptions(2018));
  
        ArrayList<Student> eleves = basereader.getStudents(2017);
        request.setAttribute("eleves", eleves);
        
        request.setAttribute("prefs", basereader.getPreferencesPerStudent(eleves));
        
        Map<Integer, List<Preference>> e = basereader.getPreferencesPerStudent(eleves);
        List<Preference> a = e.get(1);
        
        ArrayList<GroupOp> tmp = basereader.getGroupOptions();
		
		request.setAttribute("groupOp", basereader.getGroupOPs(tmp));
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/prof_apercu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
        
        int numEtudiant = basereader.numEtudiantRequest(token);
        request.setAttribute("numEtudiant", numEtudiant);
        
        request.setAttribute("options", basereader.getOptions(2018));
        
        ArrayList<Student> eleves = basereader.getStudents(2017);
        request.setAttribute("eleves", eleves);
        
        request.setAttribute("prefs", basereader.getPreferencesPerStudent(eleves));

        ArrayList<GroupOp> tmp = basereader.getGroupOptions();
		
		request.setAttribute("groupOp", basereader.getGroupOPs(tmp));
		
        this.getServletContext().getRequestDispatcher("/WEB-INF/prof_apercu.jsp").forward(request, response);
	}

}
