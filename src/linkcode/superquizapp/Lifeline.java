package linkcode.superquizapp;

import java.util.Random;

public class Lifeline {

    public static void use5050(Question q) {
        Random rand = new Random();
        int correct = q.getCorrectOption() - 1; 
        int[] remove = new int[2];
        int count = 0;

        while(count < 2) {
            int idx = rand.nextInt(4);
            if(idx != correct && (count == 0 || idx != remove[0])) {
                remove[count] = idx;
                count++;
            }
        }

        String[] newOptions = new String[4];
        for(int i=0;i<4;i++) newOptions[i] = q.getOptions()[i];
        for(int i : remove) newOptions[i] = "---"; 

        q.setOptions(newOptions);
        System.out.println("50/50 Lifeline used! Two wrong options removed.");
    }
}
