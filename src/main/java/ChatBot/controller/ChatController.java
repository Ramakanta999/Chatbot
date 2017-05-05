package ChatBot.controller;

import ChatBot.model.Bot;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ChatController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 05/05/17 22:23
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
    
        int flipCoin = new Random().nextInt(100);
        if(flipCoin < 50)
        {
            displayText("ChatBot", bot.getBotPhrase(1));
            displayText("ChatBot", bot.getBotPhrase(2));
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
