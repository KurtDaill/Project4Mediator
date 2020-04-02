package src.FacilityPackage;
import java.util.*;

public abstract class Facilities {
	protected List<String> details = new ArrayList<String>();
	protected List<String> problemHistory = new ArrayList<String>();
	protected List<String> Inspections = new ArrayList<String>();
	protected int capacity;
	protected Date startDate = new Date();
	protected Date currentDate = new Date();
	protected String name;



	public int getProblemRate() { // This will calculate the rate of problems based on the timeframe of the facility
		int numberOfProblems = problemHistory.size(); 
		int timeFrame = startDate.compareTo(currentDate);
		int problemRate = numberOfProblems / timeFrame;
		return problemRate;
	}

	public List<String> getProblems(){ //returns the problem history list
		return problemHistory; 
	}

	public List<String> getInspections(){// returns Inspections
		return Inspections;
	}


	public List<String> getDetails(){
	    return details;
    }

	public int getCapacity(){ return capacity; }

	public Date getStart(){
		return startDate;
	}

	public String getName(){
		return name;
	}
	
	public void addDetail(String detail) { 
		details.add(detail);			    
	}
	
	public void addProblem(String problem) {
		problemHistory.add(problem);
	}
	
	public void addInspection(String inspection) {
		Inspections.add(inspection);
	}
}
