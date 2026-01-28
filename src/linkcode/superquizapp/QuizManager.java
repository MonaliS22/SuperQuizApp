package linkcode.superquizapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuizManager {
    private List<Question> questions;

    public QuizManager() {
        questions = new ArrayList<>();
        loadQuestions(); 
    }

    private void loadQuestions() {
        // ----- ----------------------------------JAVA QUESTIONS ---------------------------
        questions.add(new Question("JAVA","Easy","Which method starts execution of a Java program?", new String[]{"start()","main()","run()","execute()"},2));
        questions.add(new Question("JAVA","Easy","Which keyword is used to inherit a class?", new String[]{"this","super","extends","implements"},3));
        questions.add(new Question("JAVA","Medium","Which collection does not allow duplicates?", new String[]{"List","Set","Map","Array"},2));
        questions.add(new Question("JAVA","Medium","What is the default value of a boolean variable?", new String[]{"true","false","0","null"},2));
        questions.add(new Question("JAVA","Hard","Which concept supports runtime polymorphism?", new String[]{"Overloading","Encapsulation","Overriding","Abstraction"},3));

        // ----- SQL QUESTIONS -----
        questions.add(new Question("SQL","Easy","Which command retrieves data from a table?", new String[]{"SELECT","GET","FETCH","SHOW"},1));
        questions.add(new Question("SQL","Easy","Which command is used to remove a table?", new String[]{"DELETE","REMOVE","DROP","TRUNCATE"},3));
        questions.add(new Question("SQL","Medium","Which clause is used to filter records?", new String[]{"WHERE","HAVING","FILTER","ORDER BY"},1));
        questions.add(new Question("SQL","Medium","Which function returns total number of records?", new String[]{"SUM()","COUNT()","TOTAL()","NUMBER()"},2));
        questions.add(new Question("SQL","Hard","Which statement is used to prevent duplicate rows?", new String[]{"DISTINCT","UNIQUE","GROUP BY","LIMIT"},1));
    }

    
    public void startQuiz(Player player, Scanner sc, String category) {
        attemptCategory(player, sc, category);

        String nextCategory = category.equalsIgnoreCase("JAVA") ? "SQL" : "JAVA";
        System.out.println("\nDo you want to attempt " + nextCategory + " questions now? (yes/no)");
        String ans = sc.next();
        if(ans.equalsIgnoreCase("yes")) {
            attemptCategory(player, sc, nextCategory);
        }
    }

    private void attemptCategory(Player player, Scanner sc, String category) {
        System.out.println("\n--- " + category + " QUESTIONS ---");
        int streak = 0; 
        Random rand = new Random();

        List<Question> availableQuestions = new ArrayList<>();
        for(Question q : questions) {
            if(q.getCategory().equalsIgnoreCase(category)) {
                availableQuestions.add(q);
            }
        }

        for(int i = 0; i < 5; i++) { 
            if(availableQuestions.isEmpty()) break; 

            String difficulty = (streak >= 2) ? "Hard" : "Medium";

            List<Question> filtered = new ArrayList<>();
            for(Question q : availableQuestions) {
                if(q.getDifficulty().equals(difficulty)) filtered.add(q);
            }
            if(filtered.isEmpty()) filtered.addAll(availableQuestions); 

           
            Question q = filtered.get(rand.nextInt(filtered.size()));
            availableQuestions.remove(q); 

            q.displayQuestion();
            TimeTracker tt = new TimeTracker();
            tt.start();

            System.out.println("Choose option (1-4) or type 50/50 or skip:");
            String input = sc.next();

            tt.stop();

            
            try {
                if(tt.getElapsedMillis() < 2000) throw new CheatException("Cheating detected! Answered too fast!"); // <2 sec cheating
            } catch(CheatException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                if(input.equalsIgnoreCase("50/50")) {
                    player.use5050(); 
                    Lifeline.use5050(q); 
                    q.displayQuestion(); 
                    input = sc.next();
                } else if(input.equalsIgnoreCase("skip")) {
                    player.useSkip(); 
                    System.out.println("Question skipped!");
                    continue;
                }
            } catch(LifelineException e) {
                System.out.println(e.getMessage());
                System.out.println("Answer question:");
                input = sc.next();
            }

            int answer;
            try { answer = Integer.parseInt(input); } 
            catch(NumberFormatException e) {
                System.out.println("Invalid input! Skipping question.");
                streak = 0; 
                player.addWrong(q.getCategory());
                continue;
            }

           
            if(answer == q.getCorrectOption()) {
                int score = 10;
                if(tt.getElapsedSeconds() <= 5) score += 5; 
                player.addScore(q.getCategory(), score);
                streak++;
                System.out.println("Correct! +" + score + " points");
            } else {
                streak = 0; 
                player.addWrong(q.getCategory());
                System.out.println("Wrong! Correct answer: " + q.getCorrectOption());
            }
        }
    }

    // Mock Test Runner
    public void runMockTests(int attempts) {
        Random rand = new Random();
        List<String> categories = List.of("JAVA","SQL");
        double totalScore = 0;

        for(int i = 0; i < attempts; i++) {
            Player mock = new Player("Mock" + (i+1));
            String category = categories.get(rand.nextInt(categories.size()));

            for(Question q : questions) {
                if(!q.getCategory().equalsIgnoreCase(category)) continue;
                int ans = rand.nextInt(4)+1; // random answer 1-4
                if(ans == q.getCorrectOption()) mock.addScore(category, 10);
            }

            totalScore += mock.getTotalScore();
        }

        System.out.println("=== MOCK TEST REPORT ===");
        System.out.println("Attempts: " + attempts);
        System.out.println("Average Score: " + (totalScore / attempts));
    }
}
