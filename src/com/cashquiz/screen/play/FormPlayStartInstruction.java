package com.cashquiz.screen.play;

import com.cashquiz.midlet.Midlet;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class FormPlayStartInstruction extends Form implements CommandListener {
    
    Display display;
    Midlet midlet;
    
    StringItem txtInstruction;
    
    Command cmdBack;
    Command cmdStart;

    public FormPlayStartInstruction(Midlet midlet) {
        super("Play Instructions");
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
        
        this.initUI();
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdBack) {
            FormWelcome frmWelcome = new FormWelcome(midlet);
            display.setCurrent(frmWelcome);
        }
        if (c == cmdStart) {
            FormPlayQuestion frmPlayQuestion = new FormPlayQuestion(midlet, 1);
            display.setCurrent(frmPlayQuestion);
        }
    }
    
    private void initUI() {
        txtInstruction = new StringItem("", "");
        
        cmdBack = new Command("Back", Command.BACK, 1);
        cmdStart = new Command("Start", Command.OK, 1);
        
        this.append(txtInstruction);
        this.addCommand(cmdBack);
        this.addCommand(cmdStart);
        
        this.setCommandListener(this);
        
        this.loadContent();
    }
    
    private void loadContent() {
        txtInstruction.setText("There are multiple choise questions, you have to choose any one.\n "
                + "If answer is correct you will proceed to next question else game over! \n"
                + "Press Start to start the game.");
    }
    
}
