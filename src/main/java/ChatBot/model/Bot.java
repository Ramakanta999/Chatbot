package ChatBot.model;

import ChatBot.service.Const;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Bot	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 01/05/17 15:26
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Bot extends DataBase
{
    public TextField txtInput;
    public TextArea  txtChat;
    
    @FXML
    public void initialize ()
    {
        updateFromFile();
    }
    
    public void txtInput_onAction ()
    {
        updateFromFile();
        
        //Get input value
        String input = txtInput.getText().trim();
        displayText("You", input);
        txtInput.setText("");
        
        //Removing punctuation (for now)
        while (input.matches("^.*[.,;?! ]$"))
        {
            input = input.substring(0, input.length() - 1);
        }
        
        //Searching for match
        int inputPoolIndex = findPhrase(input);
        int randLinkIndex = new Random().nextInt(getLink(inputPoolIndex).length);
        int outputPoolIndex = getLink(inputPoolIndex)[randLinkIndex];
    
        int randPhraseIndex = new Random().nextInt(getPool(outputPoolIndex).length);
        String output = getPhrase(outputPoolIndex, randPhraseIndex);
    
        displayText("Bot", output);
    }
    
    private void displayText (String user, String message)
    {
        txtChat.setText(txtChat.getText() + user + " : " + message + "\n");
    }
    
    private void updateFromFile ()
    {
        DataBase db = Const.readDb();
        setLinks(db.getLinks());
        setPools(db.getPools());
    }
}
