package previsao.tempo.server.model.usercity;

import previsao.tempo.server.model.base.BaseDTO;
import previsao.tempo.server.model.city.CityDTO;
import previsao.tempo.server.model.user.UserDTO;

/**
 * Representa um {@link UserCityEntity}.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class UserCityDTO extends BaseDTO {

	private UserDTO user;
	private CityDTO city;

	/**
	 * Retorna uma instancia de {@link UserDTO}
	 * @return {@link UserDTO}
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * Atribui um {@link UserDTO}
	 * @param user - {@link UserDTO}
	 */
	public void setUser(UserDTO user) {
		this.user = user;
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