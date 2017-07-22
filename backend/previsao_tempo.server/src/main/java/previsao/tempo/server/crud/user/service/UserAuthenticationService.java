package previsao.tempo.server.crud.user.service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import previsao.tempo.server.core.exception.ValidationException;
import previsao.tempo.server.core.utils.EncryptionUtil;
import previsao.tempo.server.model.user.Credentials;
import previsao.tempo.server.model.user.TokenDTO;
import previsao.tempo.server.model.user.UserEntity;

/**
 * Serviço que autentica os usuarios do sistema.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Service
public class UserAuthenticationService {

    @Autowired
    private UserQueryService userQueryService;
    private ConcurrentMap<String, Credentials> mapCacheToken = new ConcurrentHashMap<>();

    /**
     * Autentica o usuario com as credenciais recebidas, gerando um {@link UserTokenEntity} e retornando o mesmo.
     * @param credentials - {@link Credentials}
     * @return TokenDTO
     */
    @Transactional(rollbackFor = Throwable.class)
    public TokenDTO authenticateUser(Credentials credentials) throws Exception{
        if (existUser(credentials)) {
            TokenDTO tokenDTO = new TokenDTO();
            String username = credentials.getUsername();
			tokenDTO.setToken(generateToken(username, credentials.getPassword()));
            tokenDTO.setUsername(username);
            mapCacheToken.put(tokenDTO.getToken(), credentials);
			return tokenDTO;
        }
        throw new ValidationException("Login ou senha incorretos.");
    }

    private boolean existUser(Credentials credentials) {
        UserEntity userByUsername = userQueryService.getUserByUsername(credentials.getUsername());
        return userByUsername != null && userByUsername.getPassword().equalsIgnoreCase(credentials.getPassword());
    }

    /**
     * Valida o token, verificando se
     * @param token - {@link String}
     * @return Boolean
     */
    @Transactional(rollbackFor = Throwable.class)
    public Boolean validToken(String token) throws ValidationException {
        return mapCacheToken.containsKey(token);
    }

    /**
     * Retorna o token gerado com base no login e senha.
     * @param username - {@link String}
     * @param password - {@link String}
     * @throws Exception
     */
    public String generateToken(String username, String password) throws Exception {
    	return EncryptionUtil.encodeMD5(username+"#"+password);
    }

    /**
     * REtorna as credenciais do token.
     */
    public Credentials getCredentials(String token) {
    	return this.mapCacheToken.get(token);
    }
}