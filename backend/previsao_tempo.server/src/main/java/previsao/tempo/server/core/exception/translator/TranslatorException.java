package previsao.tempo.server.core.exception.translator;

import previsao.tempo.server.core.exception.ValidationException;

/**
 * Tradutor padrão das exceções geradas na aplicação.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class TranslatorException {

    public static void translateToCRUDException(Exception e) throws ValidationException {
        throw new ValidationException(e);
    }
}