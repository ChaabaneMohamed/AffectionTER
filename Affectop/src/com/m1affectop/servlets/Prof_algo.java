package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.algorithms.calcul.Affectop;
import Calcul.Calcul.algorithms.test.AffectopTest;
import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.base.BaseWriter;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;


/**
 * Servlet implementation class Prof_algo
 */
@WebServlet("/Prof_algo")
public class Prof_algo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_algo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
		BaseWriter bw = new BaseWriter();
		bw.initConnection();
		int nbDays = 5; // pour 5 groupes
		int nbLaunch = 2; // nombre de fois qu'on lance l'algo 
		
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
       
        // On lance l'algo avec des students et options aléatoire
		ArrayList<ArrayList<Option>> options= AffectopTest.randomOptions(nbDays,new int[]{2,2,2,2,2},new int[]{4,4,4,4,4},new int[]{40,40,40,40,40},new int[]{50,50,50,50,50});
		ArrayList<Student> students = AffectopTest.randomStudents(300,options, nbDays);
		HashMap<Option, LinkedList<Option>> incompatibilities = AffectopTest.makeRandomIncompatibilities(options);
		System.out.println(">>>"+Affectop.affectTop(students, options, incompatibilities, 5, 2));
        
		
        request.setAttribute("options", options);
        request.setAttribute("eleves", students);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_algo.jsp").forward(request, response);
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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_algo.jsp").forward(request, response);
	}

}
