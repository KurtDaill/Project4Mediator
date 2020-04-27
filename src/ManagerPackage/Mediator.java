package src.ManagerPackage;
import src.FacilityPackage.*;
import src.TimeStamps.TimeStamp;

import java.util.Date;
public class Mediator {
    FacilityTracker facTracker;
    UsageManager useManager;
    MaintenanceManager maintManager;
    ScheduleManager scheduleManager;
    Date currentDate;

    public Mediator(FacilityTracker facTracker, UsageManager useManager, MaintenanceManager maintManager, ScheduleManager scheduleManager) {
        this.facTracker = facTracker;
        this.useManager = useManager;
        this.maintManager = maintManager;
        this.scheduleManager = scheduleManager;
    }

    public void updateTime(Date newDate){
        currentDate = newDate;
        maintManager.update(currentDate);
        useManager.update(currentDate);
    }

    public void newFacility(Facility newFac){
        useManager.updateFacilities(newFac);
        maintManager.updateFacilities(newFac);
        scheduleManager.updateFacilities(newFac);
        facTracker.updateFacilities(newFac);
    }

    public boolean facInUseDuringInterval(String fac, TimeStamp checkTime) {
        return scheduleManager.isInUseDuringInterval(fac, checkTime);
    }

    public Facilities lookUp(String name){
       return facTracker.lookUp(name);
    }
}
