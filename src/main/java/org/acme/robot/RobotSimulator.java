package org.acme.robot;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class RobotSimulator {

    public static void main(String[] args) {
        CommandLineOptions commandOptions = new CommandLineOptions();
        parseCommandLineArguments(args, commandOptions);
        InputStream inputStream = getInputStream(commandOptions);
        RobotController controller = new RobotController();
        controller.run(inputStream);
    }

    private static InputStream getInputStream(CommandLineOptions commandOptions) {
        InputStream inputStream = System.in;
        if (commandOptions.hasFileOption()) {
            System.out.println("Loading file: " + commandOptions.getFilename());
            try {
                File file = new File(commandOptions.getFilename());
                inputStream = new FileInputStream(file);
            } catch (Exception ex) {
                System.err.println("Problem loading file, using standard input.");
            }
        }
        return inputStream;
    }

    private static void parseCommandLineArguments(String[] args, CommandLineOptions commandOptions) {
        try {
            commandOptions.parseArgument(args);
        } catch (ParseException e) {
            commandOptions.displayHelp();
            System.exit(1);
        }
    }

    private static class CommandLineOptions {

        private Options options;
        private CommandLine commandLine;

        public CommandLineOptions() {
            options = new Options();
            options.addOption("f", true, "When option is specified, the program will use the file as input instead of standard input.  The file must specify the full path to the file. ");
        }

        public void parseArgument(String[] args) throws ParseException {
            CommandLineParser parser = new PosixParser();
            commandLine = parser.parse(options, args);
        }

        public void displayHelp() {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "Robot Simulator", options );
        }

        public boolean hasFileOption() {
            return commandLine.hasOption("f");
        }

        public String getFilename() {
            return commandLine.getOptionValue("f");
        }
    }
}