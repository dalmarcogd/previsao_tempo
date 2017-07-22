package previsao.tempo.server.model.user;

import java.io.Serializable;

/**
 *
 * Validador do token.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class ValidToken implements Serializable {

    private String token;

    /**
	 * Retorna uma instancia de {@link String}
	 * @return {@link String}
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Seta um {@link String}
	 * @param token - {@link String}
	 */
	public void setToken(String token) {
		this.token = token;
	}
}