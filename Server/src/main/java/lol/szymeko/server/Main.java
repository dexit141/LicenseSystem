package lol.szymeko.server;

import lol.szymeko.server.data.ConnectionData;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * @author Szymon on 08.12.2021
 * @project LicenseSystem
 */
public class Main {

    private static final File authFile = new File("auth.txt");

    public static void main(String[] args) {
        if (!authFile.exists())
            createFiles();

        new Server().bind();
        loadAuthData();
    }

    private static void createFiles() {
        try {
            FileWriter fileWriter = new FileWriter(authFile);
            fileWriter.write("127.0.0.1:Szymeko:hui");
            fileWriter.flush();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void loadAuthData() {
        try {
            Scanner s = new Scanner(authFile);
            while (s.hasNext()) {
                String[] split = s.next().split(":", 3);
                Server.getInstance().getAuthData().put(split[0], new ConnectionData(split[1], split[2]));
            }
            s.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
