package previsao.tempo.server.model.user;

import java.io.Serializable;

/**
 * Credenciais utilizadas para validar.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class Credentials implements Serializable {

    private String username;
    private String password;

    /**
     * Retorna o username - {@link String}
     * @return {@link String}
     */
    public String getUsername() {
        return username;
    }

    /**
     * Atribui ao username - {@link String}
     * @param username - {@link String}
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retorna o password - {@link String}
     * @return {@link String}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Atribui ao password - {@link String}
     * @param password - {@link String}
     */
    public void setPassword(String password) {
        this.password = password;
    }
}