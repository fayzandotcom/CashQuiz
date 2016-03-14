package com.cashquiz.screen.admin;

import com.cashquiz.screen.play.FormWelcome;
import com.cashquiz.midlet.Midlet;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class FormLogin extends Form implements CommandListener{

    Display display;
    Midlet midlet;
    
    StringItem headerMsg;
    TextField tfUsername;
    TextField tfPassword;
    Command cmdBack;
    Command cmdOk;
    Alert atStatus;
    
    public FormLogin(Midlet midlet) {
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
        
        if (c == cmdOk) {
            if (tfUsername.getString().equals("admin") && tfPassword.getString().equals("admin")) {
                //success
                FormAdminPanel frmAdminPanel = new FormAdminPanel(midlet);
                display.setCurrent(frmAdminPanel);
            }else {
                //fail
                atStatus.setString("Login fail!");
                display.setCurrent(atStatus);
            }
        }
    }
    
    private void initUI() {
        headerMsg = new StringItem("", "Please enter admin username and password");
        
        tfUsername = new TextField("Username: ", "admin", 10, TextField.ANY);
        tfPassword = new TextField("Password: ", "admin", 10, TextField.PASSWORD);
        
        cmdBack = new Command("Back", Command.BACK, 1);
        cmdOk = new Command("Ok", Command.OK, 1);
        
        atStatus = new Alert("Login status");
        
        this.append(headerMsg);
        this.append(tfUsername);
        this.append(tfPassword);
        this.addCommand(cmdBack);
        this.addCommand(cmdOk);
        
        this.setCommandListener(this);
    }

}
