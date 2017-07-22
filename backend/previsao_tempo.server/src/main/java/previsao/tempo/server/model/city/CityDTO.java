package previsao.tempo.server.model.city;

import previsao.tempo.server.model.base.BaseDTO;
import previsao.tempo.server.model.state.StateDTO;

/**
 * Representa um {@link CityEntity}.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class CityDTO extends BaseDTO {

	private String name;
    private StateDTO state;

    /**
	 * Retorna uma instancia de {@link String}
	 * @return {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Atribui um {@link String}
	 * @param name - {@link String}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retorna uma instancia de {@link UserCityDTO}
	 * @return {@link UserCityDTO}
	 */
	public StateDTO getState() {
		return state;
	}

	/**
	 * Atribui um {@link UserCityDTO}
	 * @param state - {@link UserCityDTO}
	 */
	public void setState(StateDTO state) {
		this.state = state;
	}
}