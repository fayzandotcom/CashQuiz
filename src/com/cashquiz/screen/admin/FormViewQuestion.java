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
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;

public class FormViewQuestion extends Form implements CommandListener, ItemCommandListener{
    
    Display display;
    Midlet midlet;
    
    StringItem title;
    
    StringItem strQuestion1;
    Command cmdDeleteQuestion1;
    Command cmdEditQuestion1;
    Command cmdViewQuestionDetail1;
    
    StringItem strQuestion2;
    Command cmdDeleteQuestion2;
    Command cmdEditQuestion2;
    Command cmdViewQuestionDetail2;
    
    StringItem strQuestion3;
    Command cmdDeleteQuestion3;
    Command cmdEditQuestion3;
    Command cmdViewQuestionDetail3;
    
    StringItem strQuestion4;
    Command cmdDeleteQuestion4;
    Command cmdEditQuestion4;
    Command cmdViewQuestionDetail4;
    
    StringItem strQuestion5;
    Command cmdDeleteQuestion5;
    Command cmdEditQuestion5;
    Command cmdViewQuestionDetail5;
    
    StringItem strQuestion6;
    Command cmdDeleteQuestion6;
    Command cmdEditQuestion6;
    Command cmdViewQuestionDetail6;
    
    Command cmdBack;
    Alert atStatus;
    
    Question[] questions;
    int numQuestions;

    public FormViewQuestion(Midlet midlet) {
        super("View Questions");
        this.initUI();
        
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            FormAdminPanel frmAdminPanel = new FormAdminPanel(midlet);
            display.setCurrent(frmAdminPanel);
        }
    }
    
    public void commandAction(Command c, Item item) {
        
        // delete command
        if (c == cmdDeleteQuestion1) {
            if (this.deleteQuestion(questions[0])) {
                this.delete(this.getItemIndex(strQuestion1, this));
                strQuestion1.setLabel("");
                strQuestion1.setText("");
            }
        }
        if (c == cmdDeleteQuestion2) {
            if (this.deleteQuestion(questions[1])) {
                this.delete(this.getItemIndex(strQuestion2, this));
                strQuestion2.setLabel("");
                strQuestion2.setText("");
            }
        }
        if (c == cmdDeleteQuestion3) {
            if (this.deleteQuestion(questions[2])) {
                this.delete(this.getItemIndex(strQuestion3, this));
                strQuestion3.setLabel("");
                strQuestion3.setText("");
            }
        }
        if (c == cmdDeleteQuestion4) {
            if (this.deleteQuestion(questions[3])) {
                this.delete(this.getItemIndex(strQuestion4, this));
                strQuestion4.setLabel("");
                strQuestion4.setText("");
            }
        }
        if (c == cmdDeleteQuestion5) {
            if (this.deleteQuestion(questions[4])) {
                this.delete(this.getItemIndex(strQuestion5, this));
                strQuestion5.setLabel("");
                strQuestion5.setText("");
            }
        }
        if (c == cmdDeleteQuestion6) {
            if (this.deleteQuestion(questions[5])) {
                this.delete(this.getItemIndex(strQuestion6, this));
                strQuestion6.setLabel("");
                strQuestion6.setText("");
            }
        }
        
        // detail view command
        if (c == cmdViewQuestionDetail1) {
            FormViewQuestionDetails frmViewDetails = new FormViewQuestionDetails(midlet, this.questions[0]);
            display.setCurrent(frmViewDetails);
        }
        if (c == cmdViewQuestionDetail2) {
            FormViewQuestionDetails frmViewDetails = new FormViewQuestionDetails(midlet, this.questions[1]);
            display.setCurrent(frmViewDetails);
        }
        if (c == cmdViewQuestionDetail3) {
            FormViewQuestionDetails frmViewDetails = new FormViewQuestionDetails(midlet, this.questions[2]);
            display.setCurrent(frmViewDetails);
        }
        if (c == cmdViewQuestionDetail4) {
            FormViewQuestionDetails frmViewDetails = new FormViewQuestionDetails(midlet, this.questions[3]);
            display.setCurrent(frmViewDetails);
        }
        if (c == cmdViewQuestionDetail5) {
            FormViewQuestionDetails frmViewDetails = new FormViewQuestionDetails(midlet, this.questions[4]);
            display.setCurrent(frmViewDetails);
        }
        if (c == cmdViewQuestionDetail6) {
            FormViewQuestionDetails frmViewDetails = new FormViewQuestionDetails(midlet, this.questions[5]);
            display.setCurrent(frmViewDetails);
        }
        
        // edit command
        if (c == cmdEditQuestion1) {
            FormEditQuestion frmEditQuestion = new FormEditQuestion(midlet, this.questions[0]);
            display.setCurrent(frmEditQuestion);
        }
        if (c == cmdEditQuestion2) {
            FormEditQuestion frmEditQuestion = new FormEditQuestion(midlet, this.questions[1]);
            display.setCurrent(frmEditQuestion);
        }
        if (c == cmdEditQuestion3) {
            FormEditQuestion frmEditQuestion = new FormEditQuestion(midlet, this.questions[2]);
            display.setCurrent(frmEditQuestion);
        }
        if (c == cmdEditQuestion4) {
            FormEditQuestion frmEditQuestion = new FormEditQuestion(midlet, this.questions[3]);
            display.setCurrent(frmEditQuestion);
        }
        if (c == cmdEditQuestion5) {
            FormEditQuestion frmEditQuestion = new FormEditQuestion(midlet, this.questions[4]);
            display.setCurrent(frmEditQuestion);
        }
        if (c == cmdEditQuestion6) {
            FormEditQuestion frmEditQuestion = new FormEditQuestion(midlet, this.questions[5]);
            display.setCurrent(frmEditQuestion);
        }
        
    }
    
    private void initUI () {
        title = new StringItem("", "");
        
        cmdBack = new Command("Back", Command.BACK, 1);
        
        atStatus = new Alert("View Status");
        
        this.append(title);
        this.addCommand(cmdBack);
        
        this.setCommandListener(this);
        
        loadContent();
        
        renderQuestions();
    }
    
    private void loadContent() {
        
    }
    
    private void renderQuestions() {
        AdminMethods actionMethods = new AdminMethods();
        
        this.questions = new Question[actionMethods.getNumberQuestions()];  // clear previous state
        this.questions = actionMethods.getAllQuestions();
        numQuestions = questions.length;
        
        title.setText("There are " + numQuestions + " question(s) stored out of 6. ");
        
        //question 1
        if (numQuestions >= 1) {
            strQuestion1 = new StringItem(questions[0].getQuestion(), "");
            cmdDeleteQuestion1 = new Command("Delete", Command.ITEM, 1);
            cmdEditQuestion1 = new Command("Edit", Command.ITEM, 1);
            cmdViewQuestionDetail1 = new Command("View", Command.ITEM, 1);
            strQuestion1.setDefaultCommand(cmdDeleteQuestion1);
            strQuestion1.setDefaultCommand(cmdEditQuestion1);
            strQuestion1.setDefaultCommand(cmdViewQuestionDetail1);
            strQuestion1.setItemCommandListener(this);
            this.append(strQuestion1);
        }
        
        //question 2
        if (numQuestions >= 2) {
            strQuestion2 = new StringItem(questions[1].getQuestion(), "");
            cmdDeleteQuestion2 = new Command("Delete", Command.ITEM, 1);
            cmdEditQuestion2 = new Command("Edit", Command.ITEM, 1);
            cmdViewQuestionDetail2 = new Command("View", Command.ITEM, 1);
            strQuestion2.setDefaultCommand(cmdDeleteQuestion2);
            strQuestion2.setDefaultCommand(cmdEditQuestion2);
            strQuestion2.setDefaultCommand(cmdViewQuestionDetail2);
            strQuestion2.setItemCommandListener(this);
            this.append(strQuestion2);
        }
        
        //question 3
        if (numQuestions >= 3) {
            strQuestion3 = new StringItem(questions[2].getQuestion(), "");
            cmdDeleteQuestion3 = new Command("Delete", Command.ITEM, 1);
            cmdEditQuestion3 = new Command("Edit", Command.ITEM, 1);
            cmdViewQuestionDetail3 = new Command("View", Command.ITEM, 1);
            strQuestion3.setDefaultCommand(cmdDeleteQuestion3);
            strQuestion3.setDefaultCommand(cmdEditQuestion3);
            strQuestion3.setDefaultCommand(cmdViewQuestionDetail3);
            strQuestion3.setItemCommandListener(this);
            this.append(strQuestion3);
        }
        
        //question 4
        if (numQuestions >= 4) {
            strQuestion4 = new StringItem(questions[3].getQuestion(), "");
            cmdDeleteQuestion4 = new Command("Delete", Command.ITEM, 1);
            cmdEditQuestion4 = new Command("Edit", Command.ITEM, 1);
            cmdViewQuestionDetail4 = new Command("View", Command.ITEM, 1);
            strQuestion4.setDefaultCommand(cmdDeleteQuestion4);
            strQuestion4.setDefaultCommand(cmdEditQuestion4);
            strQuestion4.setDefaultCommand(cmdViewQuestionDetail4);
            strQuestion4.setItemCommandListener(this);
            this.append(strQuestion4);
        }
        
        //question 5
        if (numQuestions >= 5) {
            strQuestion5 = new StringItem(questions[4].getQuestion(), "");
            cmdDeleteQuestion5 = new Command("Delete", Command.ITEM, 1);
            cmdEditQuestion5 = new Command("Edit", Command.ITEM, 1);
            cmdViewQuestionDetail5 = new Command("View", Command.ITEM, 1);
            strQuestion5.setDefaultCommand(cmdDeleteQuestion5);
            strQuestion5.setDefaultCommand(cmdEditQuestion5);
            strQuestion5.setDefaultCommand(cmdViewQuestionDetail5);
            strQuestion5.setItemCommandListener(this);
            this.append(strQuestion5);
        }
        
        //question 6
        if (numQuestions >= 6) {
            strQuestion6 = new StringItem(questions[5].getQuestion(), "");
            cmdDeleteQuestion6 = new Command("Delete", Command.ITEM, 1);
            cmdEditQuestion6 = new Command("Edit", Command.ITEM, 1);
            cmdViewQuestionDetail6 = new Command("View", Command.ITEM, 1);
            strQuestion6.setDefaultCommand(cmdDeleteQuestion6);
            strQuestion6.setDefaultCommand(cmdEditQuestion6);
            strQuestion6.setDefaultCommand(cmdViewQuestionDetail6);
            strQuestion6.setItemCommandListener(this);
            this.append(strQuestion6);
        }
    }
    
    private boolean deleteQuestion(Question question) {
        AdminMethods actionMethods = new AdminMethods();
        boolean retVal;
        if(actionMethods.deleteQuestion(question)) {
            atStatus.setString("Question deleted successfully!");
            title.setText("There are " + (numQuestions-1) + " question(s) stored out of 6. ");
            retVal = true;
        } else {
            atStatus.setString("Unable to delete question");
            retVal = false;
        }
        display.setCurrent(atStatus);
        
        return retVal;
    }
    
    private int getItemIndex(Item item, Form form) {
        for(int i = 0, size = form.size(); i < size; i++) {
            if(form.get(i).equals(item)) {
                    return i;
            }
        }
        return -1;
    }

}
