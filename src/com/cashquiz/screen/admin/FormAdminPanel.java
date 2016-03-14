package com.cashquiz.screen.admin;

import com.cashquiz.screen.play.FormWelcome;
import com.cashquiz.midlet.Midlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.StringItem;

public class FormAdminPanel extends Form implements CommandListener, ItemCommandListener{

    Display display;
    Midlet midlet;
    
    StringItem welcomeMsg;
    StringItem btnAddQuestion;
    Command cmdAddQuestion;
    StringItem btnViewQuestion;
    Command cmdViewQuestion;
    Command cmdBack;
    
    public FormAdminPanel(Midlet midlet) {
        super("Admin Panel");
        this.initUI();
        
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            FormWelcome frmWelcome = new FormWelcome(midlet);
            display.setCurrent(frmWelcome);
        }
    }
    
    public void commandAction(Command c, Item item) {
        if (c == cmdAddQuestion) {
            FormAddQuestion frmAddQuestion = new FormAddQuestion(midlet);
            display.setCurrent(frmAddQuestion);
        }
        
        if (c == cmdViewQuestion) {
            FormViewQuestion frmViewQuestion = new FormViewQuestion(midlet);
            display.setCurrent(frmViewQuestion);
        }
    }
    
    private void initUI (){
        welcomeMsg = new StringItem("", "Welcome to the admin panel. Here you can manage the application.");
        
        btnAddQuestion = new StringItem("Add new question", "Add", StringItem.BUTTON);
        cmdAddQuestion =  new Command("Add", Command.ITEM, 1);
        btnAddQuestion.setDefaultCommand(cmdAddQuestion);
        btnAddQuestion.setItemCommandListener(this);
        
        btnViewQuestion = new StringItem("View questions", "View", StringItem.BUTTON);
        cmdViewQuestion =  new Command("View", Command.ITEM, 1);
        btnViewQuestion.setDefaultCommand(cmdViewQuestion);
        btnViewQuestion.setItemCommandListener(this);
        
        cmdBack = new Command("Back", Command.BACK, 1);
        
        this.append(welcomeMsg);
        this.append(btnAddQuestion);
        this.append(btnViewQuestion);
        this.addCommand(cmdBack);
        
        this.setCommandListener(this);
    }

}
