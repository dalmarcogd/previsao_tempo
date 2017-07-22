package previsao.tempo.server.core.exception.translator;

import previsao.tempo.server.core.exception.ValidationException;

/**
 * Tradutor padr�o das exce��es geradas na aplica��o.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
public class TranslatorException {

    public static void translateToCRUDException(Exception e) throws ValidationException {
        throw new ValidationException(e);
    }
}