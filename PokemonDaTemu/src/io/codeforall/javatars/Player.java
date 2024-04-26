package io.codeforall.javatars;

import io.codeforall.javatars.pokemons.*;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class Player implements Runnable {

    private Socket playerSocket;
    private String name;
    private PrintStream out;
    private InputStream in;
    private Prompt prompt;
    protected StringInputScanner question1;
    protected Pokemon myPokemon;
    protected HashMap<String, Pokemon> starter = HashMap.newHashMap(3);
    private String inicialColor = "\u001B[32m";
    private String finalColor = "\u001B[0m";

    private boolean ready = false;

    public Player(Socket playerSocket) throws IOException {
        this.playerSocket = playerSocket;
        out = new PrintStream(playerSocket.getOutputStream());
        in = playerSocket.getInputStream();
        starter.put("Mario", new Mario());
        starter.put("Rui", new Rui());
        starter.put("Alex", new Alex());
        prompt = new Prompt(in, out);
    }

    @Override
    public void run() {
        choosePokemon();
        int option1 = choosePath();

        if (option1 == 0) {
            out.println(inicialColor + "\n´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor +
                    "\nYou are exploring CodeForAll and you found relvinha.\n" +
                    "While searching for a puff to sleep in, a wild bug appeared.");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            int option2 = relvinha();
            if (option2 == 0) {
                fightBug(new Bug());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor +
                        "\nAfter overcoming your first challenge you continued your adventure through Summarizer route.\n" +
                        "While you were catching strawberries, guess what... a new wild bug appeared.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                int option3 = relvinha();
                if (option3 == 0) {
                    fightBug(new Bug());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor +
                            "\nAfter overcoming your second challenge you continued your adventure through Homework Avenue.\n" +
                            "You were gazing upon Luciano Sun, and a strange figure appeared from the bushes.\n" +
                            "Its an Hacker");
                    int option4 = relvinha();
                    if (option4 == 0) {
                        fightAcaro(new Acaro());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                        out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor +
                                "\nYou travelled a long way with a basket full of strawberries and a red sunburn.");
                    }
                }
            }
        }
        out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´\n" + finalColor +
                "You arrived at the Gym.\n" +
                "Now you have to wait for your opponent to arrive." + inicialColor +
                "\n´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
        ready = true;
    }

    private void choosePokemon() {
        out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣴⣶⣶⣶⣶⣶⣶⣶⣦⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣿⡿⠿⠛⠛⠋⠉⠉⠉⠉⠉⠙⠛⠻⠿⣿⣿⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣿⣷⣄⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣴⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣿⣦⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣠⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣷⡄⠀⠀⠀\n" +
                "⠀⠀⠀⣼⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣆⠀⠀\n" +
                "⠀⠀⣼⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣆⠀\n" +
                "⠀⢰⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⡄\n" +
                "⠀⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣧\n" +
                "⢸⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿\n" +
                "⢸⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿\n" +
                "⢸⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿\n" +
                "⠈⣿⣿⣿⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⡿\n" +
                "⠀⢻⣿⡿⢿⣿⣶⣄⡀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⠿⠟⠛⠿⢿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣿⡿⣿⣿⡇\n" +
                "⠀⠈⢿⣿⡄⠉⠛⠿⣿⣷⣶⣤⣤⣀⣠⣿⡿⠋⢠⠴⠒⠒⠲⢤⡈⠻⣿⣷⣀⣠⣤⣴⣶⣿⡿⠿⠋⠁⣰⣿⡟⠀\n" +
                "⠀⠀⠈⢿⣿⡄⠀⠀⠀⠈⠉⠛⠻⠿⣿⣿⡇⠀⡏⠀⠀⠀⠀⠈⡇⠀⢻⣿⡿⠿⠛⠛⠉⠁⠀⠀⠀⣰⣿⡟⠀⠀\n" +
                "⠀⠀⠀⠈⢿⣿⣦⠀⠀⠀⠀⠀⠀⠀⢸⣿⣧⡀⠻⣄⡀⠀⣀⡴⠃⢠⣿⣿⠁⠀⠀⠀⠀⠀⠀⢀⣼⣿⠟⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠻⣿⣷⣄⡀⠀⠀⠀⠀⠀⠙⢿⣿⣦⣄⣉⣉⣁⣤⣶⣿⠿⠁⠀⠀⠀⠀⠀⢀⣴⣿⡿⠋⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣦⣀⠀⠀⠀⠀⠀⠉⠛⠿⠿⠿⠿⠟⠋⠁⠀⠀⠀⠀⠀⣠⣴⣿⡿⠋⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⢿⣿⣶⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⣶⣿⡿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⠿⢿⣿⣿⣷⣶⣶⣶⣿⣿⣿⡿⠿⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" +
                "\nHello there! Welcome to the world of CODE FOR ALL_!\n" +
                "My name is Professor Gus Oak! People call me the Bug Jedi MC! This world is inhabited by creatures called Bugs!\n" +
                "For some people, Bugs are problems. Others use them for hacking. Myself...I study Bugs as a profession.");
        question1 = new StringInputScanner();
        question1.setMessage("\nFirst, what is your name? ");
        name = prompt.getUserInput(question1);
        String[] options1 = {"Mario", "Rui", "Alex"};
        MenuInputScanner question1 = new MenuInputScanner(options1);
        question1.setMessage("\u001B[32m" + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + "\u001B[0m" +
                "\n\n" + "Now, choose a starter MC:");
        int choice = prompt.getUserInput(question1) - 1;
        myPokemon = starter.get(options1[choice]);
        out.println("\nYou chose " + options1[choice] + "." + inicialColor +
                "\n´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private int choosePath() {
        out.println("Now that you have chosen your MC, you are ready to explore the CodeForAll world or fight with great opponents in the Dark-Side Gym.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        String[] options2 = {"Explore CodeForAll", "Go to the Dark-Side Gym"};
        MenuInputScanner question2 = new MenuInputScanner(options2);
        question2.setMessage("Where do you want to go? ");
        return prompt.getUserInput(question2) - 1;
    }

    private int relvinha() {
        String[] options = {"Yes", "No"};
        MenuInputScanner question = new MenuInputScanner(options);
        question.setMessage("Do you want to fight it? ");
        int choice = prompt.getUserInput(question) - 1;
        out.println(inicialColor + "\n´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
        return choice;
    }

    public PrintStream getOut() {
        return out;
    }

    public void attack(Pokemon pokemon) {
        myPokemon.attack(pokemon);

        String[] options1 = {myPokemon.getMoves()[0], myPokemon.getMoves()[1]};
        MenuInputScanner question1 = new MenuInputScanner(options1);
        question1.setMessage("Choose your MC´s move!");
        int choice = prompt.getUserInput(question1) - 1;
        out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);

        for (Player player : Server.playerList) {
            player.out.println(myPokemon + " used " + options1[choice] + "\n");
            if (pokemon.getHp() > 0) {
                player.out.println(pokemon + " hp: " + pokemon.getHp() + "/" + pokemon.getMaxHp());
            }
            player.out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
        }

        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        if (pokemon.getHp() <= 0) {
            myPokemon.levelUp();
            out.println(myPokemon + " leveled up!");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            myPokemon.restoreHp();
            out.println("Nurse Carol gave you a kiss and your MC´s health was restored!");
        }*/
    }

    public void fightBug(Pokemon pokemon) {

        while (pokemon.getHp() > 0 && myPokemon.getHp() > 0) {

            //myPokemon attack
            String[] options1 = {myPokemon.getMoves()[0], myPokemon.getMoves()[1]};
            MenuInputScanner question1 = new MenuInputScanner(options1);
            question1.setMessage("Choose your MC´s move!");
            int choice = prompt.getUserInput(question1) - 1;
            out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
            myPokemon.attack(pokemon);
            out.println(myPokemon + " used: " + myPokemon.getMoves()[choice] + "\n");
            if (pokemon.getHp() > 0) {
                out.println(pokemon + " hp: " + pokemon.getHp() + "/" + pokemon.getMaxHp());
                out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            //bug attack
            if (pokemon.getHp() > 0) {
                pokemon.attack(myPokemon);
                int move = (int) (Math.random() * 2);
                out.println("The wild bug used " + pokemon.getMoves()[move] + "\n");
                out.println(myPokemon + " hp: " + myPokemon.getHp() + "/" + myPokemon.getMaxHp());
                out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        out.println("The wild bug has fainted!");
        out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        myPokemon.levelUp();
        out.println(myPokemon + " leveled up!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        myPokemon.restoreHp();
        out.println("Nurse Carol gave you a kiss and your MC´s health was restored!");
    }

    public void fightAcaro(Acaro acaro) {
        out.println("The Hacker used a bug.");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        for (Bug bug : acaro.getBugs()) {

            while (bug.getHp() > 0 && myPokemon.getHp() > 0) {
                //myPokemon attack
                String[] options2 = {myPokemon.getMoves()[0], myPokemon.getMoves()[1]};
                MenuInputScanner question2 = new MenuInputScanner(options2);
                question2.setMessage("Choose your pokemon move!");
                int choice2 = prompt.getUserInput(question2) - 1;
                out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
                myPokemon.attack(bug);
                out.println(myPokemon + " used: " + myPokemon.getMoves()[choice2] + "\n");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                if (bug.getHp() > 0) {
                    out.println(bug + " hp: " + bug.getHp() + "/" + bug.getMaxHp());
                    out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                //bug attack
                if (bug.getHp() > 0) {
                    bug.attack(myPokemon);
                    int move = (int) (Math.random() * 2);
                    out.println("The wild bug used " + bug.getMoves()[move] + "\n");
                    out.println(myPokemon + " hp: " + myPokemon.getHp() + "/" + myPokemon.getMaxHp());
                    out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            out.println("The hacker´s bug has fainted!");
            if (acaro.getBugs()[1].getHp() > 0) {
                out.println("But, he has another one!");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }
            out.println(inicialColor + "´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´´" + finalColor);
        }

        myPokemon.levelUp();
        out.println(myPokemon + " leveled up!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        myPokemon.restoreHp();
        out.println("Nurse Carol gave you a kiss and your MC´s health was restored!");
    }

    public boolean isReady() {
        return ready;
    }

    @Override
    public String toString() {
        return name;
    }
}
