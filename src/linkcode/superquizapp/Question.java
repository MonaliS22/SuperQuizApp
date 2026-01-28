package linkcode.superquizapp;

public class Question {
    private String category;
    private String difficulty;
    private String question;
    private String[] options;
    private int correctOption;

    public Question(String category, String difficulty, String question, String[] options, int correctOption) {
        this.category = category;
        this.difficulty = difficulty;
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

  
    public String getCategory() { 
    	return category; 
    	}
    public String getDifficulty() { 
    	return difficulty; 
    	}
    public String getQuestion() { 
    	return question; 
    	}
    public String[] getOptions() { 
    	return options; 
    	}
    public int getCorrectOption() { 
    	return correctOption; 
    	}

    public void setOptions(String[] options) { 
    	this.options = options; 
    	}

    public void displayQuestion() {
        System.out.println("\nQuestion [" + category + " - " + difficulty + "]: " + question);
        for(int i = 0; i < options.length; i++) {
            System.out.println((i+1) + ". " + options[i]);
        }
    }
}
