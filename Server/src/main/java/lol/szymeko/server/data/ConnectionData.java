package lol.szymeko.server.data;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Szymon on 08.12.2021
 * @project LicenseSystem
 */
@Getter @Setter
public class ConnectionData {

    private final String user;
    private final String license;

    public ConnectionData(String user, String license) {
        this.user = user;
        this.license = license;
    }
}
