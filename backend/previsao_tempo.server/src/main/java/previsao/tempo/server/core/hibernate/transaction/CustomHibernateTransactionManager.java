package previsao.tempo.server.core.hibernate.transaction;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

/**
 * Implementa��o de {@link HibernateTransactionManager} para quer seja poss�vel configurar para que n�o autodetectDatasource.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class CustomHibernateTransactionManager extends HibernateTransactionManager {

    /**
     * Create a new HibernateTransactionManager instance.
     * @param sessionFactory SessionFactory to manage transactions for
     */
    public CustomHibernateTransactionManager(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
        this.setAutodetectDataSource(false);
        afterPropertiesSet();
    }
}
