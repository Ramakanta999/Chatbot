package ChatBot.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Bot	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 30/04/17 11:38
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Bot extends JFrame
{
    //TextField typing
    private JTextField txtEnter = new JTextField();
    
    //TextArea chatArea
    private JTextArea txtChat = new JTextArea();
    
    private String[][] db = new String[][]{
            //Greetings
            {"hi", "hello", "bonjour", "salut"}, {"hey", "Hi !", "yo", "ouech"},
            //How are you
            {"how are you", "comment ça va", "comment tu va", "how r u"}, {"I'm good", "doing good", "ça va bien", "bien bien"},
            //Default
            {"I don't understand", "je ne comprends pas"}};
    
    public Bot ()
    {
        //Creating Frame
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        setTitle("JavaBot");
        setLayout(null);
        
        //Creating txtEnter for typing
        txtEnter.setLocation(5, 540);
        txtEnter.setSize(590, 30);
        
        txtEnter.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                handleAnswers();
            }
        });
        
        //Creating txtChat for the chat
        txtChat.setLocation(5, 5);
        txtChat.setSize(590, 530);
        txtChat.setEditable(false);
        
        //Adding to Frame
        add(txtEnter);
        add(txtChat);
        setVisible(true);
    }
    
    private void handleAnswers ()
    {
        //Get input value
        String input = txtEnter.getText().trim();
        displayText("You", input);
        txtEnter.setText("");
        
        //Removing punctuation (for now)
        while (input.matches("^.*[.,;?! ]$"))
        {
            input = input.substring(0, input.length() - 1);
        }
        
        //Searching for match
        
        // 0: Still searching
        // 1: Nothing was found
        // 2: Result was found
        boolean foundAnAnswer = false;
        int j = 0;
        while (!foundAnAnswer)
        {
            if(isInArray(input, db[j * 2]))
            {
                foundAnAnswer = true;
                int rand = new Random().nextInt(db[(j * 2) + 1].length);
                displayText("Bot", db[(j * 2) + 1][rand]);
            }
            j++;
            
            if(j * 2 >= db.length - 1 && !foundAnAnswer)
            {
                foundAnAnswer = true;
                int rand = new Random().nextInt(db[db.length - 1].length);
                displayText("Bot", db[db.length - 1][rand]);
            }
        }
    }
    
    private boolean isInArray (String toFind, String[] toSearchIn)
    {
        boolean found = false;
        
        for (String string : toSearchIn)
        {
            if(string.equalsIgnoreCase(toFind)) found = true;
        }
        return found;
    }
    
    private void displayText (String user, String message)
    {
        txtChat.append(user + " : " + message + "\n");
    }
    
}
