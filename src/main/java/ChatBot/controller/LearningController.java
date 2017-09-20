package ChatBot.controller;

import ChatBot.model.DataBase;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.util.Set;

import static ChatBot.service.Const.readDb;
import static ChatBot.service.Const.writeDb;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LearningController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 20/09/17 11:23
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 This Class the controller of LearningView.fxml.<br>
 It allows the user to teach new inputs/answers to the ChatBot.<br>
 <br>
 It handles the events triggered by the GUI.<br>
 <br>
 __ Dependency : model.DataBase, service.Const __
 */
public class LearningController
{
    public TextField txtNewUser;
    public TextField txtNewBot;
    public TextField txtAddUser;
    public TextField txtAddBot;
    
    public Spinner<Integer> spinnerAddUser;
    public Spinner<Integer> spinnerAddBot;
    public Spinner<Integer> spinnerLinksUser;
    public Spinner<Integer> spinnerLinksBot;
    
    private DataBase db = readDb();
    
    /**
     Initializes the controller with values found from DataBase db (private attribute).
     <br>
     __ Warning : Reads db __<br>
     __ Dependency : model.Bot, service.Const __
     */
    public void initialize ()
    {
        //update db from .json File
        updateDb();
        
        Set<Integer> userKeys = db.getUserPhrasePools().keySet();
        Set<Integer> botKeys = db.getBotPhrasePools().keySet();
        
        initSpinner(spinnerLinksUser, getMinOf(userKeys), getMaxOf(userKeys));
        initSpinner(spinnerLinksBot, getMinOf(botKeys), getMaxOf(botKeys));
        initSpinner(spinnerAddUser, getMinOf(userKeys), getMaxOf(userKeys));
        initSpinner(spinnerAddBot, getMinOf(botKeys), getMaxOf(botKeys));
    }
    
    /**
     Initializes [spinner], witn [min] and [max], since we want defined limits on our spinners.
     
     @param spinner The spinner to initialize.
     @param min     Minimal value the spinner can access.
     @param max     Maximal value the spinner can access.
     */
    private void initSpinner (Spinner<Integer> spinner, int min, int max)
    {
        //The Spinner will now only accept integerValues between 0 and max.
        //The initialValue is set to 0.
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, 0));
        
        //The Spinner can't be modified by typing.
        //It can only be changed through the + and - buttons
        spinner.setEditable(false);
    }
    
    /**
     Returns the biggest Integer value of a Set<Integer>.
     This is needed since Set does not have sorting options unlike ArrayList.
     
     @param values Set of Integers to be analysed.
     @return The biggest Integer value found in [values].
     */
    private int getMaxOf (Set<Integer> values)
    {
        int max = Integer.MIN_VALUE;
        
        for (int val : values)
        {
            if(val > max) max = val;
        }
        
        return max;
    }
    
    /**
     Returns the smallest Integer value of a Set<Integer>.
     This is needed since Set does not have sorting options unlike ArrayList.
     
     @param values Set of Integers to be analysed.
     @return The smallest Integer value found in [values].
     */
    private int getMinOf (Set<Integer> values)
    {
        int min = Integer.MAX_VALUE;
        
        for (int val : values)
        {
            if(val < min) min = val;
        }
        
        return min;
    }
    
    /**
     Event Handler of NewPhrases button : Triggered on a click of that button.<br>
     Wille read txtNewUser and txtNewBot.<br>
     <br>
     Will add txtNewUser's value to Triggers' pool if not already added.<br>
     Will add txtNewBot's value to Answers' pool if not already added.<br>
     Will add a link leading txtNewUser's value to txtNewBot's value.<br>
     <br>
     __ Warning : Reads and Writes on db __<br>
     __ Dependency : model.Bot __
     */
    public void btnNewPhrases_onAction ()
    {
        String trigger = txtNewUser.getText().trim();
        String answer = txtNewBot.getText().trim();
        
        //update db from .json File
        updateDb();
        
        //region --> Trigger
        int triggerPoolIndex = db.findUserPhrase(trigger);
        
        if(triggerPoolIndex == -1)
        {
            triggerPoolIndex = getMaxOf(db.getUserPhrasePools().keySet()) + 1;
            
            db.getUserPhrasePools().put(triggerPoolIndex, new String[]{trigger});
        }
        //endregion
        
        //region --> Answer
        int answerPoolIndex = db.findBotPhrase(answer);
        
        if(answerPoolIndex == -1)
        {
            answerPoolIndex = getMaxOf(db.getBotPhrasePools().keySet()) + 1;
            
            db.getBotPhrasePools().put(answerPoolIndex, new String[]{answer});
        }
        //endregion
        
        //region --> Link
        if(db.getLinks().containsKey(triggerPoolIndex))
        {
            db.addToLink(triggerPoolIndex, answerPoolIndex);
        }
        else
        {
            db.addLink(triggerPoolIndex, answerPoolIndex);
        }
        //endregion
        
        writeDb(db);
    }
    
    /**
     Event Handler of AddLinks button : Triggered on a click of that button.<br>
     Adds a link between a User's input and a Bot's answer.<br>
     <br>
     __ Warning : Reads and Writes on db __<br>
     __ Dependency : model.Bot __
     */
    public void btnAddLinks_onAction ()
    {
        //update db from .json File
        updateDb();
        
        db.addToLink(spinnerLinksUser.getValue(), spinnerLinksBot.getValue());
        
        writeDb(db);
    }
    
    /**
     Event Handler of AddUserPool button : Triggered on a click of that button.<br>
     Adds a new entry to User's input pool.<br>
     <br>
     __ Warning : Reads and Writes on db __<br>
     __ Dependency : model.Bot __
     */
    public void btnAddUserPool_onAction ()
    {
        //update db from .json File
        updateDb();
        
        Integer poolIndex = spinnerAddUser.getValue();
        String toAdd = txtAddUser.getText().trim();
        String[] pool = db.getUserPhrasePool(poolIndex);
        
        if(db.findInArray(toAdd, pool)) return;
        
        db.addToUserPool(toAdd, poolIndex);
        
        writeDb(db);
    }
    
    /**
     Event Handler of AddBotPool button : Triggered on a click of that button.<br>
     Adds a new entry to Bot's answer pool.<br>
     <br>
     __ Warning : Reads and Writes on db __<br>
     __ Dependency : model.Bot __
     */
    public void btnAddBotPool_onAction ()
    {
        //update db from .json File
        updateDb();
        
        Integer poolIndex = spinnerAddBot.getValue();
        String toAdd = txtAddBot.getText().trim();
        String[] pool = db.getBotPhrasePool(poolIndex);
        
        if(db.findInArray(toAdd, pool)) return;
        
        db.addToBotPool(toAdd, poolIndex);
        
        writeDb(db);
    }
    
    /**
     Updates the current db object with the values read from the .json file.<br>
     When called before accessing db, this method ensures that your db is up to date either if the file was or wasn't modified
     */
    private void updateDb ()
    {
        db = readDb();
    }
}
