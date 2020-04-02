package src.TimeStamps;
import java.util.*;
/*
interface TimeStamp {
	String description = "";
	Date startTime = new Date();
	Date endTime = new Date();
	double costUSD = 0;
}
*/
public abstract class TimeStamp {
	protected String description;
	protected Date startTime;
	protected Date endTime;

	public boolean doWeOverlap(TimeStamp query) {
		if(startTime.before(query.getStartTime()) && !endTime.before(query.getStartTime())){
			return true;
		}
		return false;
	}

	public String getDescription(){
		return  description;
	}

	public Date getStartTime(){
		return startTime;
	}

	public Date getEndTime(){
		return endTime;
	}
}
