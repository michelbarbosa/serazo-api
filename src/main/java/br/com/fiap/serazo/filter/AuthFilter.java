package br.com.fiap.serazo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		authorize(req);
		chain.doFilter(req, res);
	}
	
	private static void authorize(ServletRequest req) {
		if (!(req instanceof HttpServletRequest)) {
			return;
		}
		HttpServletRequest httpReq = (HttpServletRequest) req;
		String uri = httpReq.getRequestURI().replaceAll("/$", "");
		if (uri.equals("/empresas") || uri.equals("/empresas/login")) {
			return;
		}
		
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}