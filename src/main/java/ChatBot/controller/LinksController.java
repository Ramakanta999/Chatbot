package ChatBot.controller;

import ChatBot.model.DataBase;
import ChatBot.service.Const;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LinksController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 05/05/17 22:26
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class LinksController
{
    public AnchorPane       currentPane;
    public ListView<String> listViewUserPhrases;
    public ListView<String> listViewBotPhrases;
    public TextField        txtInput;
    public Label            lblError;
    
    public void search_onAction ()
    {
        lblError.setVisible(false);
        DataBase db = Const.readDb();
        String input = txtInput.getText().trim();
        int userIndex;
        
        try
        {
            userIndex = Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            userIndex = db.findUserPhrase(input);
        }
        
        if(db.getUserPhrasePools().containsKey(userIndex))
        {
            listViewUserPhrases.getItems().setAll(db.getUserPhrasePool(userIndex));
            
            listViewBotPhrases.getItems().clear();
            for (int botIndex : db.getLink(userIndex))
            {
                listViewBotPhrases.getItems().addAll(db.getBotPhrasePool(botIndex));
            }
        }
        else
        {
            lblError.setVisible(true);
        }
    }
}
