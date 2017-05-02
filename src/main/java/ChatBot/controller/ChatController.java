package ChatBot.controller;

import ChatBot.model.Bot;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ChatController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 02/05/17 20:03
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
    }
    
    public void txtInput_onAction ()
    {
        String input = txtInput.getText();
        displayText("Alex", input);
        txtInput.setText("");
        
        String output = bot.getAnswer(input);
        displayText("ChatBot", output);
    }
    
    private void displayText (String user, String message)
    {
        txtChat.setText(txtChat.getText() + user + " : " + message + "\n");
    }
}
