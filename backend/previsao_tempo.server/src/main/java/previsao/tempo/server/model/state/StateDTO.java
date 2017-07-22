package previsao.tempo.server.model.state;

import previsao.tempo.server.model.base.BaseDTO;

/**
 * Representa um {@link UserCityEntity}.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class StateDTO extends BaseDTO {

	private String name;
	private String abbreviation;

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
	 * Retorna uma instancia de {@link String}
	 * @return {@link String}
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * Atribui um {@link String}
	 * @param abbreviation - {@link String}
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
}