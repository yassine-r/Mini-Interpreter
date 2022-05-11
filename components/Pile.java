package com.company.components;

import java.lang.constant.Constable;
import java.util.ArrayList;

public class Pile {

    public ArrayList<Integer> liste = new ArrayList<>();

    public void push(int ob) {
        this.liste.add(ob);
    }

    public void push(String ob) {
        this.liste.add(Integer.parseInt((ob)));
    }

    @Override
    public String toString() {
        return "Pile{" +
                "liste=" + liste +
                '}';
    }

    public Constable get2(int a){
        if(this.liste.size()>=a+1) {
            return this.liste.get(a);
        }
        return null;
    }

    public void push_personalised(int a,int b){
        if(this.liste.size()<a+1){
            for(int i=this.liste.size();i<a;i++){
                this.liste.add(0);
            }
            this.liste.add(b);
        }
        else {
            this.liste.set(a,b);
        }
    }

    public int pop() {
        int n = liste.size();
        int a;
        a = liste.get(n - 1);
        this.liste.remove(n - 1);
        return a;
    }
}
