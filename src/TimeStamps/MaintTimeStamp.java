package src.TimeStamps;
import java.util.*;

public class MaintTimeStamp extends TimeStamp {
    private float costUSD;

    MaintTimeStamp(Date start, Date end, String desc, float cost){
        startTime = start;
        endTime = end;
        description = desc;
        costUSD = cost;
    }

    public float getCostUSD() {
        return costUSD;
    }
}
