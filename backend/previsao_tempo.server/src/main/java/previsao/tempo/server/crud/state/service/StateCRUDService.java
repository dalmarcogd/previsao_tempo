package previsao.tempo.server.crud.state.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import previsao.tempo.server.crud.base.repository.AbstractCRUDRepository;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.state.repository.StateCRUDRepository;
import previsao.tempo.server.model.base.BaseDTO;
import previsao.tempo.server.model.state.StateDTO;
import previsao.tempo.server.model.state.StateEntity;

/**
 * Serviço de persistencia de {@link StateEntity}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class StateCRUDService extends AbstractCRUDService<StateEntity, StateDTO> {

    @Autowired
    private StateCRUDRepository stateCRUDRepository;
    @Autowired
    private StateQueryService stateQueryService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractCRUDRepository<StateEntity> getCRUDRepository() {
        return stateCRUDRepository;
    }

    /**
     * Converte os dados do dto para entity.
     * @param dto    - {@link BaseDTO}
     * @param entity - {@link StateEntity}
     * @return {@link StateEntity}
     */
    @Override
    public StateEntity convertToEntity(StateDTO dto, StateEntity entity) {
    	entity.setId(dto.getId());
    	entity.setVersion(dto.getVersion());
    	entity.setName(dto.getName());
        return entity;
    }

    /**
     * Converte os dados do entity para dto.
     * @param dto    - {@link BaseDTO}
     * @param entity - {@link StateEntity}
     * @return {@link UserStateDTO}
     */
    @Override
    public StateDTO convertToDTO(StateEntity entity, StateDTO dto) {
    	dto.setId(entity.getId());
    	dto.setVersion(entity.getVersion());
    	dto.setName(entity.getName());
        return dto;
    }

    /**
     * Cria uma entidade nova e vazia.
     * @return {@link StateEntity}
     */
    @Override
    public StateEntity createEmptyEntity() {
        return new StateEntity();
    }

    @Override
    public StateDTO createEmptyDTO() {
        return new StateDTO();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void internalValidate(StateEntity entity) {

	}
}