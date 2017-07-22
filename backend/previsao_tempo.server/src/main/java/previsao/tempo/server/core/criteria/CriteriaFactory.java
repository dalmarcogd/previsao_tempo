package previsao.tempo.server.core.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import previsao.tempo.server.core.hibernate.service.HibernateManagerSessionFactoryService;
import previsao.tempo.server.core.spring.context.ScopeType;
import previsao.tempo.server.core.utils.ClassUtils;
import previsao.tempo.server.model.base.BaseEntity;

/**
 * F�brica respons�vel por conter m�todos para cria��o de consultas na aplica��o.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Component
@Scope(ScopeType.SINGLETON)
public final class CriteriaFactory {

    @Autowired
    private HibernateManagerSessionFactoryService hibernateManagerSessionFactoryService;

    private CriteriaBuilder criteriaBuilder;

    /**
     * Cria uma {@link CriteriaQuery} utilizando o entityClass
     * @param entityClass - {@link Class} of extends {@link BaseEntity}
     * @return CriteriaQuery with entityClass
     */
    public <B extends BaseEntity> CriteriaQuery<B> createCriteriaQuery(Class<B> entityClass) {
        return getBuilder().createQuery(entityClass);
    }

    /**
     * Cria uma {@link Criteria} utilizando o entityClass
     * @param entityClass - {@link Class} of extends {@link BaseEntity}
     * @return Criteria with entityClass
     */
    public <B extends BaseEntity> Criteria createCriteria(Class<B> entityClass) {
        return new CriteriaImpl(entityClass.getName(), ClassUtils.toAssignable(SessionImpl.class, hibernateManagerSessionFactoryService.getCurrentSession()));
    }

    /**
     * Cria uma {@link Criteria} utilizando o entityClass
     * @param entityClass - {@link Class} of extends {@link BaseEntity}
     * @param alias - {@link String} um alias
     * @return Criteria with entityClass
     */
    public <B extends BaseEntity> Criteria createCriteria(Class<B> entityClass, String alias) {
        return new CriteriaImpl(entityClass.getName(), alias, ClassUtils.toAssignable(SessionImpl.class, hibernateManagerSessionFactoryService.getCurrentSession()));
    }

    /**
     * Retorna o criteriaBuilder - {@link CriteriaBuilder}
     * @return {@link CriteriaBuilder}
     */
    public CriteriaBuilder getBuilder() {
        return criteriaBuilder;
    }
}