package previsao.tempo.server.crud.user.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import previsao.tempo.server.core.criteria.CriteriaFactory;
import previsao.tempo.server.core.utils.ClassUtils;
import previsao.tempo.server.model.user.UserEntity;

/**
 * Serviço de consulta de usuário.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class UserQueryService {

    @Autowired
    private CriteriaFactory criteriaFactory;

    @Transactional(readOnly = true)
    public UserEntity getUserByUsername(String username) {
        Criteria q = criteriaFactory.createCriteria(UserEntity.class);
        q.add(Restrictions.eq(UserEntity.USERNAME, username));
        return ClassUtils.toAssignable(UserEntity.class, q.uniqueResult());
    }
}