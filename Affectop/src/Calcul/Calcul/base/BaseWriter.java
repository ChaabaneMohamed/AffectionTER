package Calcul.Calcul.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.algorithms.calcul.Result;
import Calcul.Calcul.algorithms.calcul.Result.Affectation;


public class BaseWriter extends BaseHandler{
	Statement st ;
	
	public void initConnection() {

		try {
			// create our mysql database connection
			String myDriver = "com.mysql.cj.jdbc.Driver";
			
			String myUrl = "jdbc:mysql://localhost:3306/affectop_BD?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&autoReconnect=true&useSSL=false";
			
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, "root", "1234");
			
			st = conn.createStatement();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void endConnection() {
		try {
		st.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeRepeaters(Map<Student,ArrayList<Option>> repeaters, int year) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Repeaters (numEtudiant,optionId,year)\nVALUES\n");
		
		for(Student s : repeaters.keySet()) {
			for(Option opt : repeaters.get(s))
				query.append("("+s.numEtu+","+opt.id+","+year+"),");
		}
		query.replace(query.length()-1, query.length(), ";");
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeOneRepeater(int numEtudiant, int optionId, int year) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Repeaters (numEtudiant,optionId,year)\nVALUES\n");
		query.append("("+numEtudiant+","+optionId+","+year+"),");
		query.replace(query.length()-1, query.length(), ";");
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
		}		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void writeResults(int year,Result r ){
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Results (optionId, numEtudiant)\n"); 
		try {
			for (Affectation aff : r.results) {
				for(Option opt : aff.options)
					query.append("(" +opt.id+" "+aff.s.numEtu+"),\n"); 
			}
			query.replace (query.length()-2,query.length()-1,";");
			System.out.println(query.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	/*public void createTableStudent(int year,
			String lastName,
			String firstName,
			String numEtu,
			String mail,
			String token,
			String step) {

				String query = "CREATE TABLE Students ("
						+ "id INT NOT NULL"
						+ "year INT NOT NULL,"
						+ "lastName VARCHAR(50),"
						+ "firstName VARCHAR(50),"
						+ "numEtudiant VARCHAR(8),"
						+ "mail VARCHAR(50),"
						+ "token VARCHAR(50),"
						+ "step VARCHAR(50)"
						+ "PRIMARY KEY (id));";
							
				try {
				System.out.println(query);
				st.executeUpdate(query);
				}
				catch (Exception e) {
				e.printStackTrace();
				}
			}*/

	public void writeStudent(int year,
							String lastName,
							String firstName,
							String numEtu,
							String mail,
							String token,
							String step) {
		
		String query = "INSERT INTO Students (year,lastName,firstName,numEtudiant,mail,token,step)"+
							"VALUES("+year+",'"+lastName+"','"+firstName+"','"+numEtu+"','"+mail+"','"+token+"','"+step+"')";
		try {
			System.out.println(query);
			st.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteStudent(String numEtudiant) {
		String query = "DELETE FROM Students WHERE numEtudiant ="+numEtudiant+";";
				
		try {
			System.out.println(query);
			st.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writePreference(int numEtudiant, ArrayList<Option> preferences) {
        int index = 1;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Preferences (choice,optionId,numEtudiant) \nVALUES\n");
        for(Option opt : preferences) {
            query.append("("+index+","+opt.id+","+numEtudiant+"),");
            index ++;
        }
        query.replace(query.length()-1, query.length(), ";");
        
        try {
            System.out.println(query);
            st.executeUpdate(query.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
	public void deleteOption(int id) {
		String query = "DELETE FROM Options WHERE id ="+id+";";
				
		try {
			System.out.println(query);
			st.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void writeOptions(ArrayList<Option> options, int year) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Options (intitule,description,size,optionGroup,year) \nVALUES\n");
		for(Option opt : options) {
			query.append("('"+opt.nom+"','"+opt.description+"',"+opt.size+","+opt.day+","+year+"),");
		}
		query.replace(query.length()-1, query.length(), ";");
		
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeOneOption(Option opt, int year) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Options (intitule,description,size,optionGroup,year) \nVALUES\n");
		query.append("('"+opt.nom+"','"+opt.getDescription()+"',"+opt.size+","+opt.day+","+year+"),");
		query.replace(query.length()-1, query.length(), ";");
		
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}