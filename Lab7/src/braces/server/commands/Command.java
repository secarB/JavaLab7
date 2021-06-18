package braces.server.commands;

import java.util.Scanner;

import org.w3c.dom.UserDataHandler;

import braces.server.fields.SpaceMarine;

/**
 * Interface for all command class
 */
public interface Command {
    String execute(String argument);
    String execute();
    String execute(String argument,Scanner scanner);
    String execute(String argument,String owner);
    String execute(String argument,SpaceMarine spaceMarine);

    String execute(String argument,SpaceMarine spaceMarine, String Id, String Pass);
    String execute(Scanner scanner);	
    String execute(SpaceMarine spaceMarine);
}
