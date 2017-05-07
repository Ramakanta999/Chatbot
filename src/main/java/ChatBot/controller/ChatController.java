package ChatBot.controller;

import ChatBot.model.Bot;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

import static ChatBot.service.Const.*;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ChatController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 07/05/17 19:20
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ChatController
{
    public  TextField txtInput;
    public  TextArea  txtChat;
    private Bot       bot;
    
    @FXML
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
        displayText("Alex", input);
        txtInput.setText("");
    
        String output = bot.getBotAnswer(input);
        displayText("ChatBot", output);
    }
    
    private void displayText (String user, String message)
    {
        txtChat.setText(txtChat.getText() + user + " : " + message + "\n");
    }
}
