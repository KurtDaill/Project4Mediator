package src;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import src.FacilityPackage.FacilityTracker;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        FacilityTracker parentTracker = (FacilityTracker) context.getBean("tracker");
    }
}
