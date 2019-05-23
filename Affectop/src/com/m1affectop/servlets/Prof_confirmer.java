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
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.utils.SendEmailTLS2;
import Calcul.Calcul.utils.TxtToMail;
import Calcul.Calcul.bean.Option;
/**
 * Servlet implementation class Prof_confirmer
 */
@WebServlet("/Prof_confirmer")
public class Prof_confirmer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_confirmer() {
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
        
        String name = basereader.nameRequestTeacher(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequestTeacher(token);
        request.setAttribute("firstname", firstname);
		
        request.setAttribute("options", basereader.getOptions(2018));
        request.setAttribute("eleves", basereader.getStudents(2017));
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_confirmer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequestTeacher(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequestTeacher(token);
        request.setAttribute("firstname", firstname);
		
        request.setAttribute("options", basereader.getOptions(2018));
        request.setAttribute("eleves", basereader.getStudents(2017));
		
        ArrayList<Student> eleves = basereader.getStudents(2017);
        
		String mail = request.getParameter("mail");
		
		Map<String, ArrayList<String>> res = new HashMap<>();
		
		res = TxtToMail.tagChecker(mail);
		
		SendEmailTLS2 send = new SendEmailTLS2("741VhY741");
		for (Student student : eleves) {
			String customMail = TxtToMail.custom(student, mail);
			send.setTo(student.getMail());
			send.setContenu(customMail);
			// mis en commentaire pour pas envoyer les mails
			//send.sendMail();
		}
		
		send.setTo("MASTERINFOLUMINY@gmail.com");
		send.setContenu("Test envoie mail Affectop");
		send.sendMail();
		
		ArrayList<String> result = res.get("Invalid");
		
		request.setAttribute("result", result);
		
		request.setAttribute("mail", mail);
		if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_confirmer.jsp").forward(request, response);
	}

}
