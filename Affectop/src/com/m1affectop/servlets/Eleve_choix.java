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
import Calcul.Calcul.base.BaseWriter;
import Calcul.Calcul.bean.GroupOp;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Preference;
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
        
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
        
        int numEtudiant = basereader.numEtudiantRequest(token);
        request.setAttribute("numEtudiant", numEtudiant);
        
		request.setAttribute("options", basereader.getOptions(2018));
		//System.out.println(basereader.getOptions(2018).size()+"AAA");
		
		Map<Student, ArrayList<Option>> repeater = basereader.getRepeater(2018);
		request.setAttribute("repeater", repeater);
		
		ArrayList<GroupOp> tmp = basereader.getGroupOptions();
		
		request.setAttribute("groupOp", basereader.getGroupOPs(tmp));
        
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_choix.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseReader basereader = new BaseReader();
		BaseWriter bw = new BaseWriter();
		bw.initConnection();
		
		Option op = new Option(0, null, null, 0, null);
        
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequest(token);
        request.setAttribute("name", name);
        
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
        
        String firstname = basereader.firstNameRequest(token);
        request.setAttribute("firstname", firstname);
        
        int numEtudiant = basereader.numEtudiantRequest(token);
        request.setAttribute("numEtudiant", numEtudiant);
        
        ArrayList<GroupOp> tmp = basereader.getGroupOptions();
        
		Map<Integer, List<String>> choix = new HashMap<Integer, List<String>>();
		
		ArrayList<Option> options = basereader.getOptions(2018);
		
		
		Map<Integer, List<Integer>> groupOps = basereader.getGroupOPs(tmp);
		
		List<Preference> prefs = new ArrayList<Preference>();
		Map<Integer, List<Preference>> prefGroup = new HashMap<Integer, List<Preference>>();
		
		for (int j = 1; j <= groupOps.size(); j++) {
            List<Preference> listp = new ArrayList<Preference>();
            for(int i = 1; i<= options.size(); i++) {
                if(request.getParameter("" + (i-1) + j) != null) {
                    Preference p = new Preference(j, Integer.parseInt(request.getParameter("" + (i-1) + j)), i, numEtudiant);
                    listp.add(p);
                }
            }
            prefGroup.put(j, listp);
        }

		boolean validation = true;
		for (int i = 1; i <= groupOps.size(); i++) {
			int max=0;
			int nb =0;
			List<Preference> a = prefGroup.get(i);
			for (Preference p : a) {
				if(a.size() < 5) {
					max += p.getChoice();
					if(p.getChoice() != 0)
						nb+=1;
					if (max > 10 || p.getChoice() > 7) {
						validation = false;
					}
				} else {
					max += p.getChoice();
					if (p.getChoice() != 0)
						nb += 1;
					if (max > (Math.round((a.size()) * (a.size() - 1)) / 2)
							|| p.getChoice() > Math.round(0.7 * ((a.size()) * (a.size() - 1)) / 2)) {
						validation = false;
					}
				}
			}
			if (a.size() < 5) {
				if (max != 10) {
					validation = false;
				}
			} else {
				if (max != (Math.round((a.size()) * (a.size() - 1)) / 2)) {
					validation = false;
				}

				if (nb < 0.5 * a.size()) {
					validation = false;
				}

			}
		}
		Map<Integer, List<Preference>> prefPerGroup = null;

		if (validation == true) {
			for (int j = 1; j <= groupOps.size(); j++) {
				for (int i = 1; i <= options.size(); i++) {
					if (request.getParameter("" + (i - 1) + j) != null) {
						Preference p = new Preference(j, Integer.parseInt(request.getParameter("" + (i - 1) + j)), i,
								numEtudiant);
						if (basereader.preferenceExist(p)) {
							bw.updatePreference(p);
						} else {
							bw.writePreference(p);
						}
						prefs.add(p);
					}
				}
			}
			
			prefPerGroup = new HashMap<Integer, List<Preference>>();

			for (int i = 1; i <= basereader.getNbDays(); i++) {
				List<Preference> liste = new ArrayList<Preference>();
				for (Preference preference : prefs) {
					if (preference.getGroupId() == i)
						liste.add(preference);
				}
				prefPerGroup.put(i, liste);
			}
		}
		
		request.setAttribute("groupOp", basereader.getGroupOPs(tmp));
		
		request.setAttribute("prefPerGroup", prefPerGroup);
		request.setAttribute("validation", validation);
		request.setAttribute("options", options);
		request.setAttribute("choix", choix);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/eleve_choix.jsp").forward(request, response);
	}

}
