package tests;

import static org.junit.jupiter.api.Assertions.*;


import java.util.*;

import org.junit.jupiter.api.Test;
import src.FacilityPackage.*;
import src.ManagerPackage.*;
import src.TimeStamps.*;


class scheduleManagerTest {

	@Test
	void testGetSchedule() {
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
		List<TimeStamp> appt = new ArrayList<TimeStamp>();
		UsageTimeStamp maintAppt = new UsageTimeStamp(start,end,"tesing the dates");
		appt.add(maintAppt);
		schedule.put("fac1", appt);
		ScheduleManager scheduleMan = new ScheduleManager(schedule, null, null, null);
		assertEquals(scheduleMan.getSchedule("fac1").get(0).getStartTime(), start);
	}
	
	@Test
	void testIsInInterval() {
		
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
		List<TimeStamp> appt = new ArrayList<TimeStamp>();
		UsageTimeStamp maintAppt = new UsageTimeStamp(start,end,"tesing the dates");
		appt.add(maintAppt);
		schedule.put("fac1", appt);
		ScheduleManager scheduleMan = new ScheduleManager(schedule, null, null, null);
		assertEquals(scheduleMan.isInUseDuringInterval("fac1", maintAppt), false);
	}

}
