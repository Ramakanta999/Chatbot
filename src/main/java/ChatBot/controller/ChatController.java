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
 . Last Modified : 12/05/17 00:19
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ChatController
{
    public  TextField txtInput;
    public  TextArea  txtChat;
    private Bot       bot;
    
    public void initialize ()
    {
        bot = new Bot();
        Random rand = new Random();
    
        if(rand.nextBoolean())
        {
            displayText("ChatBot", bot.getBotPhrase(GREETINGS));
            displayText("ChatBot", bot.getBotPhrase(rand.nextBoolean() ? HOW_ARE_YOU : MY_NAME_IS));
        }
    }
    
    public void txtInput_onAction ()
    {
        String input = txtInput.getText();
        displayText(USER_NAME.getValue(), input);
        txtInput.setText("");
    
        String output = bot.getBotAnswer(input);
        displayText(CHATBOT_NAME.getValue(), output);
    }
    
    private void displayText (String user, String message)
    {
        txtChat.setText(txtChat.getText() + user + " : " + message + "\n");
    }
    
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
