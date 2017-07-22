package previsao.tempo.server.core.spring.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Gerenciador das configura��es do contexto de spring, mant�m um referencia de {@link ConfigurableApplicationContext}
 * para que seja poss�vel recuperar as instancias dos @{@link Service}, @{@link Component}, @{@link Repository}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class ManagerInstance {

    private static ConfigurableApplicationContext configurableApplicationContext;

    /**
     * Retorna a instancia do tipo solicitado.
     * @return bean - X
     */
    public static <X> X get(Class<X> classBean) {
        X bean = configurableApplicationContext.getBean(classBean);
        if (bean == null) {
            throw new SpringRuntimeException("O bean(" +classBean.getName()+ ") n�o foi encontrado no contexto do spring.");
        }
        return bean;
    }

    /**
     * Retorna a instancia do tipo solicitado.
     * @return bean - X
     */
    public static <X> X get(String alias, Class<X> classBean) {
        X bean = configurableApplicationContext.getBean(alias, classBean);
        if (bean == null) {
            throw new SpringRuntimeException("O bean(" +classBean.getName()+ ") alias("+alias+") n�o foi encontrado no contexto do spring.");
        }
        return bean;
    }

    /**
     * Retorna a instancia do tipo solicitado.
     * @return bean - X
     */
    public static <X> List<X> getAll(Class<X> classBean) {
        Map<String, X> beansOfType = configurableApplicationContext.getBeansOfType(classBean);
        if (beansOfType == null || beansOfType.isEmpty()) {
            throw new SpringRuntimeException("Os beans de (" +classBean.getName()+ ") n�o foram encontrados no contexto do spring.");
        }
        return new ArrayList<>(beansOfType.values());
    }

    /**
     * Atribui ao configurableApplicationContext - {@link ConfigurableApplicationContext}
     * @param configurableApplicationContext - {@link ConfigurableApplicationContext}
     */
    public static void setConfigurableApplicationContext(ConfigurableApplicationContext configurableApplicationContext) {
        ManagerInstance.configurableApplicationContext = configurableApplicationContext;
    }
}