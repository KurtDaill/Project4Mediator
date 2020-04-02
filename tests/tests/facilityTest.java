package tests;
//These tests are all covered in FacilityTrackerTest. Delete
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import src.FacilityPackage.*;
import src.ManagerPackage.*;
import src.TimeStamps.*;

class facilityTest {
	@Test
	void testCapacity() {
		Facility fac = new Facility(null, null, null, 150, null, "fac1");
		assertEquals(fac.getCapacity(),150);
	}
	
	@Test
	void testProblem() { // This will test addProblem and getProblems()
		List<String> problems = new ArrayList<String>();
		Facility fac = new Facility(null, problems, null, 0, null, null);
		String prob = "This is a problem";
		fac.addProblem(prob); 
		assertEquals(fac.getProblems().contains("This is a problem"), true);
	}

	@Test
	void testDetails() {
		List<String> details = new ArrayList<String>();
		Facility fac = new Facility(details, null, null, 0, null, null);
		String det = "This is a detail";
		fac.addDetail(det);
		assertEquals(fac.getDetails().contains(det), true);
	}
	
	@Test
	void testInspections() {
		List<String> inspections = new ArrayList<String>();
		Facility fac = new Facility(null, null, inspections, 0, null, null);
		String ins = "This is an inspection";
		fac.addInspection(ins);
		assertEquals(fac.getInspections().contains(ins), true);
	}
	
	@Test
	void testStart() {
		Date start = new Date();
		Facility fac = new Facility(null, null, null, 0, start, null);
		assertEquals(fac.getStart(), start);
	}
	
	@Test
	void testProblemRate() { // Since this looks at a dynamic time the MONTH must be changed to 4 if worked on after 4/15/2020
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.MONTH, 3);
		startCal.set(Calendar.DATE, 15);
		startCal.set(Calendar.HOUR, 4);
		startCal.set(Calendar.MINUTE, 0);
		Date start = startCal.getTime();
		List<String> problems = new ArrayList<String>();
		Facility fac = new Facility(null,problems, null, 0, start, null);
		String prob = "This is a problem";
		fac.addProblem(prob); //adding 2 problems
		fac.addProblem(prob);
		assertEquals(fac.getProblemRate(), 2); // 2 problems per month
	}

	
}
