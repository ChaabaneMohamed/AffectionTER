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
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.utils.SendEmailTLS2;
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
        
        String name = basereader.nameRequestTeacher(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequestTeacher(token);
        request.setAttribute("firstname", firstname);
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_mail.jsp").forward(request, response);
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
		
		String mail_eleve = request.getParameter("mail_eleve");
		request.setAttribute("mail_eleve", mail_eleve);
		
		String mail_secretariat = request.getParameter("mail_secretariat");
		request.setAttribute("mail_secretariat", mail_secretariat);
		
		ArrayList<Student> eleves = basereader.getStudents(2017);
		
		Map<String, ArrayList<String>> resEleve = new HashMap<>();
		Map<String, ArrayList<String>> resSco = new HashMap<>();
		
		
		// Envoie mail aux élèves
		resEleve = TxtToMail.tagChecker(mail_eleve);
		
		// Envoie mail au secrétariat
		resSco = TxtToMail.tagChecker(mail_secretariat);
		
		SendEmailTLS2 send = new SendEmailTLS2("741VhY741");
		
		ArrayList<String> resultEleve = resEleve.get("Invalid");
		ArrayList<String> resultSco = resSco.get("Invalid");
		
		ArrayList<String> vresultEleve = resEleve.get("Valid");
		ArrayList<String> vresultSco = resSco.get("Valid");
		
		int index = 0;
		if(resultEleve.size()<=0 && resultSco.size()<= 0) {
			for (Student student : eleves) {
				
				String customMail = TxtToMail.custom(student, mail_eleve);
				
				// Pour envoyer au élève
				//send.setTo(student.getMail());
				
				// Pour envoyer a l'adresse de test
				send.setTo("MASTERINFOLUMINY@gmail.com");
				
				// Contenu du mail
				send.setContenu(customMail);
				System.out.println(customMail);
				
				// Décommenter pour envoyer
				//send.sendMail();
				
				index++;
				if(index >= 10)
					break;
			}
			
			
			String customMailSco = mail_secretariat.replace("<LIENSCO>", "<LIENSCO>");
			
			// Pour envoyer au secrétariat
			//send.setTo(sciences-luminy-scol@univ-amu.fr);
			
			// Pour envoyer a l'adresse de test
			send.setTo("MASTERINFOLUMINY@gmail.com");
			
			// Contenu du mail
			send.setContenu(customMailSco);
			System.out.println(customMailSco);
			
			// Décommenter pour envoyer
			//send.sendMail();
			
			
		}
		
		request.setAttribute("result1", resultEleve);
		request.setAttribute("result2", resultSco);
		
		if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_mail.jsp").forward(request, response);
	}

}
