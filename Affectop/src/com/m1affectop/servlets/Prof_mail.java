package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.utils.TxtToMail;
/**
 * Servlet implementation class Prof_mail
 */
@WebServlet("/Prof_mail")
public class Prof_mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_mail() {
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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_mail.jsp").forward(request, response);
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
		
		String mail_eleve = request.getParameter("mail_eleve");
		request.setAttribute("mail_eleve", mail_eleve);
		
		String mail_secretariat = request.getParameter("mail_secretariat");
		request.setAttribute("mail_secretariat", mail_secretariat);
		
		Map<String, ArrayList<String>> res1 = new HashMap<>();
		Map<String, ArrayList<String>> res2 = new HashMap<>();
		
		res1 = TxtToMail.tagChecker(mail_eleve);
		res2 = TxtToMail.tagChecker(mail_secretariat);
		
		ArrayList<String> result1 = res1.get("Invalid");
		ArrayList<String> result2 = res2.get("Invalid");
		
		request.setAttribute("result1", result1);
		request.setAttribute("result2", result2);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_mail.jsp").forward(request, response);
	}

}
