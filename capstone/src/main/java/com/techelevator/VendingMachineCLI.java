package com.techelevator;

import com.techelevator.exceptions.AddMoneyException;
import com.techelevator.exceptions.InvalidSlotLocationException;
import com.techelevator.exceptions.NotEnoughMoneyException;
import com.techelevator.exceptions.SoldOutException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

    public VendingMachineCLI() {
    }

    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    public void run() {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        Display vendingDisplay = new Display();

        vendingDisplay.welcomeBanner();

        while (true) {
            vendingDisplay.displayMainMenu();
            String mainMenuUserInput = scanner.nextLine();

            if (mainMenuUserInput.equalsIgnoreCase("1")) {
                vendingDisplay.pullUpProductList();
                String anyKey = scanner.nextLine();
            } else if (mainMenuUserInput.equalsIgnoreCase("2")) {
                vendingDisplay.purchaseMenuOptions();

            } else if (mainMenuUserInput.equalsIgnoreCase("3")) {
               vendingDisplay.goodbyeMessage();
                break;
            } else if (mainMenuUserInput.equalsIgnoreCase("4")) {
                bank.generateSalesReport();
            }

        }

    }
}
