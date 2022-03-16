/**
 * Name: Kathy Chen
 * PID: A17030814
 * Sources: None
 * 
 * This is the MyCalendar java file for PA 8. It contains the MyCalendar class 
 * which specifies the functionality of the calendar treeMap object to book and
 * events.
 */

/**
 * A MyCalendar class holds a reference to a treeMap calendar object which 
 * holds information regarding what days already have events booked. This class
 * contains a constructor method to initialize the calendar object, an accessor
 * method to return the calendar object and the book method which adds events 
 * to the calendar treeMap if there are no conflicts.
 */
public class MyCalendar {
    //instance variable
    MyTreeMap<Integer, Integer> calendar;
    
    /**
     * Constructor method to initialize calendar as a MyTreeMap object
     */
    public MyCalendar() {
        this.calendar = new MyTreeMap<Integer,Integer>();
    }
    
    /**
     * Adds the event with the given start and end dates if it does not cause
     * double booking
     * @param start - start date of the event to be booked
     * @param end - end date of the event to be booked
     * @return boolean whether event was booked successfully
     */
    public boolean book(int start, int end) {
        //throw exception if start is invalid
        if(start < 0 || start >= end){
            throw new IllegalArgumentException();
        }
        //add event if calendar has no events
        if(this.calendar.size()==0){
            this.calendar.put(start,end);
            return true;
        }

        //initialize ceiling and floor keys 
        Integer startCeilingKey = this.calendar.ceilingKey(start);
        Integer startFloorKey = this.calendar.floorKey(start);

        //new event to be inserted between existing events in calendar
        if( startCeilingKey != null && startFloorKey != null &&
            this.calendar.get(startFloorKey).compareTo(start) <= 0 && 
            startCeilingKey.compareTo(end) >= 0){
            this.calendar.put(start,end);
            return true;
        }
        //new event to be inserted before an event
        else if(startCeilingKey != null && 
                startCeilingKey.compareTo(end) >= 0){
            this.calendar.put(start,end);
            return true;
        }
        //new event to be inserted after an event
        else if(startFloorKey != null &&
                this.calendar.get(startFloorKey).compareTo(start) <= 0){
            this.calendar.put(start,end);
            return true;
        }
        //event already booked during this time in calendar
        return false;
    }

    /**
     * accessor method that returns the calendar MyTreeMap object
     * @return calendar MyTreeMap variable of this MyCalendar object
     */
    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}