package src.ManagerPackage;
import src.TimeStamps.*;
import src.FacilityPackage.*;
import java.util.*;
import java.util.Map;
public class MaintenanceManager {
	private List<Tuple<String, TimeStamp>> pendingRequests = new LinkedList<Tuple<String, TimeStamp>>();
	private Map<String, List<MaintTimeStamp>> maintDirectory = new HashMap<String, List<MaintTimeStamp>>();
	private Map<String, List<MaintTimeStamp>> maintHistoryDirectory = new HashMap<String, List<MaintTimeStamp>>();
	private Mediator med;
	private Date currentDate;

	public MaintenanceManager(Mediator mediate) {
		med = mediate;
	}

	void scheduleMaintenance(String facName, MaintTimeStamp maintenance) {
		maintDirectory.get(facName).add(maintenance);
	}

	void makeFacilitymaintenanceRequest(TimeStamp maintenance, String facName) {
		pendingRequests.add(new Tuple<String, TimeStamp>(facName, maintenance));
	}

	List<Tuple<String, TimeStamp>> listMaintRequests(){
		return pendingRequests;
	}

	List<MaintTimeStamp>getMaintHistory(String facName){
		return maintHistoryDirectory.get(facName);
	}

	void addMaintainance(MaintTimeStamp maint, String facName){
		maintDirectory.get(facName).add(maint);
	}

	List<MaintTimeStamp> listMaintainance(String facName){
		return maintDirectory.get(facName);
	}

	float calcMaintCostForFacility(String facName){
		float cost = 0;
		List<MaintTimeStamp> hist = maintHistoryDirectory.get(facName);
		for(MaintTimeStamp maint : hist){
			cost += maint.getCostUSD();
		}
		return cost;
	}

	public int calculateDownTimeForFacility(String facName) { //prints out the amount of time the building was not used while it was open
		int timeSinceBuildingOpen = med.lookUp(facName).getStart().compareTo(currentDate);
		List<MaintTimeStamp> hist = maintHistoryDirectory.get(facName);
		int totalDownTime = 0;
		for(int i = 0; i < hist.size(); i++) {
			int DownTime = hist.get(i).getStartTime().compareTo(hist.get(i).getEndTime());
			totalDownTime += DownTime;
		}
		int DownTime = timeSinceBuildingOpen - totalDownTime;
		return DownTime;
	}

	void update(Date newDate){
		currentDate = newDate;
		for(Map.Entry<String, List<MaintTimeStamp>> entry : maintDirectory.entrySet()) {
			List<MaintTimeStamp> schedule = entry.getValue();
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).getEndTime().before(currentDate) == true) {
					MaintTimeStamp pastDateHistory = schedule.get(i);
					schedule.remove(i);
					maintHistoryDirectory.put(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	public void updateFacilities(Facilities newFac){
		maintDirectory.put(newFac.getName(), new LinkedList<MaintTimeStamp>());
	}
}
