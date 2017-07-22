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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.StandardServletAsyncWebRequest;

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
     * Called by the web container to indicate to a filter that it is being
     * placed into service. The servlet container calls the init method exactly
     * once after instantiating the filter. The init method must complete
     * successfully before the filter is asked to do any filtering work.
     * <p>
     * The web container cannot place the filter into service if the init method
     * either:
     * <ul>
     * <li>Throws a ServletException</li>
     * <li>Does not return within a time period defined by the web
     * container</li>
     * </ul>
     * @param filterConfig The configuration information associated with the
     *                     filter instance being initialised
     * @throws ServletException if the initialisation fails
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Inicializando {"+getClass().getName()+"}");
    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due to a
     * client request for a resource at the end of the chain. The FilterChain
     * passed in to this method allows the Filter to pass on the request and
     * response to the next entity in the chain.
     * <p>
     * A typical implementation of this method would follow the following
     * pattern:- <br>
     * 1. Examine the request<br>
     * 2. Optionally wrap the request object with a custom implementation to
     * filter content or headers for input filtering <br>
     * 3. Optionally wrap the response object with a custom implementation to
     * filter content or headers for output filtering <br>
     * 4. a) <strong>Either</strong> invoke the next entity in the chain using
     * the FilterChain object (<code>chain.doFilter()</code>), <br>
     * 4. b) <strong>or</strong> not pass on the request/response pair to the
     * next entity in the filter chain to block the request processing<br>
     * 5. Directly set headers on the response after invocation of the next
     * entity in the filter chain.
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     * @throws IOException      if an I/O error occurs during this filter's
     *                          processing of the request
     * @throws ServletException if the processing fails for any other reason
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        if (httpRequest.getMethod().equalsIgnoreCase("options")) {
            return;
        }
        if (!((httpRequest.getMethod().equalsIgnoreCase(HttpMethod.POST.name()) ||
        		httpRequest.getMethod().equalsIgnoreCase(HttpMethod.GET.name())) &&
                	httpRequest.getRequestURI().contains(new StringBuffer("auth")))){
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
                RequestContextHolder.setRequestAttributes(new StandardServletAsyncWebRequest(httpRequest, httpResponse), true);
                RequestContextHolder.getRequestAttributes().setAttribute("token", token, WebRequest.SCOPE_REQUEST);
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