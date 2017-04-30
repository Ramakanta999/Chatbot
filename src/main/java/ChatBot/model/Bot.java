package ChatBot.model;

import ChatBot.service.Const;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Bot	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 30/04/17 15:04
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Bot extends JFrame
{
    //TextField typing
    private JTextField txtEnter = new JTextField();
    
    //TextArea chatArea
    private JTextArea txtChat = new JTextArea();
    
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
        int phraseKey = -1;
    
        for (int key : getPhrases().keySet())
        {
            if(getPhrases().get(key).equalsIgnoreCase(input)) phraseKey = key;
        }
    
        if(getLinks().containsKey(phraseKey))
        {
            int returnPhrase = getLinks().get(phraseKey)[new Random().nextInt(getLinks().get(phraseKey).length)];
            System.out.println(getPhrases().get(returnPhrase));
        }
    }
    
    private void displayText (String user, String message)
    {
        txtChat.append(user + " : " + message + "\n");
    }
    
    private DataBase getDb ()
    {
        return Const.readDb();
    }
    
    private HashMap<Integer, String> getPhrases ()
    {
        return getDb().getPhrases();
    }
    
    private HashMap<Integer, Integer[]> getLinks ()
    {
        return getDb().getLinks();
    }
}
