package lol.szymeko.client;

/**
 * @author Szymon on 09.12.2021
 * @project LicenseSystem
 */
public class Main {

    public static String username = "Szymeko";
    public static String license = "hui";

    public static void main(String[] args) {
        new Client().start("127.0.0.1", 2137);
    }

}
