package com.cashquiz.methods;

import com.cashquiz.obj.Question;
import java.util.Random;
import net.sourceforge.floggy.persistence.ObjectSet;
import net.sourceforge.floggy.persistence.PersistableManager;

public class PlayMethods {
    
    public Question[] getShuffledQuestions() {
        try {
            PersistableManager pm = PersistableManager.getInstance();
            ObjectSet questions = pm.find(Question.class, null, null);
            
            Question[] arrQuestion = new Question[questions.size()];
            for (int i=0; i<questions.size(); i++) {
                Question q = (Question) questions.get(i);
                arrQuestion[i] = q;
            }
            return this.shuffleQuestions(arrQuestion);
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
    private Question[] shuffleQuestions(Question[] arrQuestion) {
      Random rnd = new Random();
      for (int i = arrQuestion.length - 1; i > 0; i--) {
        int index = rnd.nextInt(i + 1);
        // Simple swap
        Question q = arrQuestion[index];
        arrQuestion[index] = arrQuestion[i];
        arrQuestion[i] = q;
      }
      return arrQuestion;
    }
    
}
