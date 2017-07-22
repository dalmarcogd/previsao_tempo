package previsao.tempo.server.crud.state.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import previsao.tempo.server.core.spring.context.ManagerInstance;
import previsao.tempo.server.crud.base.controller.AbstractCRUDController;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.state.service.StateCRUDService;
import previsao.tempo.server.model.state.StateDTO;
import previsao.tempo.server.model.state.StateEntity;

/**
 * Implementação de {@link AbstractCRUDController} para {@link StateDTO}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@RestController
@RequestMapping("/states")
public class StateCRUDController extends AbstractCRUDController<StateDTO, StateEntity> {

    private StateCRUDService userCRUDService;

    /**
     * Retorna o userCRUDService - {@link StateCRUDService}
     * @return {@link StateCRUDService}
     */
    public StateCRUDService getStateCRUDService() {
        if (userCRUDService == null) {
			userCRUDService = ManagerInstance.get(StateCRUDService.class);
		}
        return userCRUDService;
    }

    /**
     * Serviço de persistencia de {@link StateEntity}
     * @return {@link AbstractCRUDService} of {@link StateEntity}
     */
    @Override
    protected AbstractCRUDService<StateEntity, StateDTO> getService() {
        return getStateCRUDService();
    }
}