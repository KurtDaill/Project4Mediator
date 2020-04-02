package src.ManagerPackage;
import src.TimeStamps.*;
import src.FacilityPackage.*;
import java.util.*;
import java.util.Map;
public class MaintenanceManager {
	private List<Tuple<String, TimeStamp>> pendingRequests = new LinkedList<Tuple<String, TimeStamp>>();
	private Map<String, List<MaintTimeStamp>> maintDirectory = new HashMap<String, List<MaintTimeStamp>>();
	private Map<String, List<MaintTimeStamp>> maintHistoryDirectory = new HashMap<String, List<MaintTimeStamp>>();
	private ScheduleManager schedule;
	private FacilityTracker tracker;

	public MaintenanceManager(List<Tuple<String, TimeStamp>> pendingRequests, Map<String, List<MaintTimeStamp>> maintDirectory, Map<String, List<MaintTimeStamp>> maintHistoryDirectory, ScheduleManager schedule, FacilityTracker tracker) {
		this.pendingRequests = pendingRequests;
		this.maintDirectory = maintDirectory;
		this.maintHistoryDirectory = maintHistoryDirectory;
		this.schedule = schedule;
		this.tracker = tracker;
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
		int timeSinceBuildingOpen = tracker.lookUp(facName).getStart().compareTo(schedule.getDate());
		List<MaintTimeStamp> hist = maintHistoryDirectory.get(facName);
		int totalDownTime = 0;
		for(int i = 0; i < hist.size(); i++) {
			int DownTime = hist.get(i).getStartTime().compareTo(hist.get(i).getEndTime());
			totalDownTime += DownTime;
		}
		int DownTime = timeSinceBuildingOpen - totalDownTime;
		return DownTime;
	}

	void update(Date currentDate){
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
