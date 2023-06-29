import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Register people
        Person jacob = new Person("Jacob", "", "Lee", 13, 20091214,
                false, 400f, 0f);
        Person amogus = new Person("Amogus", "Sussy", "Fuiyoh", 99, 80085101,
                false, 9999f, 0f);

        // Register verifier
        Verifier user = new Verifier(false);

        // Add people to list
        List<Person> personList = new ArrayList<>();
        personList.add(jacob);
        personList.add(amogus);

        int selectedUser = 0;
        int ended = 0;

        // Start procedure

        while (ended == 0) {
            System.out.println("Hello, this is Aquarell bank. How can I help you today? \n" +
                    "You can either [deposit], [withdraw], [verify], [create] an account, [delete] your account, or [end] the system. \n" +
                    "Please enter your command below.");

            // Detect user input
            Scanner startscanner = new Scanner(System.in);
            String startinput = startscanner.nextLine();

            // Input result direction
            switch (startinput.toLowerCase()) {
                case ("deposit") -> {

                    // If user is verified
                    if (user.isVerified) {
                        userAlreadyVerified();

                        while (true) {
                            // If user has an account
                            if (personList.get(selectedUser).hasAccount) {
                                System.out.println("How much do you want to deposit in your account?");

                                // Deposit scanner
                                Scanner depositscanner = new Scanner(System.in);
                                float deposit = depositscanner.nextFloat();

                                // Check if deposit matches conditions
                                if (deposit <= personList.get(selectedUser).moneyPurse && deposit >= 0) {
                                    personList.get(selectedUser).moneyPurse -= deposit;
                                    personList.get(selectedUser).moneyBank += deposit;
                                    System.out.println("You have successfully deposited your money into your account! \n" +
                                            "Your purse currently has $" + personList.get(selectedUser).moneyPurse + ", and your account has $" +
                                            personList.get(selectedUser).moneyBank + "." + " System will restart soon. \n");
                                    break;
                                } else if (deposit < 0) {
                                    System.out.println("That is a negative number! If you want to withdraw, please restart the system and select [withdraw]. \n");
                                } else if (deposit > personList.get(selectedUser).moneyPurse) {
                                    System.out.println("You do not have enough money to deposit that amount. Please select a different amount. \n");
                                }

                                // If user doesn't have an account
                            } else {
                                System.out.println("You do not have an account. Please create one. System will restart soon. \n");
                                break;
                            }
                        }

                    // If user is not verified
                    } else {
                        userIsNotVerified();
                    }
                }
                case ("withdraw") -> {

                    // If user is verified
                    if (user.isVerified) {
                        userAlreadyVerified();

                        while (true) {
                            // If user has an account
                            if (personList.get(selectedUser).hasAccount) {
                                System.out.println("How much do you want to withdraw from your account?");

                                // Withdraw scanner
                                Scanner withdrawscanner = new Scanner(System.in);
                                float withdraw = withdrawscanner.nextFloat();

                                // Check if withdraw matches conditions
                                if (withdraw <= personList.get(selectedUser).moneyBank && withdraw >= 0) {
                                    personList.get(selectedUser).moneyBank -= withdraw;
                                    personList.get(selectedUser).moneyPurse += withdraw;
                                    System.out.println("You have successfully withdrew your money from your account! \n" +
                                            "Your purse currently has $" + personList.get(selectedUser).moneyPurse + ", and your account has $" +
                                            personList.get(selectedUser).moneyBank + "." + " System will restart soon. \n");
                                    break;
                                } else if (withdraw < 0) {
                                    System.out.println("That is a negative number! If you want to deposit, please restart the system and select [deposit]. \n");
                                } else if (withdraw > personList.get(selectedUser).moneyBank) {
                                    System.out.println("You do not have enough money in your account to withdraw that amount. \n" +
                                            "Sadly, we do not take any loans. Please choose a different amount to withdraw. \n");
                                }

                                // If user doesn't have an account
                            } else {
                                System.out.println("You do not have an account. Please create one. System will restart soon. \n");
                                break;
                            }
                        }

                        // If user is not verified
                    } else {
                        userIsNotVerified();
                    }
                }
                case ("verify") -> {

                    // If user is verified
                    if (user.isVerified) {
                        System.out.println("You are already a verified user. System will restart soon. \n");

                    // If user is not verified
                    } else {
                        System.out.println("There will be a verification process. List your first. middle, last name, age, \n" +
                                "and your social security number in order and in all separate lines.");

                        // Information scanners
                        Scanner verifyfirstnamescanner = new Scanner(System.in);
                        String verifyfirstnameinput = verifyfirstnamescanner.nextLine();

                        Scanner verifymiddlenamescanner = new Scanner(System.in);
                        String verifymiddlenameinput = verifymiddlenamescanner.nextLine();

                        Scanner verifylastnamescanner = new Scanner(System.in);
                        String verifylastnameinput = verifylastnamescanner.nextLine();

                        Scanner verifyagescanner = new Scanner(System.in);
                        int verifyageinput = verifyagescanner.nextInt();

                        Scanner verifysecuritynumberscanner = new Scanner(System.in);
                        int verifysecuritynumberinput = verifysecuritynumberscanner.nextInt();

                        // Check if information matches
                        for (int x = 0; x < personList.size(); x++) {
                            if (verifyfirstnameinput.equalsIgnoreCase(personList.get(x).firstName) &&
                                    verifymiddlenameinput.equalsIgnoreCase(personList.get(x).middleName) &&
                                    verifylastnameinput.equalsIgnoreCase(personList.get(x).lastName) &&
                                    verifyageinput == personList.get(x).age &&
                                    verifysecuritynumberinput == personList.get(x).securityNumber) {
                                selectedUser += x;
                                System.out.println("Verification success. You have been identified as " + personList.get(selectedUser).firstName + ". " +
                                        "System will restart soon. \n");
                                user.isVerified = true;
                                break;
                            } else if (x == 1) {
                                System.out.println("Your information is incorrect. System will restart soon. \n");
                            }
                        }
                    }
                }
                case ("create") -> {

                    // If user is not verified
                    if (user.isVerified) {
                        userAlreadyVerified();

                        // If user doesn't have an account
                        if (!personList.get(selectedUser).hasAccount) {
                            System.out.println("In order to create an account, you need an initial deposit. \n" +
                                    "Choose the amount you will deposit. The minimum initial deposit is $100.");

                            // If initial deposit is bigger than 100
                            while (true) {

                                Scanner intialdepositscanner = new Scanner(System.in);
                                int initialdepositinput = intialdepositscanner.nextInt();

                                if (initialdepositinput >= 100 && initialdepositinput <= personList.get(selectedUser).moneyPurse) {
                                    System.out.println("Congratulations! You have created an account. System will restart soon. \n");
                                    personList.get(selectedUser).moneyPurse -= initialdepositinput;
                                    personList.get(selectedUser).moneyBank += initialdepositinput;
                                    personList.get(selectedUser).hasAccount = true;
                                    break;
                                } else if (initialdepositinput > personList.get(selectedUser).moneyPurse) {
                                    System.out.println("You do not have that much money. \n" +
                                            "Please enter a different amount for your deposit.");
                                } else if (initialdepositinput < 100) {
                                    System.out.println("That is not enough money to create an account. \n" +
                                            "Please enter a different amount for your deposit.");
                                }
                            }

                        // If user has an account
                        } else {
                            System.out.println("You already have an account. System will restart soon. \n");
                        }

                    // If user is not verified
                    } else {
                        userIsNotVerified();
                    }
                }
                case ("delete") -> {

                    // If user is verified
                    if (user.isVerified) {
                        userAlreadyVerified();

                        // If user has an account
                        if (personList.get(selectedUser).hasAccount) {
                            System.out.println("Are you sure you want to delete your account? \n" +
                                    "All of your money in the account will be voided. ([yes] or [no], enter your command below.)");

                            // Delete confirmation scanner
                            Scanner deleteconfirmscanner = new Scanner(System.in);
                            String deleteconfirmation = deleteconfirmscanner.nextLine();

                            // Delete confirmation
                            if (deleteconfirmation.equalsIgnoreCase("yes")) {
                                System.out.println("Your account has been successfully deleted. \n");
                                personList.get(selectedUser).moneyBank = 0;
                                personList.get(selectedUser).hasAccount = false;
                            } else {
                                System.out.println("You have chose not to delete your account. System will restart soon. \n");
                            }

                        // If user doesn't have an account
                        } else {
                            System.out.println("You don't have an account to delete. System will restart soon. \n");
                        }

                    // If user is not verified
                    } else {
                        userIsNotVerified();
                    }
                }
                case ("end") -> {
                    System.out.println("System will now self-terminate. Thank you for using Aquarell bank.");
                    ended = 1;
                }
            }
        }

    }

    // User already verified
    public static void userAlreadyVerified() {
        System.out.println("You are a verified user. Procedure will continue. \n");
    }

    public static void userIsNotVerified() {
        System.out.println("You have not verified your identity. System will restart soon. \n");
    }
}