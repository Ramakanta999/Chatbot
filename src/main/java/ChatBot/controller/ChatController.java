package ChatBot.controller;

import ChatBot.model.Bot;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static ChatBot.service.Const.*;
import static ChatBot.service.Const.ReplacementCode.CHATBOT_NAME;
import static ChatBot.service.Const.ReplacementCode.USER_NAME;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ChatController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 20/09/17 08:34
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 This Class the controller of ChatView.fxml.<br>
 It allows the user to interact with the ChatBot.<br>
 <br>
 It handles the events triggered by the GUI.<br>
 <br>
 __ Dependency : model.Bot, service.Const __
 */
public class ChatController
{
    public  TextField txtInput;
    public  TextArea  txtChat;
    private Bot       bot;
    
    /**
     Initializes the controller and with a chance of 5O%, makes the ChatBot engage the conversation.
     <br>
     __ Dependency : model.Bot, service.Const __
     */
    public void initialize ()
    {
        this.bot = new Bot();
        Random rand = new Random();
        
        //There is a 50% chance of the ChatBot engaging the conversation.
        if(rand.nextBoolean())
        {
            //ChatBot engages with normal greetings.
            displayText(CHATBOT_NAME.getValue(), bot.getBotPhrase(GREETINGS));
            //There is a 50% chance ChatBot either asks how you are or introduces itself.
            displayText(CHATBOT_NAME.getValue(), bot.getBotPhrase(rand.nextBoolean() ? HOW_ARE_YOU : MY_NAME_IS));
        }
    }
    
    /**
     Event Handler of txtInput : Triggered when user presses the Enter Key int the text area.<br>
     It will read the typed phrase and call for a ChatBot's answer.<br>
     <br>
     It will also clear the text area, ready for a new input.<br>
     <br>
     __ Dependency : service.Const __
     */
    public void txtInput_onAction ()
    {
        String input = txtInput.getText();
        displayText(USER_NAME.getValue(), input);
        txtInput.setText("");
        
        String output = bot.getBotAnswer(input);
        displayText(CHATBOT_NAME.getValue(), output);
    }
    
    /**
     Used to easily display a new input in the textView.<br>
     Will print "[user] : [message]" as a new line.<br>
     
     @param user    The name of who is typing the message (either CHATBOT_NAME or USER_NAME).
     @param message The content of the message to print.
     */
    private void displayText (String user, String message)
    {
        txtChat.setText(txtChat.getText() + user + " : " + message + "\n");
    }
    
    /**
     Event Handler of SeeConnections button : Triggered on a click of that button.<br>
     Will open an new GUI window : LinksView.fxml<br>
     The current Chat window will stay open.<br>
     <br>
     __ Dependency : service.Const __
     */
    @SuppressWarnings("ConstantConditions")
    public void btnSeeConnections_onAction ()
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(LINKS_VIEW_PATH));
            stage.setTitle("Links");
            stage.setScene(new Scene(root, LINKS_VIEW_WIDTH, LINKS_VIEW_HEIGHT));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     Event Handler of AddEntriesAndLinks button : Triggered on a click of that button.<br>
     Will open an new GUI window : LearningView.fxml<br>
     The current Chat window will stay open.<br>
     <br>
     __ Dependency : service.Const __
     */
    @SuppressWarnings("ConstantConditions")
    public void btnAddEntriesAndLinks_onAction ()
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(LEARNING_VIEW_PATH));
            stage.setTitle("Teaching");
            stage.setScene(new Scene(root, LEARNING_VIEW_WIDTH, LEARNING_VIEW_HEIGHT));
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
