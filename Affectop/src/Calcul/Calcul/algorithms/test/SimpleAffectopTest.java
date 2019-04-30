package Calcul.Calcul.algorithms.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.algorithms.calcul.SimpleAffectop;

public class SimpleAffectopTest {

	
	@Test
	public void randTestSimple() {
		ArrayList<ArrayList<Option>> options = AffectopTest.randomOptions(3, new int[]{5,5,5}, new int[]{6,7,7}, new int[]{20,20,20}, new int[]{30,30,30});
		ArrayList<Student> students = AffectopTest.randomStudents(500, options, 3);
		HashMap<Student, LinkedList<Option>> available = new HashMap<>();
		
		for(int i = 0 ; i < 3 ; i ++) {
			available.clear();
			for(Student s : students) {
				available.put(s, new LinkedList<Option>(s.preferences.get(i)));
			}
		}
		SimpleAffectop aff = new SimpleAffectop(students,options.get(2),available,2);
		aff.mariagesStable();;
		assertTrue(aff.isStable(students));
	}
	
	@Test
	public void multipleRandTestSimple() {
		for(int i = 0; i < 1000; i ++) {
			randTestSimple();
			if(i%100 == 0)
				System.out.println(i);
		}
	}	
	
	@Test
	public void willingToExchangeTest() {
		ArrayList<Student> students = new ArrayList<>();
		ArrayList<Option> options = new ArrayList<>();
		
		Option opt1 = new Option(1, "1",1, 0);
		options.add(opt1);
		Option opt2 = new Option(1, "2",1, 0);
		options.add(opt2);
		Option opt3 = new Option(1, "3",1, 0);
		options.add(opt3);

		LinkedList<Option> l1 = new LinkedList<>(Arrays.asList(new Option[]{opt1,opt2,opt3}));
		ArrayList<LinkedList<Option>> liste1= new ArrayList<>();
		liste1.add(l1);		

		LinkedList<Option> l2 = new LinkedList<>(Arrays.asList(new Option[]{opt2,opt1,opt3}));
		ArrayList<LinkedList<Option>> liste2= new ArrayList<>();
		liste2.add(l2);

		LinkedList<Option> l3 = new LinkedList<>(Arrays.asList(new Option[]{opt3,opt2,opt1}));
		ArrayList<LinkedList<Option>> liste3= new ArrayList<>();
		liste3.add(l3);

		Student s1 = new Student("1", liste1,1);
		Student s2 = new Student("2", liste2,1);
		Student s3 = new Student("3", liste3,1);
		students.add(s1);students.add(s2);students.add(s3);
		
		HashMap<Student, LinkedList<Option>> available = new HashMap<>();
		
		SimpleAffectop saff = new SimpleAffectop(students, options, available, 0);
		s1.affected[0]=opt2; s2.affected[0]=opt3; s3.affected[0]=opt1;
				
		
		
		//System.out.println(saff.areWillingToExchange(s1, s2));
		assertFalse(saff.areWillingToExchange(s1, s2));
		assertTrue(saff.areWillingToExchange(s1,  s3));
		assertTrue(saff.areWillingToExchange(s3, s2));
	}

}
