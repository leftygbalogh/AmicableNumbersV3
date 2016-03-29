/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmbanknotedenominations;

import java.util.Scanner;

/**
 *
 * @author lefty
 */
public class ATMBanknoteDenominations {

    /**
     * @param args the command line arguments
     */
    public static int GetRemainder(int sum, int smallest_denomination) {
        return sum % smallest_denomination;
    }

    public static int GetSum(Scanner keyboard) {

        int sum;
        do {
            System.out.print("How much money do you want to withdraw? ");
            sum = keyboard.nextInt();
        } while (sum < 0 || sum > 250000);
        return sum;
    }

    public static int[][] GetDenominations(int dispensible_sum, int[][] denominations, int index) {
        int D20K = 0;
        int D10K = 1;
        int D5K = 2;
        int D2K = 3;
        int D1K = 4;
        int D500 = 5;
        int banknote = 0;
        int number_of_notes = 1;

        while (dispensible_sum > 0) {
            denominations[index][number_of_notes] = dispensible_sum / denominations[index][banknote];
            dispensible_sum = dispensible_sum % denominations[index][banknote];

            index++;
            denominations = GetDenominations(dispensible_sum, denominations, index);
        }

        return denominations;
    }

    public static int[][] SetDenominations() {
        int D20K = 0;
        int D10K = 1;
        int D5K = 2;
        int D2K = 3;
        int D1K = 4;
        int D500 = 5;
        int banknote = 0;
        int number_of_notes = 1;
        int[][] denominations = new int[6][2];

        denominations[D20K][banknote] = 20000;
        denominations[D20K][number_of_notes] = 0;

        denominations[D10K][banknote] = 10000;
        denominations[D10K][number_of_notes] = 0;

        denominations[D5K][banknote] = 5000;
        denominations[D5K][number_of_notes] = 0;

        denominations[D2K][banknote] = 2000;
        denominations[D2K][number_of_notes] = 0;

        denominations[D1K][banknote] = 1000;
        denominations[D1K][number_of_notes] = 0;

        denominations[D500][banknote] = 500;
        denominations[D500][number_of_notes] = 0;

        return denominations;
    }

    public static void PrintDenominations(int[][] denominations, int index) {
        while (index < denominations.length) {
            if (denominations[index][1] != 0) {
                System.out.print("\nYou will receive " + denominations[index][1] + " : " + denominations[index][0] + " Forint banknote(s).");
            }
            index++;

        };
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int sum = GetSum(keyboard);

        int[][] denominations = SetDenominations();
        int smallest_denomination = denominations[denominations.length - 1][0];

        int non_dispensible_remainder = GetRemainder(sum, smallest_denomination);
        int dispensible_sum = sum - non_dispensible_remainder;

        denominations = GetDenominations(dispensible_sum, denominations, 0);

        PrintDenominations(denominations, 0);
        System.out.print("\nThe remaining " + non_dispensible_remainder + " Forints cannot be dispensed in small change.\nIt has not been deducted from your account.");
    }

}
