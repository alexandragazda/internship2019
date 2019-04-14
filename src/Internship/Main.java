package Internship;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Returns a list with the ATMs
     * @return a list with the ATMs
     */
    public static List<Atm> getAtms(){
        List<Atm> atms=new ArrayList<>();
        atms.add(new Atm("ATM1", LocalTime.of(12,00),LocalTime.of(18,00)));
        atms.add(new Atm("ATM2",LocalTime.of(10,00),LocalTime.of(17,00)));
        atms.add(new Atm("ATM3",LocalTime.of(22,00),LocalTime.of(13,00)));
        atms.add(new Atm("ATM4",LocalTime.of(17,00),LocalTime.of(01,00)));
        return atms;
    }

    /**
     * Returns a list with the credit cards
     * @return a list with the credit cards
     */
    public static List<CreditCard> getCreditCards(){
        List<CreditCard> creditCards=new ArrayList<>();
        creditCards.add(new CreditCard(CreditCardTypes.SILVER,0.2f,2000, LocalDate.of(2020,5,23),20000));
        creditCards.add(new CreditCard(CreditCardTypes.GOLD,0.1f,2000,LocalDate.of(2018,8,15),25000));
        creditCards.add(new CreditCard(CreditCardTypes.PLATINUM,0,2000,LocalDate.of(2019,3,20),3000));
        return creditCards;
    }


    public static void main(String[] args)
    {
        LocalDateTime currentDate=LocalDateTime.of(LocalDate.of(2019,3,19),LocalTime.of(11,30));
        Solution solution=new Solution(getAtms(),getCreditCards(),currentDate);

        System.out.print("ATMs route: ");
        solution.getAtmsRoute().forEach(a-> System.out.print(a.getName()+" "));

        long minutes=Math.abs(ChronoUnit.MINUTES.between(solution.getCurrentDate().toLocalTime(),LocalTime.of(11,30)));
        long hours=minutes/60;
        long minutesRemained=minutes%60;
        System.out.println("\nTime needed: "+hours+"h and "+minutesRemained+"m");
    }
}
