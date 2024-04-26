package io.codeforall.javatars;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static ArrayList<Player> playerList = new ArrayList<>();
    private ServerSocket serverSocket;
    private ExecutorService cachePool;
    private int portNumber = 9090;

    public void start() {
        try {
            connection();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finalFight();
        finalMessage();
    }

    public void connection() throws IOException {

        serverSocket = new ServerSocket(portNumber);
        Socket clientSocket;

        cachePool = Executors.newCachedThreadPool();

        for (int i = 0; i < 2; i++) {
            clientSocket = serverSocket.accept();
            playerList.add(new Player(clientSocket));
            cachePool.submit(playerList.get(i));
        }

        while (!playerList.get(0).isReady() || !playerList.get(1).isReady()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        cachePool.shutdown();
    }

    public void finalFight() {

        while (playerList.get(0).myPokemon.getHp() > 0 && playerList.get(1).myPokemon.getHp() > 0) {
            playerList.get(0).attack(playerList.get(1).myPokemon);
            if (playerList.get(1).myPokemon.getHp() <= 0) {
                for (Player player : Server.playerList) {
                    player.getOut().println(playerList.get(1).myPokemon + " has fainted.");
                }
                break;
            }
            playerList.get(1).attack(playerList.get(0).myPokemon);
            if (playerList.get(0).myPokemon.getHp() <= 0) {
                for (Player player : Server.playerList) {
                    player.getOut().println(playerList.get(0).myPokemon + " has fainted.");
                }
                break;
            }
        }
    }

    public void finalMessage(){
        if (playerList.get(1).myPokemon.getHp() <= 0) {

            playerList.get(0).getOut().println("\n" +
                    "██╗░░░██╗░█████╗░██╗░░░██╗  ░██╗░░░░░░░██╗░█████╗░███╗░░██╗\n" +
                    "╚██╗░██╔╝██╔══██╗██║░░░██║  ░██║░░██╗░░██║██╔══██╗████╗░██║\n" +
                    "░╚████╔╝░██║░░██║██║░░░██║  ░╚██╗████╗██╔╝██║░░██║██╔██╗██║\n" +
                    "░░╚██╔╝░░██║░░██║██║░░░██║  ░░████╔═████║░██║░░██║██║╚████║\n" +
                    "░░░██║░░░╚█████╔╝╚██████╔╝  ░░╚██╔╝░╚██╔╝░╚█████╔╝██║░╚███║\n" +
                    "░░░╚═╝░░░░╚════╝░░╚═════╝░  ░░░╚═╝░░░╚═╝░░░╚════╝░╚═╝░░╚══╝");
            playerList.get(1).getOut().println("\n" +
                    "█▄█ █▀█ █░█   █░░ █▀█ █▀ ▀█▀\n" +
                    "░█░ █▄█ █▄█   █▄▄ █▄█ ▄█ ░█░");
            return;
        }
        playerList.get(1).getOut().println("\n" +
                "██╗░░░██╗░█████╗░██╗░░░██╗  ░██╗░░░░░░░██╗░█████╗░███╗░░██╗\n" +
                "╚██╗░██╔╝██╔══██╗██║░░░██║  ░██║░░██╗░░██║██╔══██╗████╗░██║\n" +
                "░╚████╔╝░██║░░██║██║░░░██║  ░╚██╗████╗██╔╝██║░░██║██╔██╗██║\n" +
                "░░╚██╔╝░░██║░░██║██║░░░██║  ░░████╔═████║░██║░░██║██║╚████║\n" +
                "░░░██║░░░╚█████╔╝╚██████╔╝  ░░╚██╔╝░╚██╔╝░╚█████╔╝██║░╚███║\n" +
                "░░░╚═╝░░░░╚════╝░░╚═════╝░  ░░░╚═╝░░░╚═╝░░░╚════╝░╚═╝░░╚══╝");
        playerList.get(0).getOut().println("\n" +
                "█▄█ █▀█ █░█   █░░ █▀█ █▀ ▀█▀\n" +
                "░█░ █▄█ █▄█   █▄▄ █▄█ ▄█ ░█░");
    }

    public static void main(String[] args)  {

        Server game1 = new Server();
        game1.start();

    }
}
