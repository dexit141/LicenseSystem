package lol.szymeko.server;

import lol.szymeko.server.data.ConnectionData;

/**
 * @author Szymon on 08.12.2021
 * @project LicenseSystem
 */
public class Main {

    public static void main(String[] args) {
        new Server().bind();

        Server.getInstance().getAuthData().put("127.0.0.1", new ConnectionData("Szymeko", "hui"));
    }

}
