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
 . Last Modified : 20/09/17 10:59
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 This Class the controller of LinksView.fxml.<br>
 It allows the user to look for the possible answers linked to an input.<br>
 <br>
 It handles the events triggered by the GUI.<br>
 <br>
 __ Dependency : model.DataBase, service.Const __
 */
public class LinksController
{
    public AnchorPane       currentPane;
    public ListView<String> listViewUserPhrases;
    public ListView<String> listViewBotPhrases;
    public TextField        txtInput;
    public Label            lblError;
    
    /**
     Event Handler of search button : Triggered on a click of that button.<br>
     Shows in GUI bot User's and Bot's known linked entries.<br>
     <br>
     __ Warning : Reads db __
     <br>
     __ Dependency : model.Bot __
     */
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
