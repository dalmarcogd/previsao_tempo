package previsao.tempo.server.core.exception.crud;

import java.util.List;

import previsao.tempo.server.core.exception.ValidationException;
import previsao.tempo.server.core.exception.error.ValidationError;

/**
 * Exce��o gerada a partir dos m�todos CRUD.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class ValidationCRUDException extends ValidationException {

	/**
	 * Construtor de ValidationCRUDException
	 * <br />- <b>Data de cria��o:</b> 8 de jul de 2017
	 * @param errors
	 */
	public ValidationCRUDException(List<ValidationError> errors) {
		super(errors);
	}
}
