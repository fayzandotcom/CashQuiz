package com.cashquiz.screen.admin;

import com.cashquiz.methods.AdminMethods;
import com.cashquiz.midlet.Midlet;
import com.cashquiz.obj.Question;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

public class FormEditQuestion extends Form implements CommandListener{
    
    Display display;
    Midlet midlet;
    
    Question objQuestion;
    
    TextField strQuestion;
    TextField optionA;
    TextField optionB;
    TextField optionC;
    TextField optionD;
    ChoiceGroup answer;
    
    Command cmdBack;
    Command cmdEdit;
    
    Alert atStatus;

    public FormEditQuestion(Midlet midlet, Question objQuestion) {
        super("Edit Question");
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
        
        this.objQuestion = objQuestion;
        
        this.iniUI();
    }

    public void commandAction(Command c, Displayable d) {
        
        if (c == cmdBack) {
            FormViewQuestion frmViewQuestion = new FormViewQuestion(midlet);
            display.setCurrent(frmViewQuestion);
        }
        if (c == cmdEdit) {
            Question newQuestion = new Question();
            newQuestion.setQuestion(strQuestion.getString());
            newQuestion.setOptionA(optionA.getString());
            newQuestion.setOptionB(optionB.getString());
            newQuestion.setOptionC(optionC.getString());
            newQuestion.setOptionD(optionD.getString());
            newQuestion.setAnswer(answer.getString(answer.getSelectedIndex()));
            
            this.editQuestion(this.objQuestion, newQuestion);
        }
        
    }
    
    private void iniUI() {
        strQuestion = new TextField("Question: ", "", 250, TextField.ANY);
        optionA = new TextField("Option A: ", "", 100, TextField.ANY);
        optionB = new TextField("Option B: ", "", 100, TextField.ANY);
        optionC = new TextField("Option C: ", "", 100, TextField.ANY);
        optionD = new TextField("Option D: ", "", 100, TextField.ANY);
        answer = new ChoiceGroup("Select the correct answer", ChoiceGroup.EXCLUSIVE);
        answer.append("A", null);
        answer.append("B", null);
        answer.append("C", null);
        answer.append("D", null);
        
        cmdBack = new Command("Back", Command.BACK, 1);
        cmdEdit = new Command("Edit", Command.OK, 1);
        
        atStatus = new Alert("Edit Question");
        
        this.append(strQuestion);
        this.append(optionA);
        this.append(optionB);
        this.append(optionC);
        this.append(optionD);
        this.append(answer);
        this.addCommand(cmdBack);
        this.addCommand(cmdEdit);
        
        this.setCommandListener(this);
        
        this.loadContent();
    }
    
    private void loadContent() {
        strQuestion.setString(this.objQuestion.getQuestion());
        optionA.setString(this.objQuestion.getOptionA());
        optionB.setString(this.objQuestion.getOptionB());
        optionC.setString(this.objQuestion.getOptionC());
        optionD.setString(this.objQuestion.getOptionD());
        answer.setLabel("Selected correct answer is " + this.objQuestion.getAnswer() + ". To change, choose from following.");
    }
    
    private boolean editQuestion(Question curQuestion, Question newQuestion) {
        AdminMethods actionMethods = new AdminMethods();
        boolean retVal;
        
        // delete first
        if(actionMethods.deleteQuestion(curQuestion)) {
            if(actionMethods.addQuestion(newQuestion) > 0) {
                atStatus.setString("Question edit successfully!");
                retVal = true;
            } else {
                atStatus.setString("Unable to edit question");
                retVal = false;
            }
        } else {
            atStatus.setString("Unable to edit question");
            retVal = false;
        }
        display.setCurrent(atStatus);
        
        return retVal;
    }
    
}
