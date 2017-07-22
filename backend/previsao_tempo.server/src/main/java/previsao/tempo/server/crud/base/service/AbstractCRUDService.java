package previsao.tempo.server.crud.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import previsao.tempo.server.core.exception.ValidationException;
import previsao.tempo.server.core.exception.crud.ValidationCRUDException;
import previsao.tempo.server.crud.base.repository.AbstractCRUDRepository;
import previsao.tempo.server.model.base.BaseDTO;
import previsao.tempo.server.model.base.BaseEntity;

/**
 * Abstra��o dos servi�os que disponibilizam formas de salvar implementa��es de {@link BaseEntity}
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 *
 * @param <E> - Implementa��o de {@link BaseEntity}
 * @param <O> - Implementa��o de {@link BaseDTO}
 */
public abstract class AbstractCRUDService<E extends BaseEntity, O extends BaseDTO> {

    /**
     * Salva a entidade e retorna a entidade salva.
     * @param entity - {@link E}
     * @return {@link E} - salvo.
     */
    @Transactional(rollbackFor = Throwable.class)
    public E save(E entity) throws ValidationException {
        return getCRUDRepository().save(entity);
    }

    /**
     * Salva a entidade e retorna a entidade salva.
     * @param dto - {@link O}
     * @return {@link E} - salvo.
     */
    @Transactional(rollbackFor = Throwable.class)
    public O save(O dto) throws ValidationException {
        return convertToDTO(getCRUDRepository().save(convertToEntity(dto, createEmptyEntity())), dto);
    }

    /**
     * Busca a entidade a partir do id.
     * @param id - {@link E#getId()}
     * @return {@link E} - salvo ou null caso n�o encontrar.
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public E getEntity(Long id) {
        return getCRUDRepository().get(id);
    }

    /**
     * Busca a entidade a partir do id e converte para dto.
     * @param id - {@link E#getId()}
     * @return {@link O} - salvo ou null caso n�o encontrar.
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public O getDTO(Long id) {
    	E e = this.getEntity(id);
        return convertToDTO(e);
    }

    /**
     * Busca todas as entidades.
     * @return {@link List} of {@link E}
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    public List<E> getAll() {
        return getCRUDRepository().getAll();
    }

    /**
     * Deleta a entidade especificado.
     * @param entity - {@link E}
     * @return {@link E} - salvo.
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(E entity) throws ValidationException {
        getCRUDRepository().delete(entity);
    }

    /**
     * Deleta a entidade com o id especificado.
     * @param id - {@link E#getId()}
     * @return {@link E} - salvo.
     */
    @Transactional(rollbackFor = Throwable.class)
    public void deleteById(Long id) throws ValidationException {
        delete(getEntity(id));
    }

    /**
     * Valida a entidade.
     * @param entity - {@link E}
     * @throws ValidationCRUDException
     */
    @Transactional(rollbackFor = Throwable.class)
    public void validate(E entity) throws ValidationCRUDException {
        internalValidate(entity);
    }

    /**
	 * Aplica valida��es.
	 * @return um {@link void}
	 * @param um {@link AbstractCRUDService}
	 */
	protected abstract void internalValidate(E entity);

	/**
     * Retorna a implementa��o do reposit�rio para {@link E}
     * @return {@link AbstractCRUDRepository} of {@link E}
     */
    protected abstract AbstractCRUDRepository<E> getCRUDRepository();

    /**
     * Cria uma entidade nova e vazia.
     * @return {@link E}
     */
    public abstract E createEmptyEntity();

    /**
     * Cria um dto novo e vazio.
     * @return {@link O}
     */
    public abstract O createEmptyDTO();

    /**
     * Converte os dados do dto para entity.
     * @param  dto - {@link O}
     * @param  entity  - {@link E}
     * @return {@link E}
     */
    public abstract E convertToEntity(O dto, E entity);

    /**
     * Converte os dados do dto para entity.
     * @param  dto - {@link O}
     * @return {@link E}
     */
    public E convertToEntity(O dto) {
        return convertToEntity(dto, createEmptyEntity());
    }

    /**
     * Converte os dados do entity para dto.
     * @param  entity  - {@link E}
     * @param  dto - {@link O}
     * @return {@link O}
     */
    public abstract O convertToDTO(E entity, O dto);

    /**
     * Converte os dados do entity para dto.
     * @param  entity  - {@link E}
     * @return {@link O}
     */
    public O convertToDTO(E entity) {
        return convertToDTO(entity, createEmptyDTO());
    }

    /**
     * Converte os dados do entity para dto.
     * @param  entities  - {@link List} of {@link E}
     * @return {@link List} of {@link O}
     */
    public List<O> convertAllToDTO(List<E> entities) {
        return entities.stream().map((e) -> convertToDTO(e)).collect(Collectors.toList());
    }
}