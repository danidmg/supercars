package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;

public abstract class Command {

	private static String UNKNOWN_COMMAND_MSG = "Unknown command";
	private static String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";
	
	protected static Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new MoveUpCommand(),
		new MoveDownCommand(),
		new MoveStraightCommand(),
		new ResetCommand("", (long) 0),
		new TestCommand(),
		new ExitCommand(),
		new ShootCommand(),
		new GrenadeCommand(0,0),
		new WaveCommand(),
		new ClearCommand(),
		new CheatCommand(""),
		new SerializeCommand(),
		new SaveCommand(""),
		new DumpCommand("")
	};
	
	private String name;
	private String shortcut;
	private String details ;
	private String help;
	public Command(String name, String shortcut, String details, String help) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	public static Command getCommand(String[] commandWords) throws CommandParseException{
		try {
			for (Command c:AVAILABLE_COMMANDS) {
				Command cReturn = c.parse(commandWords);
				if (cReturn != null) {
					return cReturn;
				}	
			}
		}
		catch (Exception e) {  //made it catch any exception so that it catches NumberFormatExceptions too
			System.out.println(e.getMessage());
			throw new CommandParseException("Unable to process command");
		}
		throw new CommandParseException(UNKNOWN_COMMAND_MSG);
	}
	
	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}
	
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0]))
			if (words.length != 1) {
				throw new CommandParseException(String.format("Command %s: %s", name, INCORRECT_NUMBER_OF_ARGS_MSG));
			} 	
			else {
				return this;
			}
	return null;
	}
	
	protected static String cHelp() {
		StringBuilder str = new StringBuilder();
		str.append("Available commands:\n");
		for (Command c: AVAILABLE_COMMANDS) {
			str.append(c.details);
			str.append(": ");
			str.append(c.help);
			str.append("\n");
		}
		return str.toString();
	}
}
