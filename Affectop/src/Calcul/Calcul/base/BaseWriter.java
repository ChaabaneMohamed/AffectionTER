package Calcul.Calcul.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Preference;
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.algorithms.calcul.Result;
import Calcul.Calcul.algorithms.calcul.Result.Affectation;


public class BaseWriter extends BaseHandler{
	Statement st ;
	
	public void initConnection() {

		try {
			// create our mysql database connection
			String myDriver = "com.mysql.cj.jdbc.Driver";
			
			String myUrl = "jdbc:mysql://localhost:3306/affectop_BD?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=utf-8";
			
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
				query.append("("+s.getNumEtudiant()+","+opt.id+","+year+"),");
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
	
	public void writeOneRepeater(int numEtudiant, int optionId) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Repeaters (numEtudiant,optionId)\nVALUES\n");
		query.append("("+numEtudiant+","+optionId+");");
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
					query.append("(" +opt.id+" "+aff.s.getNumEtudiant()+"),\n"); 
			}
			query.replace (query.length()-2,query.length()-1,";");
			System.out.println(query.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	

	public void writeStudent(int year,
							String lastName,
							String firstName,
							int numEtu,
							String mail,
							String token,
							String step) {
		
		StringBuilder query = new StringBuilder();
		
		query.append("INSERT INTO Students (year,lastName,firstName,numEtudiant,mail,token,step) VALUES\n");
		query.append("('"+year+"','"+lastName+"','"+firstName+"',"+numEtu+",'"+mail+"','"+token+"','"+step+"'),");
		query.replace(query.length()-1, query.length(), ";");
		
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
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
	
	public void writePreferences(int numEtudiant, Map<Integer, List<Option>> prefs) {
        int index;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Preferences (groupId, choice,optionId,numEtudiant) \nVALUES\n");
        for (int i = 1; i <= prefs.size(); i++) {
        	index = 1;
        	for(Option opt : prefs.get(i)) { 
        		System.out.println("("+i+","+index+","+numEtudiant+"),");
                query.append("("+i+","+index+","+opt.id+","+numEtudiant+"),");
                index ++;
            }
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
	
	public void writePreference(int numEtudiant, int groupId, int choice, int optionId) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Preferences (groupId, choice,optionId,numEtudiant) \nVALUES\n");
       
        query.append("("+groupId+","+choice+","+optionId+","+numEtudiant+");");
        
        try {
            System.out.println(query);
            st.executeUpdate(query.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }     
    }
	
	public void writePreference(Preference p) {
		StringBuilder query = new StringBuilder();
        query.append("INSERT INTO Preferences (groupId, choice,optionId,numEtudiant) \nVALUES\n");
       
        query.append("("+p.getGroupId()+","+p.getChoice()+","+p.getOptionId()+","+p.getNumEtudiant()+");");
        
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
		query.append("INSERT INTO Options (intitule,description,size,year) \nVALUES\n");
		for(Option opt : options) {
			query.append("('"+opt.nom+"','"+opt.description+"',"+opt.size+","+year+"),");
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
		query.append("INSERT INTO Options (intitule, mail,size,year) \nVALUES\n");
		query.append("('"+opt.nom+"','"+opt.getMail_prof()+"',"+opt.size+","+year+"),");
		query.replace(query.length()-1, query.length(), ";");
		
		try {
			System.out.println(query);
			st.executeUpdate(query.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeOneGroupOp(int groupId , int optionId) {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO GroupOp (groupId, optionId) \nVALUES\n");
		query.append("("+groupId+","+optionId+"),");
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