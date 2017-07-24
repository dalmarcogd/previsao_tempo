package previsao.tempo.server.crud.base.controller;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import previsao.tempo.server.core.exception.ValidationException;
import previsao.tempo.server.crud.base.service.AbstractCRUDService;
import previsao.tempo.server.model.base.BaseDTO;
import previsao.tempo.server.model.base.BaseEntity;

/**
 * Define um padr�o de implementa��o dos controladores
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 *
 * @param <O> - Implementa��o de {@link BaseDTO}
 * @param <E> - Implementa��o de {@link BaseEntity}
 */
public abstract class AbstractCRUDController<O extends BaseDTO, E extends BaseEntity> {

    /**
     * Disponibiliza uma forma de salvar entidade.
     * @param dto - {@link BaseDTO}
     * @return {@link ResponseEntity}
     */
    @PostMapping
    public @ResponseBody ResponseEntity<Object> create(@RequestBody O dto) {
        AbstractCRUDService<E, O> service = getService();
        if (dto != null) {
            E entityNew = service.createEmptyEntity();
            try {
                entityNew = service.convertToEntity(dto, entityNew);
                E entityCreated = service.save(entityNew);
                return ResponseEntity.ok(service.convertToDTO(entityCreated, getService().createEmptyDTO()));
            } catch(ValidationException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }

    /**
     * Disponibiliza uma forma de atualizar a entidade.
     * @param dto - {@link BaseDTO}
     * @return {@link ResponseEntity}
     */
    @PutMapping
    public @ResponseBody ResponseEntity<Object> update(@RequestBody O dto) {
        AbstractCRUDService<E, O> service = getService();
        if (dto != null && dto.getId() != null) {
            try {
                E entityOld = service.getEntity(dto.getId());
                E entityChanged = service.convertToEntity(dto, entityOld);
                E entityUpdated = service.save(entityChanged);
                return ResponseEntity.ok(service.convertToDTO(entityUpdated, getService().createEmptyDTO()));
            } catch (ValidationException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }

    /**
     * Disponibiliza uma forma de exclusao da entidade.
     * @param id - {@link Long}
     * @return {@link ResponseEntity}
     */
    @DeleteMapping(params={"id"})
    public @ResponseBody ResponseEntity<Serializable> deleteById(@RequestParam("id") Long id) {
        AbstractCRUDService<E, O> service = getService();
        if (id != null) {
            try {
                service.deleteById(id);
                return ResponseEntity.ok(id);
            } catch (ValidationException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }

    /**
     * Disponibiliza uma forma de leitura da entidade a partir do id.
     * @param id - {@link Long}
     * @return {@link ResponseEntity}
     */
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<?> read(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(getService().getDTO(id));
    }

    /**
     * Disponibiliza uma forma para recuperar todas as entidades.
     * @return {@link ResponseEntity}
     */
    @GetMapping
    public @ResponseBody ResponseEntity<?> read(){
        return ResponseEntity.ok(getService().getDTOs());
    }

    /**
     * Servi�o de persistencia de {@link E}
     * @return {@link AbstractCRUDService} of {@link E}
     */
    protected abstract AbstractCRUDService<E, O> getService();
}