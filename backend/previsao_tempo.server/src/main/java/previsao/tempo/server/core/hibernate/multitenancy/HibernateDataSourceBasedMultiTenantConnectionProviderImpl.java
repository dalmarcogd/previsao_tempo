package previsao.tempo.server.core.hibernate.multitenancy;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import previsao.tempo.server.core.hibernate.config.HibernateConfig;

/**
 * Implementa��o de {@link AbstractDataSourceBasedMultiTenantConnectionProviderImpl} que retorna a conex�o correspondente ao tenantIdentifier.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class HibernateDataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private DriverManagerDataSource defaultDataSource;

    public HibernateDataSourceBasedMultiTenantConnectionProviderImpl(){
        defaultDataSource = new DriverManagerDataSource();
        defaultDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        defaultDataSource.setUrl("jdbc:mysql://localhost:3306/?useLegacyDatetimeCode=false&serverTimezone=UTC");
        defaultDataSource.setUsername("root");
        defaultDataSource.setPassword("root");
        defaultDataSource.setConnectionProperties(HibernateConfig.getDefaultProperties());
    }


    @Override
    protected DataSource selectAnyDataSource() {
        return defaultDataSource;
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return defaultDataSource;
    }
}
