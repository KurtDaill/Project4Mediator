package src.FacilityPackage;
import java.util.*;
import src.ManagerPackage.*;
public class FacilityTracker{
	private Map<String, Facilities> facilityDirectory = new HashMap<String, Facilities>();
	private Mediator med;

	public FacilityTracker(Mediator mediate) {
		med = mediate;
	}

	public List<String> listFacilityProblems(String facName){
		return lookUp(facName).getProblems();
	}

	public List<String> listInspections(String facName){
		return lookUp(facName).getInspections();
	}

	public List<String> getFacilityInformation(String facName){
		return lookUp(facName).getDetails();
	}

	public int getAvailableCapacity(String facName){
		return lookUp(facName).getCapacity();
	}

	public float calcProblemRateForFacility(String facName){
		return lookUp(facName).getProblemRate();
	}

	public void addFacilityDetail(String facName, String newDetail){
		lookUp(facName).getDetails().add(newDetail);
	}

	public void updateFacilities(Facilities newFac){
		facilityDirectory.put(newFac.getName(), newFac);
	}

	public Facilities lookUp(String facName){
		return facilityDirectory.get(facName);
	}
}
