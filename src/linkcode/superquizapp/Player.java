package linkcode.superquizapp;

import java.util.HashMap;

public class Player {
    private String name;
    private int totalScore;
    private HashMap<String, Integer> categoryScores;
    private int lifeline5050Used;
    private int lifelineSkipUsed;

    
    private int totalQuestions; 
    private int correctAnswers; 
    private HashMap<String, Integer> correctPerCategory; 
    private HashMap<String, Integer> totalPerCategory; 
    public Player(String name) {
        this.name = name;
        this.totalScore = 0;
        this.categoryScores = new HashMap<>();
        this.lifeline5050Used = 0;
        this.lifelineSkipUsed = 0;
        this.totalQuestions = 0;
        this.correctAnswers = 0;
        this.correctPerCategory = new HashMap<>();
        this.totalPerCategory = new HashMap<>();
    }

    public String getName() { 
    	return name; 
    	}
    
    public int getTotalScore() {
    	return totalScore; 
    	}
    
    public HashMap<String, Integer> getCategoryScores() { 
    	return categoryScores; 
    	}

    public void addScore(String category, int score) {
        totalScore += score;
        categoryScores.put(category, categoryScores.getOrDefault(category, 0) + score);

        totalQuestions++;
        correctAnswers++;
        correctPerCategory.put(category, correctPerCategory.getOrDefault(category, 0) + 1);
        totalPerCategory.put(category, totalPerCategory.getOrDefault(category, 0) + 1);
    }

    public void addWrong(String category) {
        totalQuestions++;
        totalPerCategory.put(category, totalPerCategory.getOrDefault(category, 0) + 1);
    }

    public int getTotalQuestions() { 
    	return totalQuestions; 
    	}
    
    public int getCorrectAnswers() { 
    	return correctAnswers; 
    	}
    
    public HashMap<String,Integer> getCorrectPerCategory() { 
    	return correctPerCategory; 
    	}
    
    public HashMap<String,Integer> getTotalPerCategory() { 
    	return totalPerCategory; 
    	}

    public void use5050() throws LifelineException {
        if(lifeline5050Used >= 1) throw new LifelineException("50/50 lifeline already used!"); 
        lifeline5050Used++;
    }

    public void useSkip() throws LifelineException {
        if(lifelineSkipUsed >= 1) throw new LifelineException("Skip lifeline already used!");
        lifelineSkipUsed++;
    }

    public void resetLifelines() {
        lifeline5050Used = 0;
        lifelineSkipUsed = 0;
    }

    public void resetScore() {
        totalScore = 0;
        categoryScores.clear();
        totalQuestions = 0;
        correctAnswers = 0;
        correctPerCategory.clear();
        totalPerCategory.clear();
    }
}
