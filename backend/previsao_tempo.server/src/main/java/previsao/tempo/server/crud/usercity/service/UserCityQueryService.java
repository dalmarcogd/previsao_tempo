package previsao.tempo.server.crud.usercity.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import previsao.tempo.server.core.criteria.CriteriaFactory;
import previsao.tempo.server.model.usercity.UserCityEntity;

/**
 * Serviço de consulta de cidades.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class UserCityQueryService {

    @Autowired
    private CriteriaFactory criteriaFactory;

    @Transactional(readOnly = true)
    public List<UserCityEntity> getTasksByUser(String username) {
        Criteria q = criteriaFactory.createCriteria(UserCityEntity.class);
        q.createAlias(UserCityEntity.USER, UserCityEntity.USER);
        q.add(Restrictions.eq("user.username", username));
        return q.list();
    }
}