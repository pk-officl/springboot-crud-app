package in.pk.springcrudapp.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import in.pk.springcrudapp.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class RestAuthFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String token = request.getHeader("auth-token");
		if(StringUtils.isBlank(token)) {
			response.sendError(HttpStatus.FORBIDDEN.value(), "Token must be provided !");
			return;
		}
		try {
			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET_KEY).parseClaimsJws(token).getBody();
			request.setAttribute("username", claims.get("username"));
		} catch (Exception e) {
			response.sendError(HttpStatus.FORBIDDEN.value(), "Invalid token or user!");
			e.printStackTrace();
			return;
		}
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String url  = request.getRequestURI();
		if(url.split("/")[1].equalsIgnoreCase("product")||url.split("/")[1].equalsIgnoreCase("auth")) {
			return true;
		}
		return false;
	}

 
}
