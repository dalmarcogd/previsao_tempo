package previsao.tempo.server.crud.city.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.WebRequest;

import previsao.tempo.server.core.spring.context.ManagerInstance;
import previsao.tempo.server.crud.base.controller.AbstractCRUDController;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.state.service.StateCRUDService;
import previsao.tempo.server.crud.state.service.StateQueryService;
import previsao.tempo.server.crud.usercity.service.UserCityCRUDService;
import previsao.tempo.server.crud.usercity.service.UserCityQueryService;
import previsao.tempo.server.model.usercity.UserCityDTO;
import previsao.tempo.server.model.usercity.UserCityEntity;

/**
 * Implementação de {@link AbstractCRUDController} para {@link UserCityDTO}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@RestController
@RequestMapping("/cities")
public class CityCRUDController extends AbstractCRUDController<UserCityDTO, UserCityEntity> {

    private UserCityCRUDService userCRUDService;
    private UserCityQueryService userCityQueryService;

    /**
     * Retorna o userCRUDService - {@link StateCRUDService}
     * @return {@link StateCRUDService}
     */
    public UserCityCRUDService getUserCityCRUDService() {
        if (userCRUDService == null) {
			userCRUDService = ManagerInstance.get(UserCityCRUDService.class);
		}
        return userCRUDService;
    }

    /**
	 * Retorna uma instancia de {@link StateQueryService}
	 * @return {@link StateQueryService}
	 */
	public UserCityQueryService getTaskQueryService() {
		if (userCityQueryService == null) {
			userCityQueryService = ManagerInstance.get(UserCityQueryService.class);
		}
		return userCityQueryService;
	}

    /**
     * Serviço de persistencia de {@link UserCityEntity}
     * @return {@link AbstractCRUDService} of {@link UserCityEntity}
     */
    @Override
    protected AbstractCRUDService<UserCityEntity, UserCityDTO> getService() {
        return getUserCityCRUDService();
    }

    /**
     * Disponibiliza uma forma de leitura da entidade a partir do id.
     * @param id - {@link Long}
     * @return {@link ResponseEntity}
     */
	@GetMapping(params={"username"})
    public @ResponseBody ResponseEntity<?> read(@RequestParam(value = "username") String username){
		RequestContextHolder.getRequestAttributes().setAttribute("username", username, WebRequest.SCOPE_REQUEST);
        return ResponseEntity.ok(getService().convertAllToDTO(getTaskQueryService().getTasksByUser(username)));
    }
}