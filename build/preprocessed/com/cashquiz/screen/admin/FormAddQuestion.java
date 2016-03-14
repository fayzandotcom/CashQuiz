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
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class FormAddQuestion extends Form implements CommandListener {

    Display display;
    Midlet midlet;
    
    StringItem msg;
    StringItem msg2;
    TextField txtQuestion;
    TextField txtOptA;
    TextField txtOptB;
    TextField txtOptC;
    TextField txtOptD;
    ChoiceGroup cgAnswer;
    Command cmdBack;
    Command cmdAdd;
    Alert atStatus;
    
    String nextQuestionId;
    
    public FormAddQuestion(Midlet midlet) {
        super("Add New Question");
        this.initUI();
        
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            FormAdminPanel frmAdminPanel = new FormAdminPanel(midlet);
            display.setCurrent(frmAdminPanel);
        }
        
        if (c == cmdAdd) {
            if(txtQuestion.getString().equals("")) {
                atStatus.setString("Please fill the question field!");
                display.setCurrent(atStatus);
                return;
            }
            if(txtOptA.getString().equals("")) {
                atStatus.setString("Please fill the option A field!");
                display.setCurrent(atStatus);
                return;
            }
            if(txtOptB.getString().equals("")) {
                atStatus.setString("Please fill the option B field!");
                display.setCurrent(atStatus);
                return;
            }
            if(txtOptC.getString().equals("")) {
                atStatus.setString("Please fill the option C field!");
                display.setCurrent(atStatus);
                return;
            }
            if(txtOptD.getString().equals("")) {
                atStatus.setString("Please fill the option D field!");
                display.setCurrent(atStatus);
                return;
            }
            
            Question objQuestion = new Question();
            objQuestion.setQuestion(txtQuestion.getString());
            objQuestion.setOptionA(txtOptA.getString());
            objQuestion.setOptionB(txtOptB.getString());
            objQuestion.setOptionC(txtOptC.getString());
            objQuestion.setOptionD(txtOptD.getString());
            objQuestion.setAnswer(cgAnswer.getString(cgAnswer.getSelectedIndex()));
            AdminMethods actionMethod = new AdminMethods();
            if(actionMethod.addQuestion(objQuestion) > 0 ){
                atStatus.setString("Question added successfully!");
                txtQuestion.setString("");
                txtOptA.setString("");
                txtOptB.setString("");
                txtOptC.setString("");
                txtOptD.setString("");
                cgAnswer.setSelectedIndex(1, true);
            }else{
                atStatus.setString("Uable to add question.");
            }
            
            display.setCurrent(atStatus);
            loadContent();
        }
    }
    
    private void initUI() { 
        msg = new StringItem("", "");
        msg2 = new StringItem("", "");
        
        txtQuestion = new TextField("Question: ", "", 250, TextField.ANY);
        txtOptA = new TextField("Option A: ", "", 100, TextField.ANY);
        txtOptB = new TextField("Option B: ", "", 100, TextField.ANY);
        txtOptC = new TextField("Option C: ", "", 100, TextField.ANY);
        txtOptD = new TextField("Option D: ", "", 100, TextField.ANY);
        
        cgAnswer = new ChoiceGroup("Select the correct answer.", ChoiceGroup.EXCLUSIVE);
        cgAnswer.append("A", null);
        cgAnswer.append("B", null);
        cgAnswer.append("C", null);
        cgAnswer.append("D", null);
        
        cmdBack = new Command("Back", Command.BACK, 1);
        cmdAdd = new Command("Add", Command.OK, 1);
        
        atStatus = new Alert("Add Status");
        
        this.append(msg);
        this.append(msg2);
        this.append(txtQuestion);
        this.append(txtOptA);
        this.append(txtOptB);
        this.append(txtOptC);
        this.append(txtOptD);
        this.append(cgAnswer);
        this.addCommand(cmdBack);
        this.addCommand(cmdAdd);
        
        this.setCommandListener(this);
        
        this.loadContent();
    }
    
    private void loadContent() {
        AdminMethods actionMethod = new AdminMethods();
        
        int numQuestions = actionMethod.getNumberQuestions();
        msg.setText("Currently there are " + numQuestions + " question(s) stored out of 6");
        if (numQuestions == 6) {
            msg2.setText("You can not add more than 6 questions. Delete any one and then add another.");
            this.removeCommand(cmdAdd);
        } else {
            nextQuestionId = String.valueOf(numQuestions+1);
            msg2.setText("You can add the question no. " + nextQuestionId);
        }

    }

}
