package Internship;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Distance class
 */
public class Distance {
    List<Atm> atmList;

    /**
     * Constructor with parameters
     * @param atmList the list of ATMs
     */
    public Distance(List<Atm> atmList) {
        this.atmList = atmList;
    }

    /**
     * Returns the walking time between the starting point and ATMs / between 2 ATMs
     * @param start the starting point / ATM
     * @param end the destination ATM
     * @return the walking time between start and end
     */
    public int getWalkingTime(Atm start, Atm end){
        //the starting point is represented by null
        if(start==null && end.equals(atmList.get(0))) return 5;
        if(start==null && end.equals(atmList.get(1))) return 60;
        if(start==null && end.equals(atmList.get(2))) return 30;
        if(start==null && end.equals(atmList.get(3))) return 45;

        if((start.equals(atmList.get(0)) && end.equals(atmList.get(1))) || (start.equals(atmList.get(1)) && end.equals(atmList.get(0)))) return 40;
        if((start.equals(atmList.get(0)) && end.equals(atmList.get(3)) || (start.equals(atmList.get(3)) && end.equals(atmList.get(0))))) return 45;
        if((start.equals(atmList.get(1))&& end.equals(atmList.get(2))) || (start.equals(atmList.get(2)) && end.equals(atmList.get(1)))) return 15;
        if((start.equals(atmList.get(2)) && end.equals(atmList.get(0))) || (start.equals(atmList.get(0)) && end.equals(atmList.get(2)))) return 40;
        if((start.equals(atmList.get(2)) && end.equals(atmList.get(3))) || (start.equals(atmList.get(3)) && end.equals(atmList.get(2)))) return 15;
        if((start.equals(atmList.get(3)) && end.equals(atmList.get(1))) || (start.equals(atmList.get(1)) && end.equals(atmList.get(3)))) return 30;

        return -1;
    }

    /**
     * Sorts the ATMs after the walking time from the current location
     * @param start the current location
     * @return the list of the sorted ATMs
     */
    public List<Atm> atmsOrder(Atm start){
        List<Atm> returnedList=new ArrayList<>();

        for (Atm end:atmList
             ) {
            if(start==null){ //the user is in the starting point
                returnedList.add(end);
            }
            else if(!start.equals(end)){
                returnedList.add(end);
            }
        }

        return returnedList
                .stream()
                .sorted(Comparator.comparingInt(a -> getWalkingTime(start, a)))
                .sorted(Comparator.comparing(Atm::getClosingTime)) //sort the list after the closing time as well, in case we have at least 2 ATMs for which the walking time is equal
                .collect(Collectors.toList());
    }

    /**
     * Returns the nearest and open ATM, which is not in the current list
     * @param start the current location
     * @param currentTime the current time
     * @param currentList the current list, which contains the ATMs
     * @return
     */
    public Atm findNearestAndOpen(Atm start,LocalTime currentTime,List<Atm> currentList){
        for (Atm a:atmsOrder(start)
             ) {
            if(a.isOpen(currentTime) && !currentList.contains(a)) return a;
        }

        return null;
    }

    /**
     * Returns the ATM which opens in the shortest amount of time and which is not in the current list
     * @param start the current location
     * @param currentList the current list
     * @return the ATM
     */
    public Atm fastestOpen(Atm start,List<Atm> currentList){
        List<Atm> atms=atmList
                .stream()
                .filter(a->!currentList.contains(a))
                .sorted(Comparator.comparing(Atm::getOpeningTime))
                //sort the list after the walking time as well, in case we have at least 2 ATMs which open at the same time
                .sorted(Comparator.comparingInt(a -> getWalkingTime(start, a)))
                .collect(Collectors.toList());

        return atms.get(0);
    }
}
