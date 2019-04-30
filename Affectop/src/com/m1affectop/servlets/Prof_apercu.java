package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.base.BaseReader;

import Calcul.Calcul.bean.Student;
import Calcul.Calcul.bean.Option;

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
        
		List<Student> students = new ArrayList<>();
		
		Student eleve1 = new Student(null, null, 0);
		Student eleve2 = new Student(null, null, 0);
		Student eleve3 = new Student(null, null, 0);
		Student eleve4 = new Student(null, null, 0);
		Student eleve5 = new Student(null, null, 0);
		
		eleve1.setNomPrenom("Jean1", "Martin1");
		eleve1.setMail("jean.martin1@etu.univ-amu.fr");
		eleve2.setNomPrenom("Jean2", "Martin2");
		eleve2.setMail("jean.martin2@etu.univ-amu.fr");
		eleve3.setNomPrenom("Jean3", "Martin3");
		eleve3.setMail("jean.martin3@etu.univ-amu.fr");
		eleve4.setNomPrenom("Jean4", "Martin4");
		eleve4.setMail("jean.martin4@etu.univ-amu.fr");
		eleve5.setNomPrenom("Jean5", "Martin5");
		eleve5.setMail("jean.martin5@etu.univ-amu.fr");
		
		students.add(eleve1);
		students.add(eleve2);
		students.add(eleve3);
		students.add(eleve4);
		students.add(eleve5);
		
		List<Option> optionPref = new ArrayList<>();
		
		Option option1 = new Option(0, "Option test 1", 0, 0);
		option1.setDescription("Lorem ipsum");
		Option option2 = new Option(0, "Option test 2", 0, 0);
		option2.setDescription("Lorem ipsum");
		Option option3 = new Option(0, "Option test 3", 0, 0);
		option3.setDescription("Lorem ipsum");
		
		optionPref.add(option1);
		optionPref.add(option2);
		optionPref.add(option3);
		
		for (Student eleve : students) { 
			eleve.setOptionPref(optionPref);
		}
		students.get(3).setOptionPref(null);
        
        request.setAttribute("eleves", students);
        
        
        //ArrayList<LinkedList<Option>> prefs = basereader.getStudentPreference(3, basereader.numEtudiantRequest(token), 2018); 
        //request.setAttribute("eleves", basereader.getStudents(2018));
        //request.setAttribute("prefs", basereader.getOptions(2018));
        
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
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/prof_apercu.jsp").forward(request, response);
	}

}
