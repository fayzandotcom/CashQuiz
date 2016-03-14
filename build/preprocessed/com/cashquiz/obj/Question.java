package com.cashquiz.obj;

import net.sourceforge.floggy.persistence.Persistable;

public class Question implements Persistable{
    
    //private String id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    
    /**
    public void setId(String value) {
        this.id = value;
    }
    
    public String getId() {
        return this.id;
    }**/
    
    public void setQuestion(String value) {
        this.question = value;
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public void setOptionA(String value) {
        this.optionA = value;
    }
    
    public String getOptionA() {
        return this.optionA;
    }
    
    public void setOptionB(String value) {
        this.optionB = value;
    }
    
    public String getOptionB() {
        return this.optionB;
    }
    
    public void setOptionC(String value) {
        this.optionC = value;
    }
    
    public String getOptionC() {
        return this.optionC;
    }
    
    public void setOptionD(String value) {
        this.optionD = value;
    }
    
    public String getOptionD() {
        return this.optionD;
    }
    
    public void setAnswer(String value) {
        this.answer = value;
    }
    
    public String getAnswer() {
        return this.answer;
    }
    
}
