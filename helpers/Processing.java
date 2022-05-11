package com.company.helpers;

import com.company.components.InstructionCible;
import com.company.components.Pile;

import java.awt.*;
import java.io.File;
import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Processing {

    public static ArrayList<String> Process(ArrayList<InstructionCible> VIC) {
        Pile pile=new Pile();
        int co=0;
        ArrayList<String> result=new ArrayList<>();

        //Impression de Vecteur des instructions Cibles
        //**************
        System.out.println(VIC);
        //**************

        if(VIC.get(VIC.size()-1).getOperateur().equals("ERROR")){

            if(VIC.size()>=2){
                result.add("Erreur  après l'instruction "+VIC.get(VIC.size()-2).getOperateur()+" "+VIC.get(VIC.size()-2).getOperand()+";");
            }else {
                result.add("Erreur  dans la première instruction");
            }
            GraphicalInterface.result.setForeground(Color.red);
            return result;
        }else {
            GraphicalInterface.result.setForeground(Color.green);
        }

        while (!VIC.get(co).getOperateur().equals("END")) {
            int i = co;
            co += 1;

            if (VIC.get(i).getOperateur().equals("LOAD")) {
                Constable c;
                if(VIC.get(i).getOperand().equals("")){
                    int s=pile.pop();
                    c = pile.get2(s);
                }else{
                    c =pile.get2(Integer.parseInt(VIC.get(i).getOperand()));
                }
                if(c!=null){
                    pile.push(c.toString());
                }
                else {
                    result.add("Erreur: L'adress pointée par LOAD ne contient aucune valeur");
                    GraphicalInterface.result.setForeground(Color.red);
                    return result;
                }

            }
            if (VIC.get(i).getOperateur().equals("LOADC")) {
                if(VIC.get(i).getOperand().equals("")){
                    return msg(result,"LOADC doit");
                }
                pile.push(VIC.get(i).getOperand());
            }
            if (VIC.get(i).getOperateur().equals("STORE")) {
                if(VIC.get(i).getOperand().equals("")){
                    return msg(result,"STORE doit");
                }
                int a = pile.pop();
                pile.push_personalised(Integer.parseInt(VIC.get(i).getOperand()),a);
            }
            if (VIC.get(i).getOperateur().equals("ADD")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"ADD ne doit pas");
                }
                int a = pile.pop();
                int b = pile.pop();
                pile.push(a + b);
            }
            if (VIC.get(i).getOperateur().equals("SUB")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"SUB ne doit pas");
                }
                int a = pile.pop();
                int b = pile.pop();
                pile.push(b - a);
            }
            if (VIC.get(i).getOperateur().equals("MUL")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"MUL ne doit pas");
                }
                int a = pile.pop();
                int b = pile.pop();
                pile.push(a * b);
            }
            if (VIC.get(i).getOperateur().equals("DIV")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"DIV ne doit pas");
                }
                int a = pile.pop();
                int b = pile.pop();
                pile.push(b / a);
            }
            if (VIC.get(i).getOperateur().equals("INF")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"INF ne doit pas");
                }
                int a = pile.pop();
                int b = pile.pop();
                pile.push(Math.min(a, b));
            }
            if (VIC.get(i).getOperateur().equals("SUP")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"SUP ne doit pas");
                }
                int a = pile.pop();
                int b = pile.pop();
                pile.push(Math.max(a, b));
            }
            if (VIC.get(i).getOperateur().equals("EGAL")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"EGAL ne doit pas");
                }
                int a = pile.pop();
                int b = pile.pop();
                if (a == b) pile.push(a);
            }
            if (VIC.get(i).getOperateur().equals("NOT")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"NOT ne doit pas");
                }
                int a = pile.pop();
                pile.push(-a);
            }
            if (VIC.get(i).getOperateur().equals("OR")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"OR ne doit pas");
                }
                int a = pile.pop();
                int b = pile.pop();
                if (a == 1 && b == 1) pile.push("0");
                else pile.push("1");
            }
            if (VIC.get(i).getOperateur().equals("AND")) {
                if(!VIC.get(i).getOperand().equals("")){
                    return msg(result,"AND ne doit pas");
                }
                int a = (int) pile.pop();
                int b = (int) pile.pop();
                if (a == 1 && b == 1) pile.push("1");
                else pile.push("0");
            }
            if (VIC.get(i).getOperateur().equals("JUMP")) {
                if(VIC.get(i).getOperand().equals("")){
                    return msg(result,"JUMP doit");
                }
                co = Integer.parseInt(VIC.get(i).getOperand());
            }
            if (VIC.get(i).getOperateur().equals("JZERO")) {
                if(VIC.get(i).getOperand().equals("")){
                    return msg(result,"JZERO doit");
                }
                if (pile.pop() == 0) {
                    co = Integer.parseInt(VIC.get(i).getOperand());
                }
            }
            if (VIC.get(i).getOperateur().equals("WRITEC")) {
                if(VIC.get(i).getOperand().equals("")){
                    return msg(result,"WRITEC doit");
                }
                result.add(VIC.get(i).getOperand());
            }
            if (VIC.get(i).getOperateur().equals("WRITE")) {
                if(VIC.get(i).getOperand().equals("")){
                    return msg(result,"WRITE doit");
                }
                result.add(String.valueOf(pile.get2(Integer.parseInt(VIC.get(i).getOperand()))));
            }
        }
        return result;
    }

    public static ArrayList<String> msg(ArrayList<String> result, String txt){
        result.add("Erreur : \n"+ txt +" avoir un Opérande");
        GraphicalInterface.result.setForeground(Color.red);
        return result;
    }
    public static ArrayList<InstructionCible> VIC(File file,HashMap<String, ArrayList<String>> Tokens){
        ArrayList<String> sentences = Processing.getSentences(file);
        InstructionCible instruction;
        ArrayList<InstructionCible> VIC=new ArrayList<>();
        for (String sentence : sentences) {
            instruction = Processing.getInstruction(sentence, Tokens);
            if (instruction.getOperateur().equals("ERROR")) {
                System.out.println("ERROR");
//                VIC.clear();
                VIC.add(new InstructionCible("ERROR", ""));
                return VIC;
            } else {
                VIC.add(instruction);
            }
        }
        return VIC;
    }

    public static InstructionCible getInstruction(String sequence,HashMap<String, ArrayList<String>> Tokens) {
        ArrayList<String> sentence = Processing.split(sequence);
        InstructionCible instruction;
        ArrayList<String> test;
        String st2 = "";
        if (sentence.size() == 1 || sentence.size() > 3) {
            return  new InstructionCible("ERROR", "");
        } else {
            if (sentence.size() == 3) {
                if (!sentence.get(2).equals(";")) {
                    return  new InstructionCible("ERROR", "");
                } else {
                    test = Tokens.get("IDENT-OPERATEUR");
                    if (test.contains(sentence.get(0))) {
                        instruction=new InstructionCible(sentence.get(0));
                    } else {
                        return  new InstructionCible("ERROR", "");
                    }
                    test = Tokens.get("Entier");
                    for (int i = 0; i < sentence.get(1).length(); i++) {
                        if (!test.contains(Character.toString(sentence.get(1).charAt(i)))) {
                            return  new InstructionCible("ERROR", "");
                        } else {
                            st2 = st2 + sentence.get(1).charAt(i);
                        }
                    }
                    instruction.setOperand(sentence.get(1));
                }
            } else {
                if (!sentence.get(1).equals(";")) {
                    return  new InstructionCible("ERROR", "");
                } else {
                    test = Tokens.get("IDENT-OPERATEUR");
                    if (test.contains(sentence.get(0))) {
                        instruction=new InstructionCible(sentence.get(0));

                    } else {
                        return  new InstructionCible("ERROR", "");

                    }
                }
            }
        }
        return instruction;
    }
    public static ArrayList<String> getSentences(File file) {
        ArrayList<String> sentences = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                process_line(sentences, line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentences;
    }
    public static void process_line(ArrayList<String> sentences, String line) {
        boolean test = false;
        StringBuilder sentence = new StringBuilder();
        if (line.length() >= 2) {
            if (line.charAt(0) == '/' && line.charAt(1) == '/') {
                return;
            }
        }
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ';') {
                sentence.append(line.charAt(i));
            } else {
                test = true;
                sentence.append(' ');
                sentence.append(line.charAt(i));
                sentences.add(sentence.toString());
                sentence = new StringBuilder();
            }
            if (!test && i == line.length() - 1) {
                sentences.add(sentence.toString());
            }
        }
    }

    public static ArrayList<String> split(String sentence) {
        sentence = sentence + " ";
        ArrayList<String> words = new ArrayList<>();
        String word = "";
        int j = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) != ' ') {
                word = word + sentence.charAt(i);
            } else {
                if (!word.equals("")) {
                    words.add(word);
                    j = j + 1;
                }
                word = "";
            }
        }
        return words;
    }
}
