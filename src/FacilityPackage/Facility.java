package src.FacilityPackage;

import java.util.Date;
import java.util.List;

public class Facility extends Facilities {
    public Facility(List<String> details, List<String> problemHistory, List<String> inspections, int capacity, Date startDate, String name) {
        this.details = details;
        this.problemHistory = problemHistory;
        Inspections = inspections;
        this.capacity = capacity;
        this.startDate = startDate;
        this.name = name;
    }
}
