package Calcul.Calcul.base;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Calcul.Calcul.bean.GroupOp;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Preference;
import Calcul.Calcul.bean.Student;

/**
 * This base reader can only be used with a mysql database
 * 
 */
public class BaseReader extends BaseHandler {
	Map<Integer,Option> options = new HashMap<Integer, Option>();
	Map<Integer,Student> students = new HashMap<Integer, Student>();
	Map<Integer,GroupOp> groupOp = new HashMap<Integer, GroupOp>();
	Statement st;
	
	private Connection conn;

	
	public BaseReader() {

		/**
		 * A Java MySQL SELECT statement example. Demonstrates the use of a SQL SELECT
		 * statement against a MySQL database, called from a Java program.
		 * 
		 * Created by Alvin Alexander, http://alvinalexander.com
		 */
		try {
			// create our mysql database connection
			String myDriver = "com.mysql.cj.jdbc.Driver";
			String myUrl = "jdbc:mysql://localhost:3306/affectop_BD?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf-8";
			
			System.out.println(myUrl);
			Class.forName(myDriver);
			System.out.println("test");
			conn = DriverManager.getConnection(myUrl, "root", "1234");

			System.out.println("connexion etablie");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<ArrayList<Option>> getOptionsPerDays(int groupId){
		String query = "SELECT * FROM Options o;";

		ResultSet rs = getResultOfQuery(query);		
		ArrayList<ArrayList<Option>> result = new ArrayList<>();
		
		int nbDays = getNbDays();
		for(int d = 0 ; d < nbDays ; d++) {
			result.add(new ArrayList<>());
		}
		
		try {
			while (rs.next()) {
				String intitule = rs.getString("intitule");
				String mail = rs.getString("mail");
				
				int size = rs.getInt("size");
				int id = rs.getInt("id");
				
				Option o = new Option(size, intitule, mail, id);
				options.put(id,o);
				result.get(groupId-1).add(o);
				//System.out.format("%s, %s, %s, %s, %s, %s, %s\n", firstName, lastName,numetu,mail,token,step,year);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			return result;
		}
	}
	
	/**
	 * Return the database content associated with the tag
	 * 
	 * @param tag
	 * @param token
	 *            primary key used for accessing database specified value for a
	 *            given student
	 */
	public String tagRequest(String tag, String token) {
		String tagcontent = tag.substring(1, tag.length() - 1);
		System.out.println(tagcontent);
		if (tagcontent.equalsIgnoreCase("nom")) {
			return nameRequest(token);
		}
		if (tagcontent.equalsIgnoreCase("prenom")) {
			return firstNameRequest(token);
		}
		if (tagcontent.equalsIgnoreCase("LISTE_AFFECTATION")) {
			return affectListRequest(token);
		}
		if (tagcontent.equalsIgnoreCase("date")) {
			return dateRequest(token);
		}
		return "/!\\unknown tag/!\\ ";
	}
	
	public ArrayList<Student> getStudents(int year){
		String query = "SELECT * FROM Students where year = "+year+" ;";
		ResultSet rs = getResultOfQuery(query);
		
		ArrayList<Student> result = new ArrayList<>();
		
		int nbDays = getNbDays();
		// iterate through the java resultset
		try {
			while (rs.next()) {
				
				String mail = rs.getString("mail");
				Integer numEtu = rs.getInt("numEtudiant");
				String lastName = rs.getString("lastName");
				String firstName = rs.getString("firstName");
				
				Student s = new Student(firstName, lastName, mail,  numEtu); 
				students.put(numEtu,s);
				result.add(s);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	public List<Preference> getStudentPreferences(int numEtudiant){
		String query = 
			"SELECT * FROM Preferences where numEtudiant = '"+numEtudiant+"' ORDER BY optionId;" ;
		System.out.println(query);
		ResultSet rs = getResultOfQuery(query);
		ArrayList<Preference> pref = new ArrayList<>();
		try {
			while (rs.next()) {
				int choice = rs.getInt("choice");
				int optionId = rs.getInt("optionId");
				int groupId = rs.getInt("groupId");
				
				Preference p = new Preference(groupId, choice, optionId, numEtudiant);
				pref.add(p);
			}
			return pref;
		}
		catch(Exception e) {
			e.printStackTrace();
			return pref;
		}
	}
	
	public Map<Integer, List<Preference>> getPreferencesPerStudent(List<Student> liste){
		Map<Integer, List<Preference>> prefs = new HashMap<Integer, List<Preference>>();
		for (Student student : liste) {
			int numEtudiant = student.getNumEtudiant();
			prefs.put(numEtudiant, getStudentPreferences(numEtudiant));
		}
		return prefs;
	}
	
	/*
	public Map<Integer, List<Option>> getStudentPreferencesbis(int studentID){
		int nbGroup = getNbGroups();
		 Map<Integer, List<Option>> tmp = new HashMap<Integer, List<Option>>();
		
		 for(int i = 1; i <= nbGroup; i++) {
			 tmp.put(i, getStudentPreferenceByGroup(i, studentID));
		 }
		return tmp;
	}*/
	
	private int getNbGroups() {
		String query = 
				"select COUNT(DISTINCT(groupId)) from GroupOp;" ;
		ResultSet rs = getResultOfQuery(query);
		
		try {
			if(rs.next())
				return  rs.getInt("COUNT(DISTINCT(groupId))");
		}
		catch(Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return -1;
	}

	public int getNbDays() {
		String query = 
				"select MAX(groupId) from GroupOp;" ;
		ResultSet rs = getResultOfQuery(query);
		
		try {
			if(rs.next())
				return  rs.getInt("MAX(groupId)");
		}
		catch(Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return 1;
	}
	
	public int getNbOptions() {
		String query = 
				"select COUNT(*) from Options;" ;
		ResultSet rs = getResultOfQuery(query);
		
		try {
			if(rs.next())
				return  rs.getInt("COUNT(*)");
		}
		catch(Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return -1;
	}
	

	public Map<Option,ArrayList<Option>> getIncompatibilities(){
		Map<Option,ArrayList<Option>> incompatibilities = new HashMap<Option, ArrayList<Option>>();
		
		for(Option opt1 : options.values()) {
			incompatibilities.put(opt1,new ArrayList<>());
			for(Option opt2 : options.values()) {
			if(opt1.nom == opt2.nom)
				incompatibilities.get(opt1).add(opt2);
			}
		}
		return incompatibilities;
	}
	
	public Map<Student, ArrayList<Option>> getRepeater(int year) {
		String query = "SELECT * FROM Repeaters WHERE optionId IN ("
				+ "SELECT id FROM Options WHERE year ="+year+") ;";
		ResultSet rs = getResultOfQuery(query);
		
		Map<Student, ArrayList<Option>> repeaters = new HashMap<Student, ArrayList<Option>>();
		

		// iterate through the java resultset
		try {
			while (rs.next()) {
				
				int opt = rs.getInt("optionId");
				int numEtu = rs.getInt("numEtudiant");

				if(!repeaters.containsKey(students.get(numEtu)))
					repeaters.put(students.get(numEtu), new ArrayList<>());
					repeaters.put(students.get(numEtu), new ArrayList<>());
				System.out.println();
				System.out.println(students.get(numEtu));
				System.out.println(numEtu);
				System.out.println(students);
				System.out.println();
				repeaters.get(students.get(numEtu)).add(options.get(opt));
				//System.out.format("%s, %s, %s, %s, %s, %s, %s\n", firstName, lastName,numetu,mail,token,step,year);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return repeaters;
		}
		return repeaters;
	}
	
	public ArrayList<Option> getOptions(int year){
		String query = "SELECT * FROM Options where year = "+year+" ;";

		ResultSet rs = getResultOfQuery(query);		
		ArrayList<Option> result = new ArrayList<>();
		
		options.clear();
		
		try {
			while (rs.next()) {
				String intitule = rs.getString("intitule");
				String mail = rs.getString("mail");
				
				int size = rs.getInt("size");
				int id = rs.getInt("optionId");
				
				Option o = new Option(size, intitule, mail, id);
				options.put(id,o);
				result.add(o);
				//System.out.format("%s, %s, %s, %s, %s, %s, %s\n", firstName, lastName,numetu,mail,token,step,year);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			return result;
		}
	}
	
	public ArrayList<GroupOp> getGroupOptions() {
		String query = "SELECT * FROM GroupOp;";

		ResultSet rs = getResultOfQuery(query);		
		ArrayList<GroupOp> result = new ArrayList<>();
		
		groupOp.clear();
		
		try {
			while (rs.next()) {
				int groupId = rs.getInt("groupId");
				int optionId = rs.getInt("optionId");
				
				GroupOp grOp = new GroupOp(groupId, optionId);
				groupOp.put(groupId, grOp);
				result.add(grOp);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			return result;
		}
	}
	
	public Map<Integer, List<Integer>> getGroupOPs(ArrayList<GroupOp> result){
		Map<Integer, List<Integer>> groupOps = new HashMap<Integer, List<Integer>>();
		int Max = 0;
		for (GroupOp groupOp : result) {
			if(groupOp.getGroupId() > Max) {
				Max = groupOp.getGroupId();
			}
				
		}
		for(int i = 1; i <= Max; i++) {
			List<Integer> tmp = new ArrayList<Integer>();
			for (GroupOp groupOp : result) {
				if(groupOp.getGroupId() == i) {
					tmp.add(groupOp.getOptionId());
				}
			}
			groupOps.put(i, tmp);
		}
		return groupOps;
	}
	

	private String studentsQueryBuilder(String col, String token) {

		return "SELECT " + col + " FROM Students WHERE token=" + token + ";";
	}

	private String studentsQueryRequest(String col, String token) {

		String query = studentsQueryBuilder(col, token);
		//System.out.println(query);
		Statement st;
		String res = "";
		try {
			st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset

			while (rs.next()) {

				res = rs.getString(col);
			}
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;

	}

	private String teachersQueryBuilder(String col, String token) {
		return "SELECT " + col + " FROM Teachers WHERE token=\"" + token + "\";";

	}

	private String teachersQueryRequest(String col, String token) {

		String query = teachersQueryBuilder(col, token);
		//System.out.println(query);
		Statement st;
		String res = "";
		try {
			st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset

			while (rs.next()) {

				res = rs.getString(col);
			}
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;

	}

	public String nameRequest(String token) {
		return studentsQueryRequest("lastname", token);

	}

	public String firstNameRequest(String token) {

		return studentsQueryRequest("firstname", token);
	}
	
	public int numEtudiantRequest(String token) {

		return Integer.parseInt(studentsQueryRequest("numEtudiant", token));
	}

	public int tokenIdentification(String token) {

		String resQueryStudent = studentsQueryRequest("token", token);
		if (resQueryStudent != null && !resQueryStudent.equals(""))
			return 1;

		String resQueryTeacher = teachersQueryRequest("token", token);

		if (resQueryTeacher != null && !resQueryTeacher.equals(""))
			return 2;
		return 0;
	}

	//TODO ne marche pas car on va confondre les années et les sessions, il faut une jointure entre le prof et les options puis avec les prefs et leseleves
	public ArrayList<String> studentsValidateListRequest() {
		ArrayList<String> students = new ArrayList<>();

		String query = "SELECT token FROM Students WHERE numEtudiant in (SELECT DISTINCT numEtudiant FROM  Preferences );";
	//	String query = "SELECT * FROM Students INNER JOIN Teachers ON table1.id = table2.fk_id ;";
		Statement st;
		String res = "";
		try {
			st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset

			while (rs.next()) {

				res = rs.getString("token");
				students.add(res);
			}
			st.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return students;

	}

	private String affectListRequest(String token) {

		return "affectListRequest";
	}

	public String dateRequest(String token) {

		return "dateRequest";
	}

}
