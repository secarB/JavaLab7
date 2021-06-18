package braces.server.commands;

import java.io.Serializable;
import java.util.Scanner;

import javax.lang.model.element.ExecutableElement;

import braces.server.fields.SpaceMarine;

/**
 * This abstract class is created to avoid implementing all the unnecessary methods of interface
 */
public abstract class AbstractCommand implements Command,Serializable{
    @Override
    public String execute(String argument) {
        return "Can't execute";
    }
	@Override
    public String execute(String argument,String owner) {
        return "Can't execute";
    }
    @Override
    public String execute() {
        return "Can't execute";
    }
    @Override
    public String execute(String argument, Scanner scanner) {
        return "Can't execute";
    }
    
    @Override
    public String execute(String argument, SpaceMarine spaceMarine) {
        return "Can't execute";
    }
    @Override
    public String execute(String argument, SpaceMarine spaceMarine, String user, String pass) {
        return "Can't execute";
    }
    @Override
    public String execute(SpaceMarine spaceMarine) {
        return "Can't execute";
    }
    @Override
    public String execute(Scanner scanner) {
        return "Can't execute";
    }
}