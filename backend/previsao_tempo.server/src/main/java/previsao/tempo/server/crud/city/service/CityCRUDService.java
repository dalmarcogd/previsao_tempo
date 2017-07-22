package previsao.tempo.server.crud.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import previsao.tempo.server.crud.base.repository.AbstractCRUDRepository;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.city.repository.CityCRUDRepository;
import previsao.tempo.server.crud.state.service.StateCRUDService;
import previsao.tempo.server.model.base.BaseDTO;
import previsao.tempo.server.model.city.CityDTO;
import previsao.tempo.server.model.city.CityEntity;
import previsao.tempo.server.model.usercity.UserCityDTO;

/**
 * Serviço de persistencia de {@link CityEntity}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class CityCRUDService extends AbstractCRUDService<CityEntity, CityDTO> {

    @Autowired
    private CityCRUDRepository cityCRUDRepository;
    @Autowired
    private CityQueryService cityQueryService;
    @Autowired
    private StateCRUDService stateCRUDService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractCRUDRepository<CityEntity> getCRUDRepository() {
        return cityCRUDRepository;
    }

    /**
     * Converte os dados do dto para entity.
     * @param dto    - {@link BaseDTO}
     * @param entity - {@link CityEntity}
     * @return {@link CityEntity}
     */
    @Override
    public CityEntity convertToEntity(CityDTO dto, CityEntity entity) {
    	entity.setId(dto.getId());
    	entity.setVersion(dto.getVersion());
    	entity.setName(entity.getName());
    	if (dto.getState() != null) {
    		entity.setState(stateCRUDService.getEntity(dto.getState().getId()));
		}
        return entity;
    }

    /**
     * Converte os dados do entity para dto.
     * @param dto    - {@link BaseDTO}
     * @param entity - {@link CityEntity}
     * @return {@link UserCityDTO}
     */
    @Override
    public CityDTO convertToDTO(CityEntity entity, CityDTO dto) {
    	dto.setId(entity.getId());
    	dto.setVersion(entity.getVersion());
    	dto.setName(entity.getName());
    	if (entity.getState() != null) {
    		dto.setState(stateCRUDService.getDTO(entity.getState().getId()));
		}
        return dto;
    }

    /**
     * Cria uma entidade nova e vazia.
     * @return {@link CityEntity}
     */
    @Override
    public CityEntity createEmptyEntity() {
        return new CityEntity();
    }

    @Override
    public CityDTO createEmptyDTO() {
        return new CityDTO();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void internalValidate(CityEntity entity) {

	}
}