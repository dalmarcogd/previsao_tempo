package previsao.tempo.server.core.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import previsao.tempo.server.core.spring.context.ManagerInstance;
import previsao.tempo.server.crud.user.service.UserAuthenticationService;

/**
 * Implementação de um filtro para autenticação.
 *
 * @author Guilherme Dalmarco (dalmarco.gd@gmail.com)
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private Logger log = LogManager.getLogger(this.getClass());

    private UserAuthenticationService userAuthenticationService;

    /**
     * Retorna o userAuthenticationService - {@link UserAuthenticationService}
     * @return {@link UserAuthenticationService}
     */
    public UserAuthenticationService getUserAuthenticationService() {
        if (userAuthenticationService == null) {
			userAuthenticationService = ManagerInstance.get(UserAuthenticationService.class);
		}
        return userAuthenticationService;
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Inicializando {"+getClass().getName()+"}");
    }

    /**
	 * {@inheritDoc}
	 */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        if (!((httpRequest.getMethod().equalsIgnoreCase(HttpMethod.POST.name()) ||
        		httpRequest.getMethod().equalsIgnoreCase(HttpMethod.GET.name())) &&
                	httpRequest.getRequestURI().contains(new StringBuffer("auth")))){
        	if (httpRequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
				chain.doFilter(httpRequest, httpResponse);
				return;
			}

            // Get the HTTP Authorization header from the request
            String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

            // Check if the HTTP Authorization header is present and formatted correctly
            if (authorizationHeader == null) {
                String origin = httpRequest.getHeader("Origin");
                httpResponse.sendRedirect(origin != null && !origin.isEmpty()? origin + "/login" : "/login");
                return;
            }

            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.trim();

            try {
                // Validate the token
                if (!validateToken(token)) {

                    String origin = httpRequest.getHeader("Origin");
                    httpResponse.sendRedirect(origin != null && !origin.isEmpty()? origin + "/login" : "/login");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                httpResponse.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
                return;
            }
        }
        chain.doFilter(request, response);
    }

	private Boolean validateToken(String token) throws Exception {
        return getUserAuthenticationService().validToken(token);
    }

    /**
     * Called by the web container to indicate to a filter that it is being
     * taken out of service. This method is only called once all threads within
     * the filter's doFilter method have exited or after a timeout period has
     * passed. After the web container calls this method, it will not call the
     * doFilter method again on this instance of the filter. <br>
     * <br>
     * <p>
     * This method gives the filter an opportunity to clean up any resources
     * that are being held (for example, memory, file handles, threads) and make
     * sure that any persistent state is synchronized with the filter's current
     * state in memory.
     */
    @Override
    public void destroy() {
        log.info("Desturindo {"+getClass().getName()+"}");
    }
}