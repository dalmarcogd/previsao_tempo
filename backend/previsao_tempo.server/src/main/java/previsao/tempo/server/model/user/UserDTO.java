package previsao.tempo.server.model.user;

import previsao.tempo.server.model.base.BaseDTO;

/**
 * Representa um {@link UserEntity}.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class UserDTO extends BaseDTO {

    private Long code;
    private String name;
    private String email;
    private String username;
    private String password;

    /**
     * Retorna o code - {@link Long}
     * @return {@link Long}
     */
    public Long getCode() {
        return code;
    }

    /**
     * Atribui ao code - {@link Long}
     * @param code - {@link Long}
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * Retorna o name - {@link String}
     * @return {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Atribui ao name - {@link String}
     * @param name - {@link String}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retorna o email - {@link String}
     * @return {@link String}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Atribui ao email - {@link String}
     * @param email - {@link String}
     */
    public void setEmail(String email) {
        this.email = email;
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