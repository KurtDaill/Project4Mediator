package src.ManagerPackage;

import src.TimeStamps.*;
import src.FacilityPackage.*;
import java.util.*;

public class UsageManager{
	Map<String, List<UsageTimeStamp>> usageDirectory = new HashMap<String, List<UsageTimeStamp>>();
	Map<String, List<UsageTimeStamp>> usageHistoryDirectory = new HashMap<String, List<UsageTimeStamp>>();
	Mediator med;
	Date currentDate;
	public UsageManager(Mediator mediate) {
		med = mediate;
	}

	public boolean assignFacilityToUse(String facName, UsageTimeStamp use) {
		if(!med.facInUseDuringInterval(facName, use)){
			usageDirectory.get(facName).add(use);
			return true;
		}
		return false;
	}
	public List<UsageTimeStamp> listActualUsage(String facName){
		return usageDirectory.get(facName);
	}

	float calcUsageRate(String facName){
		int timeSinceBuildingOpen = med.lookUp(facName).getStart().compareTo(currentDate);
		int totalUsageTime = 0;
		for(Map.Entry<String, List<UsageTimeStamp>> entry : usageHistoryDirectory.entrySet()) {
			List<UsageTimeStamp> list = entry.getValue();
			for(UsageTimeStamp use : list) {
				int UsageTime = use.getStartTime().compareTo(use.getEndTime());
				totalUsageTime += UsageTime;
			}
		}
		int usageRate = totalUsageTime / timeSinceBuildingOpen;
		return usageRate;
	}

	void update(Date newDate) {
		currentDate = newDate;
		for (Map.Entry<String, List<UsageTimeStamp>> entry : usageDirectory.entrySet()) {
			List<UsageTimeStamp> schedule = entry.getValue();
			for (int i = 0; i < schedule.size(); i++) {
				if (schedule.get(i).getEndTime().before(currentDate) == true) {
					UsageTimeStamp pastDateHistory = schedule.get(i);
					schedule.remove(i);
					usageHistoryDirectory.put(entry.getKey(), entry.getValue());
				}
			}
		}
	}

	public void updateFacilities(Facilities newFac){
		usageDirectory.put(newFac.getName(), new LinkedList<UsageTimeStamp>());
	}
}
