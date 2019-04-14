package Internship;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Solution class
 */
public class Solution {
    private List<Atm> atms;
    private List<CreditCard> creditCards;
    private LocalDateTime currentDate;

    /**
     * Constructor with parameters
     * @param atms the list of ATMs
     * @param creditCards the list of credit cards
     * @param currentDate the current date and time
     */
    public Solution(List<Atm> atms, List<CreditCard> creditCards, LocalDateTime currentDate) {
        this.atms = atms;
        this.creditCards = creditCards;
        this.currentDate = currentDate;
    }

    /**
     * Returns the list of ATMSs
     * @return the list of ATM
     */
    public List<Atm> getAtms() {
        return atms;
    }

    /**
     * Returns the list of credit cards
     * @return the list of credit cards
     */
    public List<CreditCard> getCreditCards() {
        return creditCards;
    }


    /**
     * Returns the current date and time
     * @return the current date and time
     */
    public LocalDateTime getCurrentDate() {
        return currentDate;
    }


    /**
     * Returns the route which the user should follow
     * @return the list of ATMs in their right order
     */
    public List<Atm> getAtmsRoute(){
        List<Atm> returnedList=new ArrayList<>();


        float currentSumOfMoney=0;
        Atm currentAtm=null; //the user is in the starting point
        Distance distance=new Distance(atms);

        while(currentSumOfMoney!=8000){
            boolean exists=true;
            Atm best=distance.findNearestAndOpen(currentAtm,currentDate.toLocalTime(),returnedList);
            if(best==null) { //there is no ATM which is open
                exists=false;
                best = distance.fastestOpen(currentAtm, returnedList); //best will be the ATM which opens in the shortest amount of time
                //add the amount of time remained until the opening of the ATM to the current date
                long minutes= Math.abs(ChronoUnit.MINUTES.between(best.getOpeningTime(),currentDate));
                currentDate=LocalDateTime.of(currentDate.toLocalDate(),currentDate.toLocalTime().plusMinutes(minutes));
            }

            returnedList.add(best);
            for(int c=creditCards.size()-1;c>=0;c--){
                CreditCard creditCard=creditCards.get(c);
                //withdraw 2000 lei from each ATM
                if(creditCard.isValid(currentDate.toLocalDate()) && creditCard.getAvailableAmount()>=2000 && best.getAmountOfMoney()>=2000){
                    currentSumOfMoney+=2000;
                    creditCard.withdraw(2000);
                    break;
                }
            }

            //add the walking time to the current date
            if(returnedList.size()!=4 && exists) currentDate=LocalDateTime.of(currentDate.toLocalDate(),currentDate.toLocalTime().plusMinutes(distance.getWalkingTime(currentAtm,best)));

            currentAtm=best;
        }

        return returnedList;
    }
}
