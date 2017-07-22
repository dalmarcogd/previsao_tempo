package previsao.tempo.server.crud.usercity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import previsao.tempo.server.crud.base.repository.AbstractCRUDRepository;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.user.service.UserAuthenticationService;
import previsao.tempo.server.crud.user.service.UserQueryService;
import previsao.tempo.server.crud.usercity.repository.UserCityCRUDRepository;
import previsao.tempo.server.model.base.BaseDTO;
import previsao.tempo.server.model.usercity.UserCityDTO;
import previsao.tempo.server.model.usercity.UserCityEntity;

/**
 * Serviço de persistencia de {@link UserCityEntity}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class UserCityCRUDService extends AbstractCRUDService<UserCityEntity, UserCityDTO> {

    @Autowired
    private UserCityCRUDRepository userCityCRUDRepository;
    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractCRUDRepository<UserCityEntity> getCRUDRepository() {
        return userCityCRUDRepository;
    }

    /**
     * Converte os dados do dto para entity.
     * @param dto    - {@link BaseDTO}
     * @param entity - {@link UserCityEntity}
     * @return {@link UserCityEntity}
     */
    @Override
    public UserCityEntity convertToEntity(UserCityDTO dto, UserCityEntity entity) {
    	entity.setId(dto.getId());
    	entity.setVersion(dto.getVersion());
        return entity;
    }

    /**
     * Converte os dados do entity para dto.
     * @param dto    - {@link BaseDTO}
     * @param entity - {@link UserCityEntity}
     * @return {@link UserUserCityDTO}
     */
    @Override
    public UserCityDTO convertToDTO(UserCityEntity entity, UserCityDTO dto) {
    	dto.setId(entity.getId());
    	dto.setVersion(entity.getVersion());
        return dto;
    }

    /**
     * Cria uma entidade nova e vazia.
     * @return {@link UserCityEntity}
     */
    @Override
    public UserCityEntity createEmptyEntity() {
        return new UserCityEntity();
    }

    @Override
    public UserCityDTO createEmptyDTO() {
        return new UserCityDTO();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void internalValidate(UserCityEntity entity) {

	}
}