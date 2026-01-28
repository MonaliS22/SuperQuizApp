package linkcode.superquizapp;

import java.util.HashMap;

public class ResultGenerator {

    public static void generateReport(Player player, long totalTime) {
        System.out.println("\n======= QUIZ SUMMARY =======");
        System.out.println("Player: " + player.getName());
        System.out.println("Total Questions: " + player.getTotalQuestions());
        System.out.println("Correct Answers: " + player.getCorrectAnswers());
        System.out.println("Time Taken: " + totalTime + " seconds");

        HashMap<String,Integer> correctPerCat = player.getCorrectPerCategory();
        HashMap<String,Integer> totalPerCat = player.getTotalPerCategory();

        String strongest = "";
        String weakest = "";
        double maxPercent = -1;
        double minPercent = 101;

        for(String cat : totalPerCat.keySet()) {
            int correct = correctPerCat.getOrDefault(cat,0);
            int total = totalPerCat.getOrDefault(cat,0);
            double percent = (total==0)?0:(correct*100.0/total);
            System.out.println(cat + " (" + correct + "/" + total + ") - " + String.format("%.0f",percent) + "%");

            if(percent > maxPercent) { 
            	maxPercent = percent; 
            	strongest = cat; 
            	}
            if(percent < minPercent) { 
            	minPercent = percent; 
            	weakest = cat; 
            	}
        }

        System.out.println("Strongest Category: " + strongest + " (" + String.format("%.0f",maxPercent) + "%)");
        System.out.println("Weakest Category: " + weakest + " (" + String.format("%.0f",minPercent) + "%)");
        System.out.println("============================");
    }
}
