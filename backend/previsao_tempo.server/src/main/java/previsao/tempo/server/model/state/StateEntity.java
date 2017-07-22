package previsao.tempo.server.model.state;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import previsao.tempo.server.model.base.BaseEntity;
import previsao.tempo.server.model.city.CityEntity;

/**
 * Representa um estado para a aplicação.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Entity
@Table(name = "state")
public class StateEntity extends BaseEntity {

    public static final String CODE = "code";
    public static final String NAME = "name";
    public static final String ABBREVIATION = "abbreviation";
    public static final String CITIES = "cities";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id", nullable = false)
    private Long id;

    @Column(name = "state_name")
    private String name;

    @Column(name = "state_abbreviation")
    private String abbreviation;

    @OneToMany(cascade = CascadeType.REFRESH,targetEntity = CityEntity.class, fetch = FetchType.LAZY, mappedBy = CityEntity.STATE)
    private List<CityEntity> cities = new ArrayList<>();

    /**
     * Retorna o id - {@link Long}
     * @return {@link Long}
     */
    @Override
	public Long getId() {
        return id;
    }

    /**
     * Atribui ao id - {@link Long}
     * @param id - {@link Long}
     */
    @Override
	public void setId(Long id) {
        this.id = id;
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
     * Retorna o abbreviation - {@link String}
     * @return {@link String}
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Atribui ao abbreviation - {@link String}
     * @param abbreviation - {@link String}
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Retorna o cities - {@link List<CityEntity>}
     * @return {@link List<CityEntity>}
     */
    public List<CityEntity> getCities() {
        return cities;
    }

    /**
     * Atribui ao cities - {@link List<CityEntity>}
     * @param cities - {@link List<CityEntity>}
     */
    public void setCities(List<CityEntity> cities) {
        this.cities = cities;
    }
}