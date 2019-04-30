package com.m1affectop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.base.BaseWriter;
import Calcul.Calcul.bean.Option;

/**
 * Servlet implementation class Prof_option
 */
@WebServlet("/Prof_option")
public class Prof_option extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_option() {
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
		
        //request.setAttribute("options", basereader.getOptions(2018));
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_option.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
		BaseWriter basewriter = new BaseWriter();
		basewriter.initConnection();
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);

        String group = request.getParameter("group");
        
        HttpSession session = request.getSession() ;
        
        if(group != null) {
	        session.setAttribute("group.session", group) ;
        }
        
        if(group == null) {
        	group = (String)session.getAttribute("group.session");
        }
        
        request.setAttribute("group", group);
        
		List<String> groupes = new ArrayList<>();
		
		for(int i = 1; i <= Integer.parseInt(group); i++)
			groupes.add(request.getParameter("groupe_" + i));
		
		String test = request.getParameter("nom");
		if(test != null) {
			Option option = new Option(0, request.getParameter("nom"), 0, (int)Math.random() * ( 1000 - 0 ));
			for (String g : groupes) {
				if(g != null)
					option.setGroupe(Integer.parseInt(g.substring(7)));
			}
			
			option.setDescription(request.getParameter("description"));
			option.setMail_prof(request.getParameter("mail_prof"));
			option.setYear(2018);
			
			basewriter.writeOneOption(option, 2018);
		}
		request.setAttribute("options", basereader.getOptions(2018));
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_option.jsp").forward(request, response);
	}

}
