package previsao.tempo.server.crud.base.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import previsao.tempo.server.core.criteria.CriteriaFactory;
import previsao.tempo.server.core.exception.ValidationException;
import previsao.tempo.server.core.exception.crud.ValidationCRUDException;
import previsao.tempo.server.core.hibernate.service.HibernateHandleSessionService;
import previsao.tempo.server.core.spring.context.ManagerInstance;
import previsao.tempo.server.core.utils.ClassUtils;
import previsao.tempo.server.model.base.BaseEntity;

/**
 * Classe base para implementa��es de CRUD repositories.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 *
 * @param <E> - Implementa��o de {@link BaseEntity}
 */
public abstract class AbstractCRUDRepository<E extends BaseEntity> {

    private Logger log = LogManager.getLogger();

    @Autowired
    private HibernateHandleSessionService hibernateHandleSessionService;

    private CriteriaFactory criteriaFactory;

    /**
     * Retorna o criteriaFactory - {@link CriteriaFactory}
     * @return {@link CriteriaFactory}
     */
    private CriteriaFactory getCriteriaFactory() {
        if (criteriaFactory == null) {
			criteriaFactory = ManagerInstance.get(CriteriaFactory.class);
		}
        return criteriaFactory;
    }

    /**
     * Salva a entidade e retorna a entidade salva.
     * @param entity - {@link E}
     * @return {@link E} - salvo.
     * @throws ValidationException
     */
    @Transactional(rollbackFor = Throwable.class)
    public E save(E entity) throws ValidationException {
        return hibernateHandleSessionService.saveOrUpdate(entity);
    }

    /**
     * Busca a entidade a partir do id.
     * @param id - {@link E#getId()}
     * @return {@link E} - salvo ou null caso n�o encontrar.
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public E get(Long id) {
        try {
            return (E) hibernateHandleSessionService.get(ClassUtils.getGenericType(getClass(), BaseEntity.class), id);
        } catch (ValidationException e) {
            log.error(e);
        }
        return null;
    }

    /**
     * Busca todas as entidades.
     * @return {@link List} of {@link E}.
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public List<E> getAll() {
        return getCriteriaFactory().createCriteria(ClassUtils.getGenericType(getClass(), BaseEntity.class)).list();
    }

    /**
     * Busca todas as entidades, aplicando {@link Criterion}.
     * @return {@link List} of {@link E}.
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public List<E> getAll(Criterion... criterions) {
        Criteria criteria = getCriteriaFactory().createCriteria(ClassUtils.getGenericType(getClass(), BaseEntity.class));
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria.list();
    }

    /**
     * Busca a entidade a partir do id.
     * @param criterions - {@link Criterion}[]
     * @return {@link List} of {@link E}
     * @throws ValidationCRUDException dispara exce��o caso encontre mais de um objeto.
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public E getUnique(Criterion... criterions) throws ValidationCRUDException {
        Criteria criteria = getCriteriaFactory().createCriteria(ClassUtils.getGenericType(getClass(), BaseEntity.class));
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return (E) criteria.uniqueResult();
    }

    /**
     * Deleta a entidade especificado.
     * @param entity - {@link E}
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(E entity) throws ValidationException {
        hibernateHandleSessionService.delete(entity);
    }

    /**
     * Realiza o lock na entidade impedindo altera��es.
     * @param entity - {@link E}
     */
    @Transactional(rollbackFor = Throwable.class)
    public void lock(E entity) throws ValidationException {
        hibernateHandleSessionService.lock(entity);
    }
}