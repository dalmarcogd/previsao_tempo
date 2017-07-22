package previsao.tempo.server.core.hibernate.service;

import java.io.Serializable;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import previsao.tempo.server.core.exception.ValidationException;
import previsao.tempo.server.core.exception.translator.TranslatorException;
import previsao.tempo.server.model.base.BaseEntity;


/**
 * Servi�o padr�o respons�vel por persistir entidades do hibernate.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class HibernateHandleSessionService {

    @Autowired
    private HibernateManagerSessionFactoryService hibernateManagerSessionFactoryService;

    /**
     * Cria ou atuliza a entidade.
     * @param entity - E
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public <E extends BaseEntity> E saveOrUpdate(E entity) throws ValidationException{
        Session session = hibernateManagerSessionFactoryService.getCurrentSession();
        try {
            session.saveOrUpdate(entity);
            entity.getId();
        } catch (Exception e) {
        	TranslatorException.translateToCRUDException(e);
        }
        // flush();
        return entity;
    }

    /**
     * Deleta a entidade.
     * @param entity - E
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public <E extends BaseEntity> void delete(E entity) throws ValidationException {
        Session session = hibernateManagerSessionFactoryService.getCurrentSession();
        try {
            session.delete(entity);
        } catch (Exception e) {
        	TranslatorException.translateToCRUDException(e);
        }
        // flush();
    }

    /**
     * Recupera a entidade que est� na sess�o do hibernate pelo id.
     * @param clazz - {@link Class} of {@link BaseEntity}
     * @param id - {@link Long}
     * @return E
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public <E extends BaseEntity> E get(Class<E> clazz, Long id) throws ValidationException {
        return get(clazz, (Serializable) id);
    }

    /**
     * Recupera a entidade que est� na sess�o do hibernate pelo id.
     * @param clazz - {@link Class} of {@link BaseEntity}
     * @param id - {@link Long}
     * @return E
     */
    @Transactional(propagation = Propagation.MANDATORY)
    private <E extends BaseEntity> E get(Class<E> clazz, Serializable id) throws ValidationException {
        Session session = hibernateManagerSessionFactoryService.getCurrentSession();
        E entity = null;
        try {
            entity = session.get(clazz, id);
        } catch (Exception e) {
            TranslatorException.translateToCRUDException(e);
        }
        return entity;
    }

    /**
     * Efetuar a sncroniza��o dos objetos com o banco de dados e limpa a sess�o do hibernate.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void flush() throws ValidationException {
        Session session = hibernateManagerSessionFactoryService.getCurrentSession();
        try {
            session.flush();
        } catch (Exception e) {
        	TranslatorException.translateToCRUDException(e);
        }
    }

    /**
     * Efetuar a sncroniza��o dos objetos com o banco de dados e limpa a sess�o do hibernate.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void flushClear() throws ValidationException {
            Session session = hibernateManagerSessionFactoryService.getCurrentSession();
        try {
            flush();
            session.clear();
        } catch (Exception e) {
        	TranslatorException.translateToCRUDException(e);
        }
    }

    /**
     * Efetuar a o lock da entidade.
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public <E extends BaseEntity> void lock(E entity) throws ValidationException {
        Session session = hibernateManagerSessionFactoryService.getCurrentSession();
        try {
            session.lock(entity, LockMode.OPTIMISTIC);
        } catch (Exception e) {
        	TranslatorException.translateToCRUDException(e);
        }
    }
}