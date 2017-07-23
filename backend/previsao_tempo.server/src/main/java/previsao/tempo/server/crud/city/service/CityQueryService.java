package previsao.tempo.server.crud.city.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import previsao.tempo.server.core.criteria.CriteriaFactory;
import previsao.tempo.server.model.city.CityDTO;
import previsao.tempo.server.model.city.CityEntity;

/**
 * Serviço de consulta de cidades.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class CityQueryService {

    @Autowired
    private CriteriaFactory criteriaFactory;
    @Autowired
    private CityCRUDService cityCRUDService;

    @Transactional(readOnly = true)
    public List<CityEntity> searchCityEntityByName(String name, Long idState) {
        Criteria q = criteriaFactory.createCriteria(CityEntity.class);
        q.add(Restrictions.like(CityEntity.NAME, name, MatchMode.START));
        if (idState != null) {
        	q.createAlias(CityEntity.STATE, CityEntity.STATE);
        	q.add(Restrictions.eq("state.id", idState));
		}
        return q.list();
    }

    @Transactional(readOnly = true)
    public List<CityDTO> searchCityDTOByName(String name, Long idState) {
        return cityCRUDService.convertAllToDTO(searchCityEntityByName(name, idState));
    }
}