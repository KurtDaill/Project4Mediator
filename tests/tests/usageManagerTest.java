package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;

import org.junit.jupiter.api.Test;
import src.FacilityPackage.*;
import src.ManagerPackage.*;
import src.TimeStamps.*;

class usageManagerTest {

	@Test
	void testAssignFacilityToUse() {
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.MONTH, 3);
		startCal.set(Calendar.DATE, 15);
		startCal.set(Calendar.HOUR, 4);
		startCal.set(Calendar.MINUTE, 0);
		Date start = startCal.getTime();
		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.MONTH, 3);
		endCal.set(Calendar.DATE, 30);
		endCal.set(Calendar.HOUR, 4);
		endCal.set(Calendar.MINUTE, 0);
		Date end = endCal.getTime();
		Facility fac = new Facility(null, null, null, 0, null, "fac1");
		Map<String, List<TimeStamp>> schedule = new HashMap<String, List<TimeStamp>>();
		Map<String, List<UsageTimeStamp>> usageSchedule = new HashMap<String, List<UsageTimeStamp>>();
		List<TimeStamp> appt = new ArrayList<TimeStamp>();
		List<UsageTimeStamp> UsageTimeStampList = new ArrayList<UsageTimeStamp>();
		UsageTimeStamp maintAppt = new UsageTimeStamp(start,end,"tesing the dates");
		appt.add(maintAppt);
		usageSchedule.put("fac1", UsageTimeStampList);
		schedule.put("fac1", appt);
		ScheduleManager scheduleMan = new ScheduleManager(schedule, null, null, null);
		UsageManager usageMan = new UsageManager(usageSchedule,null, null, scheduleMan);
		assertEquals(usageMan.assignFacilityToUse("fac1", maintAppt), true);
	}
	
	@Test
	void testListActualUsage() {
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.MONTH, 3);
		startCal.set(Calendar.DATE, 15);
		startCal.set(Calendar.HOUR, 4);
		startCal.set(Calendar.MINUTE, 0);
		Date start = startCal.getTime();
		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.MONTH, 3);
		endCal.set(Calendar.DATE, 30);
		endCal.set(Calendar.HOUR, 4);
		endCal.set(Calendar.MINUTE, 0);
		Date end = endCal.getTime();
		Facility fac = new Facility(null, null, null, 0, null, "fac1");
		Map<String, List<TimeStamp>> schedule = new HashMap<String, List<TimeStamp>>();
		Map<String, List<UsageTimeStamp>> usageSchedule = new HashMap<String, List<UsageTimeStamp>>();
		List<UsageTimeStamp> UsageTimeStampList = new ArrayList<UsageTimeStamp>();
		UsageTimeStamp maintAppt = new UsageTimeStamp(start,end,"testing the dates");
		UsageTimeStampList.add(maintAppt);
		usageSchedule.put("fac1", UsageTimeStampList);
		UsageManager usageMan = new UsageManager(usageSchedule,null, null, null);
		assertEquals(usageMan.listActualUsage("fac1").get(0).getStartTime(), start);
	}

}
