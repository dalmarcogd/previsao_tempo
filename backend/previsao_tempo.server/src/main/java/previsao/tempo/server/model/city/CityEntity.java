package previsao.tempo.server.model.city;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import previsao.tempo.server.model.base.BaseEntity;
import previsao.tempo.server.model.state.StateEntity;
import previsao.tempo.server.model.usercity.UserCityEntity;

/**
 * Define uma tarfe da aplicação.
 *
 * @autor Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Entity
@Table(name = "city")
public class CityEntity extends BaseEntity {

	public static final String NAME = "name";
	public static final String STATE = "state";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "city_id", nullable = false)
	private Long id;

	@NotNull
	@Column(nullable = false, name = "city_name")
	private String name;

	@NotNull
    @ManyToOne(targetEntity = StateEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_state_city"), nullable = false, name = "city_state_id")
    private StateEntity state;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

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
	 * Retorna uma instancia de {@link UserCityEntity}
	 * @return {@link UserCityEntity}
	 */
	public StateEntity getState() {
		return state;
	}

	/**
	 * Atribui um {@link UserCityEntity}
	 * @param state - {@link UserCityEntity}
	 */
	public void setState(StateEntity state) {
		this.state = state;
	}
}