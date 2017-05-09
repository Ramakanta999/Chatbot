package ChatBot.controller;

import ChatBot.model.Bot;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

import static ChatBot.service.Const.*;
import static ChatBot.service.Const.ReplacementCode.CHATBOT_NAME;
import static ChatBot.service.Const.ReplacementCode.USER_NAME;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ChatController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 09/05/17 08:38
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
}
