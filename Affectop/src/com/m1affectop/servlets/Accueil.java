package com.m1affectop.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.base.BaseWriter;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Acceuil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
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
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

}