package com.company.components;

public class InstructionCible {
    private String operateur;
    private String operand;

    public InstructionCible(String operateur) {
        this.operateur = operateur;
        this.operand = "";
    }

    public InstructionCible(String operateur, String operand) {
        this.operateur = operateur;
        this.operand = operand;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        return "InstructionCible{" +
                "operateur='" + operateur + '\'' +
                ", operand='" + operand + '\'' +
                '}';
    }
}
