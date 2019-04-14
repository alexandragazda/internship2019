package Internship;

import java.time.LocalDate;

/**
 * CreditCard class
 */
public class CreditCard {
    private CreditCardTypes type;
    private float fee;
    private float withdrawLimit;
    private LocalDate expirationDate;
    private float availableAmount;

    /**
     * Constructor with parameters
     * @param type the type of the credit card
     * @param fee the fee of the credit card
     * @param withdrawLimit  the withdraw limit / ATM
     * @param expirationDate the expiration date of the credit card
     * @param availableAmount the amount of credit available
     */
    public CreditCard(CreditCardTypes type, float fee, float withdrawLimit, LocalDate expirationDate, float availableAmount) {
        this.type = type;
        this.fee = fee;
        this.withdrawLimit = withdrawLimit;
        this.expirationDate = expirationDate;
        this.availableAmount = availableAmount;
    }

    /**
     * Returns the type of the credit card
     * @return the type of the credit card
     */
    public CreditCardTypes getType() {
        return type;
    }

    /**
     * Sets the type of the credit card
     * @param type the type which is going to be set for the credit card
     */
    public void setType(CreditCardTypes type) {
        this.type = type;
    }

    /**
     * Returns the fee of the credit card
     * @return the fee of the credit card
     */
    public float getFee() {
        return fee;
    }

    /**
     * Sets the fee of the credit card
     * @param fee the fee which is going to be set for the credit card
     */
    public void setFee(float fee) {
        this.fee = fee;
    }

    /**
     * Returns the withdraw limit of the credit card
     * @return the withdraw limit of the credit card
     */
    public float getWithdrawLimit() {
        return withdrawLimit;
    }

    /**
     * Sets the withdraw limit of the credit card
     * @param withdrawLimit the withdraw limit which is going to be set for the credit card
     */
    public void setWithdrawLimit(float withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    /**
     * Returns the expiration date of the credit card
     * @return the expiration date of the credit card
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the credit card
     * @param expirationDate the expiration date which is going to be set for the credit card
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the amount of credit available
     * @return the amount of credit available
     */
    public float getAvailableAmount() {
        return availableAmount;
    }

    /**
     * Sets the amount of credit available
     * @param availableAmount the available amount which is going to be set for the credit card
     */
    public void setAvailableAmount(float availableAmount) {
        this.availableAmount = availableAmount;
    }

    /**
     * Verifies if the credit card is valid at the current date
     * @param currentDate the current date
     * @return true, if the credit card is valid
     *         false, otherwise
     */
    public boolean isValid(LocalDate currentDate){
        return this.getExpirationDate().isAfter(currentDate);
    }

    /**
     *Withdraws a sum of money from the credit card
     * @param sumOfMoney the sum of money that is going to be withdrawn
     */
    public void withdraw(float sumOfMoney){
        float currentAmount=this.availableAmount-sumOfMoney-this.fee/100*sumOfMoney;
        this.setAvailableAmount(currentAmount);
    }

    @Override
    public String toString() {
        return
                "type= " + type + " | " +
                "fee=" + fee + " | "+
                "withdrawLimit= " + withdrawLimit + " | "+
                "expirationDate= " + expirationDate + " | "+
                "availableAmount=" + availableAmount;
    }
}
