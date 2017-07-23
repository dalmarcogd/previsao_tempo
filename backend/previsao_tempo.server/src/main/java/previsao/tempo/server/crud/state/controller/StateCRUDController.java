package previsao.tempo.server.crud.state.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import previsao.tempo.server.core.spring.context.ManagerInstance;
import previsao.tempo.server.crud.base.controller.AbstractCRUDController;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.state.service.StateCRUDService;
import previsao.tempo.server.crud.state.service.StateQueryService;
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

    private StateCRUDService stateCRUDService;
    private StateQueryService stateQueryService;

    /**
     * Retorna o stateCRUDService - {@link StateCRUDService}
     * @return {@link StateCRUDService}
     */
    public StateCRUDService getStateCRUDService() {
        if (stateCRUDService == null) {
			stateCRUDService = ManagerInstance.get(StateCRUDService.class);
		}
        return stateCRUDService;
    }

    /**
	 * Retorna uma instancia de {@link StateQueryService}
	 * @return {@link StateQueryService}
	 */
	public StateQueryService getStateQueryService() {
		if (stateQueryService == null) {
			stateQueryService = ManagerInstance.get(StateQueryService.class);
		}
		return stateQueryService;
	}

    /**
     * Serviço de persistencia de {@link StateEntity}
     * @return {@link AbstractCRUDService} of {@link StateEntity}
     */
    @Override
    protected AbstractCRUDService<StateEntity, StateDTO> getService() {
        return getStateCRUDService();
    }

    /**
     * Disponibiliza uma forma de leitura da entidade a partir do id.
     * @param id - {@link Long}
     * @return {@link ResponseEntity}
     */
	@GetMapping(params={"query"})
    public @ResponseBody ResponseEntity<?> read(@RequestParam(value = "query") String query){
        return ResponseEntity.ok(getStateQueryService().searchStateDTOByName(query));
    }
}