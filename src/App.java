import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class App {

    private static ArrayList<String> Names = new ArrayList<>(
        List.of("Jane", "Julia", "John", "Alex")
    );
    private static ArrayList<HashSet<String>> Countries = new ArrayList<>(List.of(
        new HashSet<>(List.of("USA", "England", "Canada")),
        new HashSet<>(List.of("USA", "Mexico", "Canada")),
        new HashSet<>(List.of("England", "Japan", "Russia")),
        new HashSet<>(List.of("Russia", "Mexico", "France"))
    ));

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        boolean printMenu = true;
        for (boolean run = true; run;) {
            if (printMenu) {
                System.out.println("1. View visitors to a country");
                System.out.println("2. Send a person to a country");
                System.out.println("3. List countries a person has visited");
                System.out.println("4. Quit");
                System.out.print("\nEnter a number to select an option: ");
            }

            printMenu = true;
            switch (GetSelection(4, sc)) {
                case 1:
                    DisplayVisitors(sc);
                    break;
                case 2:
                    VisitCountry(sc);
                    break;
                case 3:
                    ListCountries(sc);
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Sorry, I didn't get that.\n");
                    printMenu = false;
                    break;
            }
        }
    }

    private static int GetSelection(int count, Scanner sc) {
        while(true) {
            try {
                int selection = sc.nextInt();
                if (selection > 0 && selection <= count) {
                    sc.nextLine();
                    return selection;
                }
            }
            catch(Exception ex) {
                sc.nextLine();
            }

            System.out.println("Please select a valid option\n");
        }
    }

    private static void DisplayVisitors(Scanner sc) {
        System.out.println("Please enter the country name: ");
        String country = sc.nextLine();
        System.out.println();

        boolean anyResults = false;
        for (int i = 0; i < Countries.size(); i++) {
            if (Countries.get(i).contains(country)) {
                System.out.println(Names.get(i));
                anyResults = true;
            }
        }

        if (!anyResults) {
            System.out.println("Nobody has visited that country");
        }

        System.out.println();
    }

    private static void VisitCountry(Scanner sc) {
        System.out.println("Please enter the person's name: ");
        String name = sc.nextLine();

        int who = Names.indexOf(name);
        if (who == -1) {
            Names.add(name);
            Countries.add(new HashSet<>());
            who = Names.size() - 1;
        }

        System.out.println("Please enter the country to visit: ");
        Countries.get(who).add(sc.nextLine());
        System.out.println();
    }

    private static void ListCountries(Scanner sc) {
        System.out.println("Please enter the person's name: ");

        int who = Names.indexOf(sc.nextLine());;
        while (who == -1) {
            System.out.println("That person does not exist, please re-enter.");
            who = Names.indexOf(sc.nextLine());
        }

        System.out.println();
        for (String country : Countries.get(who)) {
            System.out.println(country);
        }
        System.out.println();
    }
}
