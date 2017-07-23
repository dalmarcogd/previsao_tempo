package previsao.tempo.server.crud.usercity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import previsao.tempo.server.crud.base.repository.AbstractCRUDRepository;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.city.service.CityCRUDService;
import previsao.tempo.server.crud.user.service.UserCRUDService;
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
    private CityCRUDService cityCRUDService;
    @Autowired
    private UserCRUDService userCRUDService;
    @Autowired
    private UserQueryService userQueryService;

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
    	if (dto.getCity() != null) {
    		entity.setCity(cityCRUDService.getEntity(dto.getCity().getId()));
		}
    	if (dto.getUsername() != null) {
    		entity.setUser(userQueryService.getUserByUsername(dto.getUsername()));
		}
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
    	if (entity.getCity() != null) {
    		dto.setCity(cityCRUDService.getDTO(entity.getCity().getId()));
		}
    	if (entity.getUser() != null) {
    		dto.setUsername(entity.getUser().getUsername());
		}
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