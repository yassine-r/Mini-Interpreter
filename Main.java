package com.company;

import com.company.helpers.GraphicalInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> Tokens = new HashMap<>();
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> temp2 = new ArrayList<String>();

        temp.add("LOAD");
        temp.add("LOADC");
        temp.add("STORE");

        temp.add("ADD");
        temp.add("SUB");
        temp.add("MUL");

        temp.add("DIV");
        temp.add("INF");
        temp.add("SUP");
        temp.add("EGAL");

        temp.add("NOT");
        temp.add("OR");
        temp.add("AND");

        temp.add("JUMP");
        temp.add("JZERO");

        temp.add("WRITEC");
        temp.add("WRITE");

        temp.add("END");

        temp2.add("0");
        temp2.add("1");
        temp2.add("2");
        temp2.add("3");
        temp2.add("4");
        temp2.add("5");
        temp2.add("6");
        temp2.add("7");
        temp2.add("8");
        temp2.add("9");

        Tokens.put("IDENT-OPERATEUR", temp);
        Tokens.put("Entier", temp2);


        new GraphicalInterface(Tokens);
    }
}
