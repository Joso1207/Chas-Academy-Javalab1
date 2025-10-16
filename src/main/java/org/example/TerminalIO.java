package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminalIO {
private final static Logger log = LoggerFactory.getLogger(TerminalIO.class);

    private final BufferedReader reader;
    private List<String> menuOptions;

    //Constructor for when we want only select options to be available,
    public TerminalIO(List<String> menuOptions){
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.menuOptions = menuOptions;
    }

    public TerminalIO(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.menuOptions = null;
    }


    //Lets the user input an option out of the options defined in menuOptions.
    public String validMenuOption(String message, String error) throws ElementIsEmptyException {
        if(menuOptions == null|| menuOptions.isEmpty()){
        log.error("No options included");
        throw new ElementIsEmptyException("Menu instance contains no viable options");
    }

    if(!message.isBlank()){
        System.out.println(message);
    }
    boolean validOption = false;
    String selectedOption = "";
    while(!validOption){
        try {
            String line = reader.readLine();
            if (menuOptions.contains(line)) {
                selectedOption = line;
                validOption = true;
            }else{
                System.err.println("Not valid option");
            }
        }catch(IOException e){

            System.err.println(error);
            throw new RuntimeException();
            }
        }

    return selectedOption;
    }

    //Lets the user input text not defined in menuoptions.
    public String readTextInput(String message, String error){
        if(!message.isBlank()){
            System.out.println(message);
        }

        String input = "";
        while(input.isBlank()){
            try{input = reader.readLine();}
            catch(IOException e){
                System.out.println(error);
                throw new RuntimeException();
            }

        }

        return input;


    }

    //Same as previous,  But for numbers specifically.
    public int readNumberInput(String message, String error) {
        if (!message.isBlank()) {
            System.out.println(message);
        }

        int inputNumber = 0;
        String line;
        boolean validInput = false;
        while (!validInput) {
            try {
                line = reader.readLine();
                if (line.matches("[0-9]+")) {
                    inputNumber = Integer.parseInt(line);
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println(error);
                throw new RuntimeException();
            }

        }

    return inputNumber;

    }

    public void setMenuOptions(List<String> menuOptions) {
        this.menuOptions = menuOptions;
    }


}

