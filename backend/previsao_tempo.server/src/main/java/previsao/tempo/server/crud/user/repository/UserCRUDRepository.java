package previsao.tempo.server.crud.user.repository;

import org.springframework.stereotype.Repository;

import previsao.tempo.server.crud.base.repository.AbstractCRUDRepository;
import previsao.tempo.server.model.user.UserEntity;

/**
 * Respositório de {@link UserEntity}.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Repository
public class UserCRUDRepository extends AbstractCRUDRepository<UserEntity> {
}