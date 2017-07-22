package previsao.tempo.server.crud.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import previsao.tempo.server.core.spring.context.ManagerInstance;
import previsao.tempo.server.crud.base.controller.AbstractCRUDController;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.user.service.UserCRUDService;
import previsao.tempo.server.model.user.UserDTO;
import previsao.tempo.server.model.user.UserEntity;

/**
 * Implementação de {@link AbstractCRUDController} para {@link UserCityDTO}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@RestController
@RequestMapping("/users")
public class UserCRUDController extends AbstractCRUDController<UserDTO, UserEntity> {

    private UserCRUDService userCRUDService;

    /**
     * Retorna o userCRUDService - {@link UserCityCRUDService}
     * @return {@link UserCityCRUDService}
     */
    public UserCRUDService getUserCRUDService() {
        if (userCRUDService == null) {
			userCRUDService = ManagerInstance.get(UserCRUDService.class);
		}
        return userCRUDService;
    }

    /**
     * Serviço de persistencia de {@link UserEntity}
     * @return {@link AbstractCRUDService} of {@link UserEntity}
     */
    @Override
    protected AbstractCRUDService<UserEntity, UserDTO> getService() {
        return getUserCRUDService();
    }
}