package com.cashquiz.midlet;

import com.cashquiz.screen.play.FormWelcome;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Midlet extends MIDlet{
    
    Display display;
    
    public Midlet() {
        display = Display.getDisplay(this);
    }

    public void startApp() {
        FormWelcome frmWelcome = new FormWelcome(this);
        display.setCurrent(frmWelcome);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    


}
