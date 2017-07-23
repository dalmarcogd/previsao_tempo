package previsao.tempo.server.model.usercity;

import previsao.tempo.server.model.base.BaseDTO;
import previsao.tempo.server.model.city.CityDTO;

/**
 * Representa um {@link UserCityEntity}.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class UserCityDTO extends BaseDTO {

	private String username;
	private CityDTO city;

	/**
	 * Retorna uma instancia de {@link String}
	 * @return {@link String}
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Atribui um {@link String}
	 * @param username - {@link String}
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Retorna uma instancia de {@link CityDTO}
	 * @return {@link CityDTO}
	 */
	public CityDTO getCity() {
		return city;
	}

	/**
	 * Atribui um {@link CityDTO}
	 * @param city - {@link CityDTO}
	 */
	public void setCity(CityDTO city) {
		this.city = city;
	}
}