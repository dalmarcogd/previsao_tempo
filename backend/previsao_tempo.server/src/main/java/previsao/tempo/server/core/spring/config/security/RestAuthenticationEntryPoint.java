package previsao.tempo.server.core.spring.config.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * TODO doc me.
 *
 * @autor Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@Component( "restAuthenticationEntryPoint" )
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{

   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
      response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
   }
}