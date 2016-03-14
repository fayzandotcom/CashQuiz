package com.cashquiz.methods;

import com.cashquiz.obj.Question;
import net.sourceforge.floggy.persistence.ObjectSet;
import net.sourceforge.floggy.persistence.PersistableManager;

public class AdminMethods {
    
    public int addQuestion(Question objQuestion)  { 
        try {
            PersistableManager pm = PersistableManager.getInstance();
            int id = pm.save(objQuestion); 
            return id;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return 0;
        }
    }
    
    public Question[] getAllQuestions() {
        try {
            PersistableManager pm = PersistableManager.getInstance();
            ObjectSet questions = pm.find(Question.class, null, null);
            
            Question[] retArray = new Question[questions.size()];
            for (int i=0; i<questions.size(); i++) {
                Question q = (Question) questions.get(i);
                retArray[i] = q;
            }
            return retArray;
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
    public int getNumberQuestions() {
        try {
            PersistableManager pm = PersistableManager.getInstance();
            ObjectSet questions = pm.find(Question.class, null, null);
            return questions.size();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return 0;
        }
    }
    
    public boolean deleteQuestion(Question question) {
        try {
            PersistableManager pm = PersistableManager.getInstance();
            pm.delete(question);
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
}
