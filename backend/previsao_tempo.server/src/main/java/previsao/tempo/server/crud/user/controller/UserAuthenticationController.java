package previsao.tempo.server.crud.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import previsao.tempo.server.crud.user.service.UserAuthenticationService;
import previsao.tempo.server.model.user.Credentials;
import previsao.tempo.server.model.user.TokenDTO;
import previsao.tempo.server.model.user.ValidToken;

/**
 * Controller responsável por autenticar usuários na aplicação.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    /**
     * Recurso para autenticação das credenciais do usuário.
     * @param credentials - {@link Credentials}
     * @return ResponseEntity
     */
    @PostMapping
    public @ResponseBody ResponseEntity<?> authenticateUser(@RequestBody Credentials credentials) {
        try {
            TokenDTO token = userAuthenticationService.authenticateUser(credentials);
            // Informa nos atributos do request o token.
            RequestContextHolder.currentRequestAttributes().setAttribute(HttpHeaders.AUTHORIZATION, token.getToken(), RequestAttributes.SCOPE_REQUEST);

            // Return the token on the response
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Recurso para validar o token do usuário.
     * @param token - {@link TokenDTO}
     * @return ResponseEntity
     */
    @PutMapping
    public ResponseEntity<?> validToken(@RequestBody ValidToken validToken) {
        try {
            // Return the token on the response
            return ResponseEntity.ok(userAuthenticationService.validToken(validToken.getToken()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}