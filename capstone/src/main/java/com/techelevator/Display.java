package com.techelevator;

import com.techelevator.exceptions.AddMoneyException;
import com.techelevator.exceptions.InvalidSlotLocationException;
import com.techelevator.exceptions.NotEnoughMoneyException;
import com.techelevator.exceptions.SoldOutException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class Display {
    private Bank bank = new Bank();
    Scanner scanner = new Scanner(System.in);

    public Display() {

    }

    public String welcomeBanner() {
        System.out.println("Umbrella Corp presents 'It's Raining Chips!' Vending Machine");
        return null;
    }

    public String displayMainMenu() {
        System.out.println("Main Menu\n" + "(1) Display Vending Machine Items\n" + "(2) Purchase\n" + "(3) Exit");
        return null;
    }

    public String pullUpProductList() {
        for (Map.Entry<String, Product> entrySet : bank.getPurchaseInventory().getInventoryMap().entrySet()) {
            System.out.println(entrySet.getValue());
        }
        System.out.println("Press enter to return to Main Menu: ");
        return null;
    }

    public String displayPurchaseMenu () {
        System.out.println("Purchase Menu\n" + "Current Money Provided: $" + bank.getCurrentMoneyProvided() + "\n" + "\n" + "(1) Feed Money\n" + "(2) Select Product\n" + "(3) Finish Transaction");
        return null;
    }

    public String purchaseMenuOptions() {
        while (true) {
            displayPurchaseMenu();
            String purchaseMenuUserInput = scanner.nextLine();
            if (purchaseMenuUserInput.equalsIgnoreCase("1")) {
                System.out.print("How much money would you like to add? $");
                String moneyToAddString = scanner.nextLine();
                try {
                    bank.addMoney(new BigDecimal(moneyToAddString));
                } catch (AddMoneyException nMe) {
                    System.out.println("This machine doesn't stock pennies. You need to add a positive amount of money that is divisible by at least a nickel.");
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a monetary value");
                }
            } else if (purchaseMenuUserInput.equalsIgnoreCase("2")) {
                pullUpProductList();
                System.out.println("Please enter the slot location of the item you'd like to purchase");
                String userPurchaseChoice = scanner.nextLine();
                try {
                    bank.purchaseAnItem(userPurchaseChoice.toUpperCase());
                    System.out.printf("You bought %s! %s \n \n",
                            bank.getPurchaseInventory().getInventoryMap().get(userPurchaseChoice.toUpperCase()).getProductName(),
                            bank.getPurchaseInventory().getInventoryMap().get(userPurchaseChoice.toUpperCase()).getCatchPhrase());
                } catch (InvalidSlotLocationException isle) {
                    System.out.println("That slot location does not exist. Please enter a valid slot location.");
                } catch (SoldOutException soe) {
                    System.out.println("That item is sold out, please try again.");
                } catch (NotEnoughMoneyException neme) {
                    System.out.println("You don't have enough money for that item. Please feed me more money!");
                }
            } else if (purchaseMenuUserInput.equalsIgnoreCase("3")) {
                bank.giveChange();
                System.out.printf("You received %d quarters, %d dimes, and %d nickels, totaling $%s in change.\n \n",
                        bank.getNumberOfQuarters(), bank.getNumberOfDimes(), bank.getNumberOfNickels(), bank.getChangeProvided().toString());
                bank.resetAllChange();
                bank.resetChangeProvided();
                break;
            }
        }
        return null;
    }

    public String goodbyeMessage() {
        System.out.println("Thank you for using the vending machine!\nRemember: Umbrella Corp knows your social security number :)");
        return null;
    }

}
