package com.m1affectop.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Calcul.Calcul.algo.Affectation;
import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.base.BaseWriter;
import Calcul.Calcul.bean.GroupOp;
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
		ArrayList<Affectation> allAffectation = new ArrayList<Affectation>();
		BaseReader basereader = new BaseReader();
		BaseWriter bw = new BaseWriter();
		bw.initConnection();
		int nbDays = 5; // pour 5 groupes
		int nbLaunch = 2; // nombre de fois qu'on lance l'algo 
		
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequestTeacher(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequestTeacher(token);
        request.setAttribute("firstname", firstname);
       
        ArrayList<GroupOp> tmp = basereader.getGroupOptions();
		Map<Integer, List<Integer>> options = basereader.getGroupOPs(tmp);
		
		ArrayList<Student> eleves = basereader.getStudents(2017);
		
		System.out.println("Affectation0");
		for (int i = 1; i <= options.size(); i++) {
			System.out.println("Affectation"+i);
			if(i==1) {
				System.out.println("Affectation"+1+"test");
				Affectation a = new Affectation(i, allAffectation, eleves);
				
				a.reset();			
				a.affecter();
				allAffectation.add(a);
				
			}
			else {
				Affectation a = new Affectation(i, allAffectation , eleves);
				a.affecter();
				allAffectation.add(a);
				//System.out.println("ADD");
			}

		}
		
		Affectation ok = new Affectation(0, allAffectation, eleves);
		String sortie = ok.creerSortie();
		String sco = ok.creerListeScolarite();
		//System.out.println(sortie);
		
		String path = getServletContext().getRealPath("WEB-INF" + File.separator + "output" + File.separator + "listeEtudiant.pdf");
		
		String pathSco = getServletContext().getRealPath("WEB-INF" + File.separator + "output" + File.separator + "listeScolarite.pdf");
		
		String pathCSV = getServletContext().getRealPath("WEB-INF" + File.separator + "output" + File.separator + "Affectation.csv");
		
		FileOutputStream file = new FileOutputStream(new  File(path));
		
		FileOutputStream fileSco = new FileOutputStream(new  File(pathSco));
		
		//FileOutputStream fileCSV = new FileOutputStream(new  File(pathCSV));
		
		Affectation.createPDF(sortie, file);
		Affectation.createPDFSco(sco, fileSco);
		ok.createCSV(allAffectation, pathCSV);
//		ok.createCSV(allAffectation);

		
		//System.out.println(ok.creerSortie());
		
		request.setAttribute("aff", ok.getSatisfaction());
		request.setAttribute("path", path);
		
		request.setAttribute("pathCSV", pathCSV);
		
		
		 
        request.setAttribute("options", basereader.getOptions(2018));
        request.setAttribute("eleves", basereader.getStudents(2017));
        
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_algo.jsp").forward(request, response);
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
        
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_algo.jsp").forward(request, response);
	}

}
