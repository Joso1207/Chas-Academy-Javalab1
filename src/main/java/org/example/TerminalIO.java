package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TerminalIO {

private final BufferedReader reader;
private final List<String> menuOptions;

public TerminalIO(List<String> menuOptions){
    reader = new BufferedReader(new InputStreamReader(System.in));
    this.menuOptions = menuOptions;
}

public String getOption(String message,String error){
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
            }
        }catch(IOException e){

            System.err.println(error);
            throw new RuntimeException();
            }
        }

    return selectedOption;
    }
}

