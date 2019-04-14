package Internship;

import java.time.LocalTime;
import java.util.Objects;

/**
 * Atm class
 */
public class Atm {
    private final float amountOfMoney=5000;
    private String name;
    private LocalTime openingTime;
    private LocalTime closingTime;

    /**
     * Constructor with parameters
     * @param name - the name of the ATM
     * @param openingTime - the opening time of the ATM
     * @param closingTime - the closing time of the ATM
     */
    public Atm(String name, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atm)) return false;
        Atm atm = (Atm) o;
        return Float.compare(atm.getAmountOfMoney(), getAmountOfMoney()) == 0 &&
                Objects.equals(getName(), atm.getName()) &&
                Objects.equals(getOpeningTime(), atm.getOpeningTime()) &&
                Objects.equals(getClosingTime(), atm.getClosingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAmountOfMoney(), getOpeningTime(), getClosingTime());
    }

    /**
     * Returns the name of the ATM
     * @return the name of the ATM
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ATM
     * @param name the name which is going to be set for the ATM
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the amount of money of the ATM
     * @return the amount of money of the ATM
     */
    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    /**
     * Returns the opening time of the ATM
     * @return the opening time of the ATM
     */
    public LocalTime getOpeningTime() {
        return openingTime;
    }

    /**
     * Sets the opening time of the ATM
     * @param openingTime the opening time which is going to be set for the ATM
     */
    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    /**
     * Returns the closing time of the ATM
     * @return the closing time of the ATM
     */
    public LocalTime getClosingTime() {
        return closingTime;
    }

    /**
     * Sets the closing time of the ATM
     * @param closingTime the closing time which is going to be set for the ATM
     */
    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }


    /**
     * Verifies if the ATM is open at the current time
     * @param currentTime the current time
     * @return true, if the ATM is open
     *         false, otherwise
     */
    public boolean isOpen(LocalTime currentTime){
        if(this.openingTime.isBefore(this.closingTime))
            return this.openingTime.isBefore(currentTime) && this.closingTime.isAfter(currentTime);
        return this.openingTime.isAfter(currentTime) && this.closingTime.isAfter(currentTime);
    }

    @Override
    public String toString() {
        return
                "name= " + name + " | " +
                "amountOfMoney= " + amountOfMoney + " | "+
                "openingTime= " + openingTime + " | "+
                "closingTime= " + closingTime;
    }
}
