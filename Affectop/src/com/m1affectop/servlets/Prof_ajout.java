package com.m1affectop.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.base.BaseWriter;
import Calcul.Calcul.excel.ExcelReader;
import Calcul.Calcul.exceptions.TokenLengthException;
import Calcul.Calcul.token.Token;

/**
 * Servlet implementation class Prof_ajout
 */
@WebServlet("/Prof_ajout")
public class Prof_ajout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final int TAILLE_TAMPON = 10240;
	public static final String CHEMIN = "chemin";
    public static final String CHEMIN_FICHIERS = "/temp/"; // A changer
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prof_ajout() {
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
		ExcelReader er = new ExcelReader();
		ArrayList<String> tokenlist;
		
		String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequestTeacher(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequestTeacher(token);
        request.setAttribute("firstname", firstname);
        
        request.setAttribute("path", new File(".").getCanonicalPath());
        
        try {
			tokenlist = Token.generateTokenList(20, 10);
		} catch (TokenLengthException e1) {
			e1.printStackTrace();
			return;
		}
        
        /*
        bw.writeStudent(2018, "AISSA", "abdulmaged", "15031366", "lattazdy.fall@etu.univ-amu.fr","qg9zkjpuydlhsdhjg4d2", "SIN301");
        bw.writeStudent(2018, "AIT-ALI", "maxime", "12082486", "latdazdty.fall@etu.univ-amu.fr",tokenlist.get(1), "SIN301");
        bw.writeStudent(2018, "BENAHMED", "martin", "13000771", "ldadatty.fall@etu.univ-amu.fr",tokenlist.get(2), "SIN301");
        bw.writeStudent(2018, "EL BOURSOUMI", "vadym", "14006268", "lafezeftty.fall@etu.univ-amu.fr",tokenlist.get(3), "SIN301");
        bw.writeStudent(2018, "FALL", "maxime", "14004797", "lhtyhtatty.fall@etu.univ-amu.fr",tokenlist.get(4), "SIN301");
        bw.writeStudent(2018, "GABIOT", "hugo", "12003401", "lhtyhatty.fall@etu.univ-amu.fr",tokenlist.get(5), "SIN301");
        bw.writeStudent(2018, "GARCIA", "nam", "15029310", "latsdcsty.fall@etu.univ-amu.fr",tokenlist.get(6), "SIN301");
        bw.writeStudent(2018, "LALAMI", "chriss", "15021392", "latukty.fall@etu.univ-amu.fr",tokenlist.get(7), "SIN301");
        bw.writeStudent(2018, "MIMOUNI", "soufiane", "11520172", "latdzdaty.fall@etu.univ-amu.fr",tokenlist.get(8), "SIN301");
        bw.writeStudent(2018, "NAZEF", "robin", "13014239", "latfdbbty.fall@etu.univ-amu.fr",tokenlist.get(9), "SIN301");
        bw.writeStudent(2018, "TEST", "test", "13014239", "test.test@etu.univ-amu.fr","", "SIN301");
		*/

        //er.request("./opt/tomcat/Affectop/Webcontent/datas/Saint_Charles.xlsx");
        
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
		this.getServletContext().getRequestDispatcher("/WEB-INF/prof_ajout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BaseReader basereader = new BaseReader();
        ExcelReader er = new ExcelReader();

        String token = request.getParameter("token");
        request.setAttribute("token", token);
        
        String name = basereader.nameRequestTeacher(token);
        request.setAttribute("name", name);
        
        String firstname = basereader.firstNameRequestTeacher(token);
        request.setAttribute("firstname", firstname);
        
        /*
         * Lecture du param�tre 'chemin' pass� � la servlet via la d�claration
         * dans le web.xml
         */
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );
        
        // On r�cup�re le champ du fichier
        Part part = request.getPart("fichier");

        // On v�rifie qu'on a bien re�u un fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            
            /*
            * On doit donc faire en sorte de ne s�lectionner que le nom et
            * l'extension du fichier, et de se d�barrasser du superflu.
            */
            nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 )
                   .substring( nomFichier.lastIndexOf( '\\' ) + 1 );
            	
            // On �crit d�finitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, chemin);

            request.setAttribute(nomChamp, nomFichier);
        }
        try {
        	er.request("C:/temp/"+nomFichier);
        }
        catch (Exception e) {
        	request.setAttribute("error", true);
		}
        
        request.setAttribute("options", basereader.getOptions(2018));
        request.setAttribute("eleves", basereader.getStudents(2017));
        
        if(name == "") {
        	this.getServletContext().getRequestDispatcher("/WEB-INF/error_token.jsp").forward(request, response);
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/prof_ajout.jsp").forward(request, response);
    }
	
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            /*
             * Lit le fichier re�u et �crit son contenu dans un fichier sur le
             * disque.
             */
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
            	if(sortie != null)
            		sortie.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
            try {
            	if(entree != null)
            		entree.close();
            } catch (IOException e) {
            	e.printStackTrace();
            }
        }
    }
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }   
    
}
