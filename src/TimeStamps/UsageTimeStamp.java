package src.TimeStamps;
import java.util.Date;

public class UsageTimeStamp extends TimeStamp{
    public UsageTimeStamp(Date start, Date end, String desc){
        startTime = start;
        endTime = end;
        description = desc;
    }
}
