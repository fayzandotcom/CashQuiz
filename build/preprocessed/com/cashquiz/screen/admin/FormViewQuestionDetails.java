package com.cashquiz.screen.admin;

import com.cashquiz.methods.AdminMethods;
import com.cashquiz.midlet.Midlet;
import com.cashquiz.obj.Question;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class FormViewQuestionDetails extends Form implements CommandListener{
    
    Display display;
    Midlet midlet;
    
    StringItem strQuestion;
    StringItem optionA;
    StringItem optionB;
    StringItem optionC;
    StringItem optionD;
    StringItem answer;
    
    Command cmdBack;
    Command cmdDelete;
    Command cmdEdit;
    
    Alert atStatus;
    
    private Question objQuestion;

    public FormViewQuestionDetails(Midlet midlet, Question objQuestion) {
        super("View Question Details");
        
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
        
        this.objQuestion = objQuestion;
        
        this.initUI();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            FormViewQuestion formViewQuestion = new FormViewQuestion(midlet);
            display.setCurrent(formViewQuestion);
        }
        if (c == cmdDelete) {
            if (this.deleteQuestion(this.objQuestion)) {
                FormViewQuestion frmViewQuestion = new FormViewQuestion(midlet);
                display.setCurrent(frmViewQuestion);
            }
        }
        if (c == cmdEdit) {
            FormEditQuestion frmEditQuestion = new FormEditQuestion(midlet, objQuestion);
            display.setCurrent(frmEditQuestion);
        }
    }
    
    private void initUI() {
        strQuestion = new StringItem("Question: ", "");
        optionA = new StringItem("Option A: ", "");
        optionB = new StringItem("Option B: ", "");
        optionC = new StringItem("Option C:", "");
        optionD = new StringItem("Option D:", "");
        answer = new StringItem("Answer: ", "");
        
        cmdBack = new Command("Back", Command.BACK, 1);
        cmdEdit = new Command("Edit", Command.OK, 1);
        cmdDelete = new Command("Delete", Command.OK, 1);
        
        atStatus = new Alert("View Question");
        
        this.append(strQuestion);
        this.append(optionA);
        this.append(optionB);
        this.append(optionC);
        this.append(optionD);
        this.append(answer);
        this.addCommand(cmdBack);
        this.addCommand(cmdEdit);
        this.addCommand(cmdDelete);
        
        this.setCommandListener(this);
        
        this.loadContent();
    }
    
    private void loadContent() {
        strQuestion.setText(this.objQuestion.getQuestion());
        optionA.setText(this.objQuestion.getOptionA());
        optionB.setText(this.objQuestion.getOptionB());
        optionC.setText(this.objQuestion.getOptionC());
        optionD.setText(this.objQuestion.getOptionD());
        answer.setText(this.objQuestion.getAnswer());
    }
    
    private boolean deleteQuestion(Question question) {
        AdminMethods actionMethods = new AdminMethods();
        boolean retVal;
        if(actionMethods.deleteQuestion(question)) {
            atStatus.setString("Question deleted successfully!");
            retVal = true;
        } else {
            atStatus.setString("Unable to delete question");
            retVal = false;
        }
        display.setCurrent(atStatus);
        
        return retVal;
    }

}
