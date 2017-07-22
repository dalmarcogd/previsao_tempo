package previsao.tempo.server.model.user;

/**
 * Classe que mantém o token do usuário.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class TokenDTO {

    private String token;
    private String username;

    /**
     * Retorna o token - {@link String}
     * @return {@link String}
     */
    public String getToken() {
        return token;
    }

    /**
     * Atribui ao token - {@link String}
     * @param token - {@link String}
     */
    public void setToken(String token) {
        this.token = token;
    }

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
}