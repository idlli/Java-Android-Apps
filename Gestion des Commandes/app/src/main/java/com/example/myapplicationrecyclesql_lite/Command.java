package com.example.myapplicationrecyclesql_lite;

import java.util.Date;

public class Command {
    int NumCommamd;
    String Command;
    String DateCommand;
    int CodeCl;

    public Command(int numCommamd, String command, String dateCommand, int codeCl) {
        NumCommamd = numCommamd;
        Command = command;
        DateCommand = dateCommand;
        CodeCl = codeCl;
    }

    public Command(String command, String dateCommand, int codeCl) {
        Command = command;
        DateCommand = dateCommand;
        CodeCl = codeCl;
    }

    public int getNumCommamd() {
        return NumCommamd;
    }

    public void setNumCommamd(int numCommamd) {
        NumCommamd = numCommamd;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public String getDateCommand() {
        return DateCommand;
    }

    public void setDateCommand(String dateCommand) {
        DateCommand = dateCommand;
    }

    public int getCodeCl() {
        return CodeCl;
    }

    public void setCodeCl(int codeCl) {
        CodeCl = codeCl;
    }

    @Override
    public String toString() {
        return "Command{" +
                "Client Id='" + CodeCl + '\'' +
                "Command='" + Command + '\'' +
                ", DateCommand='" + DateCommand + '\'' +
                '}';
    }
}
