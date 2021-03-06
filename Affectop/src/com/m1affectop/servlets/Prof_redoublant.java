package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.base.BaseWriter;
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.bean.Option;
/**
 * Servlet implementation class Prof_redoublant
 */
@WebServlet("/Prof_redoublant")
public class Prof_redoublant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_redoublant() {
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
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequestTeacher(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequestTeacher(token);
        request.setAttribute("firstname", firstname);
        
        request.setAttribute("options", basereader.getOptions(2018));
        request.setAttribute("eleves", basereader.getStudents(2017));
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_redoublant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
		BaseWriter bw = new BaseWriter();
		bw.initConnection();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequestTeacher(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequestTeacher(token);
        request.setAttribute("firstname", firstname);

        ArrayList<Option> options = basereader.getOptions(2018);
        ArrayList<Student> students = basereader.getStudents(2017);
        
		request.setAttribute("options", basereader.getOptions(2018));
        request.setAttribute("eleves", basereader.getStudents(2017));
		
        
        for (int i = 0; i < students.size(); i++) {
        	for(int j = 0; j < options.size(); j++) {
        		if(request.getParameter("valide"+i+"_"+(j)) != null)
        			bw.writeOneRepeater( students.get(i).getNumEtudiant(), options.get(j).id);
        	}
        }
        
        request.setAttribute("options", options);
        request.setAttribute("eleves", students);
        request.setAttribute("redoublants", basereader.getRepeater(2017));
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_confirmer.jsp").forward(request, response);
	}

}
