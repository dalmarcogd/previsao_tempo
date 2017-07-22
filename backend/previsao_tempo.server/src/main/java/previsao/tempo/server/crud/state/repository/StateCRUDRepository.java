package previsao.tempo.server.crud.state.repository;

import org.springframework.stereotype.Repository;

import previsao.tempo.server.crud.base.repository.AbstractCRUDRepository;
import previsao.tempo.server.model.state.StateEntity;


/**
 * Respositório de {@link StateEntity}.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Repository
public class StateCRUDRepository extends AbstractCRUDRepository<StateEntity> {
}