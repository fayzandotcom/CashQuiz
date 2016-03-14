package com.cashquiz.screen.play;

import com.cashquiz.methods.PlayMethods;
import com.cashquiz.midlet.Midlet;
import com.cashquiz.obj.Question;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class FormPlayQuestion extends Form implements CommandListener {

    Display display;
    Midlet midlet;
    
    StringItem strQuestion;
    StringItem strPollResult;
    ChoiceGroup cgAnswer;
    
    Command cmdQuit;
    Command cmdSelect;
    Command cmdNext;
    Command cmdFinish;
    Command cmdErrorOk;
    Command cmd5050;
    Command cmdAudiencePoll;
    
    Alert atStatus;
    
    Question[] questions;
    int currQuestionNo;
    
    int winAmount = 16; // initial prize
    
    int idxOptA;
    int idxOptB;
    int idxOptC;
    int idxOptD;
    
    int fifty50 = 0;
    int audiencePoll = 0;
    
    public FormPlayQuestion(Midlet midlet, int questionNo) {
        super("Cash Quiz");
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
        
        PlayMethods playMethod = new PlayMethods();
        this.questions = playMethod.getShuffledQuestions();
        this.currQuestionNo = questionNo;
        
        this.initUI();
    }
    
    public FormPlayQuestion(Midlet midlet, int questionNo, Question[] questions, int amountPrize, int fifty50, int audiencePoll) {
        super("Cash Quiz");
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
        
        this.questions = questions;
        this.currQuestionNo = questionNo;
        
        this.winAmount = amountPrize;
        this.fifty50 = fifty50;
        this.audiencePoll = audiencePoll;
        
        this.initUI();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdQuit) {
            FormWelcome frmWelcome = new FormWelcome(midlet);
            display.setCurrent(frmWelcome);
        }
        if (c == cmdSelect) {
            String correctAnswer = this.getCorrectAnswer();
            if (cgAnswer.getString(cgAnswer.getSelectedIndex()).equals(correctAnswer)){
                this.winAmount = this.winAmount * 2;
                if (this.currQuestionNo < 6) {
                    atStatus.setString("Answer is correct! You win $" + this.winAmount + " \n Moved to next question.");
                    atStatus.addCommand(cmdNext);
                } else {
                    atStatus.setString("Answer is correct! \n Congratulations! You Won total $" + this.winAmount);
                    atStatus.addCommand(cmdFinish);
                }
                atStatus.setCommandListener(this); //set the listner to alert
                display.setCurrent(atStatus, d);
            } else {
                if (this.currQuestionNo > 4) { // atleat win something...
                    atStatus.setString("Answer is incorrect! \n But you still win $" + this.winAmount);
                    atStatus.addCommand(cmdFinish);
                } else {
                    atStatus.setString("Answer is incorrect! \n Game Over!");
                    atStatus.setType(AlertType.ERROR);
                    atStatus.addCommand(cmdQuit);
                }
                atStatus.setCommandListener(this); //set the listner to alert
                display.setCurrent(atStatus, d);
            }
        }
        if (c == cmdNext) {
            this.currQuestionNo++; //next question
            FormPlayQuestion frmFormPlayQuestion = new FormPlayQuestion(midlet, this.currQuestionNo, this.questions, 
                    this.winAmount, this.fifty50, this.audiencePoll);
            display.setCurrent(frmFormPlayQuestion);
        }
        if (c == cmdFinish) {
            FormWelcome frmWelcome = new FormWelcome(midlet);
            display.setCurrent(frmWelcome);
        }
        if (c == cmdErrorOk) {
            FormWelcome frmWelcome = new FormWelcome(midlet);
            display.setCurrent(frmWelcome);
        }
        if (c == cmd5050) {
            this.fifty50 = 1;
            this.get5050();
            this.removeCommand(cmd5050);
        }
        if (c == cmdAudiencePoll) {
            this.audiencePoll = 1;
            this.getAudiencePoll();
            this.removeCommand(cmdAudiencePoll);
        }
    }
    
    private void initUI() {
        strQuestion = new StringItem("", "");
        strPollResult = new StringItem("Audience Poll Result: ", "");
        cgAnswer = new ChoiceGroup("Select the correct answer.", ChoiceGroup.EXCLUSIVE);
        
        cmdQuit = new Command("Quit", Command.BACK, 1);
        cmdSelect = new Command("Select", Command.OK, 1);
        cmdNext = new Command("Next", Command.OK, 1);
        cmdFinish = new Command("Finish", Command.OK, 1);
        cmdErrorOk = new Command("Ok", Command.OK, 1);
        
        atStatus = new Alert("Cash Quiz");
        atStatus.setTimeout(Alert.FOREVER);
        
        this.append(strQuestion);
        this.append(cgAnswer);
        this.addCommand(cmdQuit);
        this.addCommand(cmdSelect);
        
        if (fifty50 == 0) {
            cmd5050 = new Command("50:50", Command.OK, 1);
            this.addCommand(cmd5050);
        }
        if (audiencePoll == 0) {
            cmdAudiencePoll = new Command("Audience Poll", Command.OK, 1);
            this.addCommand(cmdAudiencePoll);
        }
        
        this.setCommandListener(this);
        
        this.loadContent();
    }
    
    private void loadContent() {
        
        if (this.questions.length != 6) {
            strQuestion.setText("Questions are missing!");
            return;
        }
        
        strQuestion.setText(this.questions[this.currQuestionNo-1].getQuestion());
        strQuestion.setLabel("Question # " + this.currQuestionNo);
        this.idxOptA = cgAnswer.append(this.questions[this.currQuestionNo-1].getOptionA(), null);
        this.idxOptB = cgAnswer.append(this.questions[this.currQuestionNo-1].getOptionB(), null);
        this.idxOptC = cgAnswer.append(this.questions[this.currQuestionNo-1].getOptionC(), null);
        this.idxOptD = cgAnswer.append(this.questions[this.currQuestionNo-1].getOptionD(), null);
    }
    
    private String getCorrectAnswer() {
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("A"))
            return this.questions[this.currQuestionNo-1].getOptionA();
        
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("B"))
            return this.questions[this.currQuestionNo-1].getOptionB();
        
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("C"))
            return this.questions[this.currQuestionNo-1].getOptionC();
        
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("D"))
            return this.questions[this.currQuestionNo-1].getOptionD();
        
        return null;
    }
    
    private void get5050(){
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("A")) {
            cgAnswer.delete(idxOptD);
            cgAnswer.delete(idxOptB);
        }
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("B")) {
            cgAnswer.delete(idxOptC);
            cgAnswer.delete(idxOptA);
        }
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("C")) {
            cgAnswer.delete(idxOptD);
            cgAnswer.delete(idxOptB);
        }
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("D")) {
            cgAnswer.delete(idxOptC);
            cgAnswer.delete(idxOptA);    
        }
    }
    
    private void getAudiencePoll() {
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("A")) {
            strPollResult.setText("A=75%, B=10%, C=5%, D=10%");
            this.append(strPollResult);
        }
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("B")) {
            strPollResult.setText("A=15%, B=70%, C=10%, D=5%");
            this.append(strPollResult);
        }
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("C")) {
            strPollResult.setText("A=10%, B=10%, C=60%, D=20%");
            this.append(strPollResult);
        }
        if (this.questions[this.currQuestionNo-1].getAnswer().equals("D")) {
            strPollResult.setText("A=20%, B=5%, C=10%, D=65%");
            this.append(strPollResult);
        }
    }

}
