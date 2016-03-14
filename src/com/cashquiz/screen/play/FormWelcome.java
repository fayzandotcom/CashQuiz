package com.cashquiz.screen.play;

import com.cashquiz.midlet.Midlet;
import com.cashquiz.screen.admin.FormLogin;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.StringItem;

public class FormWelcome extends Form implements CommandListener{

    Display display;
    Midlet midlet;
    
    StringItem welcomeMsg;
    Command cmdExit;
    Command cmdPlay;
    Command cmdLogin;
    
    Image mainImage;
    
    public FormWelcome(Midlet midlet) {
        super("Cash Quiz!");
        this.iniUI();
        
        this.display = Display.getDisplay(midlet);
        this.midlet = midlet;
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdExit) {
            midlet.destroyApp(false);
            midlet.notifyDestroyed();
            
        }
        
        if (c == cmdPlay) {
            FormPlayStartInstruction frmStartInstruction = new FormPlayStartInstruction(midlet);
            display.setCurrent(frmStartInstruction);
        }
        
        if (c == cmdLogin) {
            FormLogin frmLogin = new FormLogin(midlet);
            display.setCurrent(frmLogin);
        }        
    }
    
    private void iniUI() {
        welcomeMsg = new StringItem("", "Welcome to Cash Quiz! Your knowledge can pay you here! :)");
        cmdExit = new Command("Exit", Command.EXIT, 1);
        cmdPlay = new Command("Play", Command.OK, 1);
        cmdLogin = new Command("Login", Command.OK, 1);
        try {
            mainImage = Image.createImage("/img/title.png" );
        } catch (IOException ex) {
            System.err.println("Unable to load image");
            ex.printStackTrace();
        }
        
        this.append(welcomeMsg);
        this.append(mainImage);
        this.addCommand(cmdExit);
        this.addCommand(cmdPlay);
        this.addCommand(cmdLogin);
        
        this.setCommandListener(this);
    }

}
