package previsao.tempo.server.crud.city.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import previsao.tempo.server.core.spring.context.ManagerInstance;
import previsao.tempo.server.crud.base.controller.AbstractCRUDController;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.crud.city.service.CityCRUDService;
import previsao.tempo.server.crud.city.service.CityQueryService;
import previsao.tempo.server.model.city.CityDTO;
import previsao.tempo.server.model.city.CityEntity;
import previsao.tempo.server.model.usercity.UserCityDTO;

/**
 * Implementação de {@link AbstractCRUDController} para {@link UserCityDTO}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@RestController
@RequestMapping("/cities")
public class CityCRUDController extends AbstractCRUDController<CityDTO, CityEntity> {

    private CityCRUDService cityCRUDService;
    private CityQueryService cityQueryService;

    /**
     * Retorna o userCRUDService - {@link CityCRUDService}
     * @return {@link CityCRUDService}
     */
    public CityCRUDService getCityCRUDService() {
        if (cityCRUDService == null) {
			cityCRUDService = ManagerInstance.get(CityCRUDService.class);
		}
        return cityCRUDService;
    }

    /**
     * Retorna o userQueryService - {@link CityQueryService}
     * @return {@link CityQueryService}
     */
    public CityQueryService getCityQueryService() {
        if (cityQueryService == null) {
			cityQueryService = ManagerInstance.get(CityQueryService.class);
		}
        return cityQueryService;
    }

    /**
     * Serviço de persistencia de {@link CityEntity}
     * @return {@link AbstractCRUDService} of {@link CityEntity}
     */
    @Override
    protected AbstractCRUDService<CityEntity, CityDTO> getService() {
        return getCityCRUDService();
    }

    /**
     * Disponibiliza uma forma de leitura da entidade a partir do id.
     * @param id - {@link Long}
     * @return {@link ResponseEntity}
     */
	@GetMapping(path="search", params={"query", "idState"})
    public @ResponseBody ResponseEntity<?> read(@RequestParam(value = "query") String query, @RequestParam(value = "idState") Long idState){
        return ResponseEntity.ok(getCityQueryService().searchCityDTOByName(query, idState));
    }
}