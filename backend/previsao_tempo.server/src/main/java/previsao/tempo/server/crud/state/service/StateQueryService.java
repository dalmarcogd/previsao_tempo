package previsao.tempo.server.crud.state.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import previsao.tempo.server.core.criteria.CriteriaFactory;
import previsao.tempo.server.model.state.StateDTO;
import previsao.tempo.server.model.state.StateEntity;

/**
 * Serviço de consulta de cidades.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class StateQueryService {

    @Autowired
    private CriteriaFactory criteriaFactory;
    @Autowired
    private StateCRUDService stateCRUDService;

    @Transactional(readOnly = true)
    public List<StateEntity> searchStateEntityByName(String name) {
        Criteria q = criteriaFactory.createCriteria(StateEntity.class);
        q.add(Restrictions.like(StateEntity.NAME, name, MatchMode.START));
        return q.list();
    }

    @Transactional(readOnly = true)
    public List<StateDTO> searchStateDTOByName(String name) {
        return stateCRUDService.convertAllToDTO(searchStateEntityByName(name));
    }
}