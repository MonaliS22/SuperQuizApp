package linkcode.superquizapp;

import java.util.Scanner;

public class MainQuiz {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuizManager quiz = new QuizManager();
        boolean flag = true;

        System.out.println("===== WELCOME TO SUPERCHARGED QUIZ APP =====");

        while(flag) {
            System.out.println("\n1. Play Quiz");
            System.out.println("2. Run Mock Tests");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1 -> {
                    sc.nextLine(); 
                    System.out.println("Enter your name:");
                    String name = sc.nextLine();
                    Player player = new Player(name);

                    System.out.println("Select Category (JAVA/SQL):");
                    String category = sc.nextLine();
                    long startTime = System.currentTimeMillis();
                    quiz.startQuiz(player, sc, category);
                    long endTime = System.currentTimeMillis();
                    long totalTime = (endTime - startTime)/1000;
                    ResultGenerator.generateReport(player, totalTime);
                }
                case 2 -> {
                    System.out.println("Enter number of mock attempts:");
                    int attempts = sc.nextInt();
                    quiz.runMockTests(attempts);
                }
                case 3 -> flag = false;
                default -> System.out.println("Invalid choice!");
            }
        }

        System.out.println("Thank you for using Supercharged Quiz App!");
        sc.close();
    }
}
