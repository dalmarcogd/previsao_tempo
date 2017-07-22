package previsao.tempo.server.model.user;

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
import javax.validation.constraints.NotNull;

import previsao.tempo.server.model.base.BaseEntity;
import previsao.tempo.server.model.usercity.UserCityEntity;

/**
 * Representa um usuário no sistema.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", nullable = false)
	private Long id;

	@NotNull
	@Column(nullable = false, name = "user_name")
	private String name;

	@NotNull
	@Column(nullable = false, name = "user_email")
	private String email;

	@NotNull
    @Column(nullable = false, name = "user_username")
    private String username;

    @NotNull
	@Column(nullable = false, name = "user_password")
	private String password;

    @OneToMany(cascade = CascadeType.REFRESH, targetEntity = UserCityEntity.class, fetch = FetchType.LAZY, mappedBy = UserCityEntity.USER)
    private List<UserCityEntity> userCities = new ArrayList<>();

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

    /**
	 * Retorna uma instancia de {@link List<UserCityEntity>}
	 * @return {@link List<UserCityEntity>}
	 */
	public List<UserCityEntity> getTasks() {
		return userCities;
	}

	/**
	 * Atribui um {@link List<UserCityEntity>}
	 * @param userCities - {@link List<UserCityEntity>}
	 */
	public void setTasks(List<UserCityEntity> userCities) {
		this.userCities = userCities;
	}

	/**
	 * Adiciona um {@link UserCityEntity}
	 * @param userCity - {@link UserCityEntity}
	 */
	public void addTask(UserCityEntity userCity) {
		this.userCities.add(userCity);
	}
}