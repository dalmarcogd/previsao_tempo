package previsao.tempo.server.model.usercity;

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
import previsao.tempo.server.model.city.CityEntity;
import previsao.tempo.server.model.user.UserEntity;

/**
 * Representa o relacionamento das cidades com o usuário.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Entity
@Table(name = "user_city")
public class UserCityEntity extends BaseEntity {

    public static final String CITY = "city";
    public static final String USER = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_city_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(targetEntity = CityEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_usercity_city"), nullable = false, name = "city_id")
    private CityEntity city;

    @NotNull
    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_usercity_user"), nullable = false, name = "user_id")
    private UserEntity user;

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
	 * Retorna uma instancia de {@link CityEntity}
	 * @return {@link CityEntity}
	 */
	public CityEntity getCity() {
		return city;
	}

	/**
	 * Atribui um {@link CityEntity}
	 * @param city - {@link CityEntity}
	 */
	public void setCity(CityEntity city) {
		this.city = city;
	}
}