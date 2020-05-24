package com.prometheus.ionkid.config.contextfilter;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

public class RequestCacheOAuth2ClientContextFilter extends OAuth2ClientContextFilter {
  private final RequestCache cache = new HttpSessionRequestCache();

  @Override
  protected void redirectUser(UserRedirectRequiredException e, HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request) {
      @Override
      public String getRequestURI() {
        return URI.create(getReferer()).getPath();
      }

      @Override
      public StringBuffer getRequestURL() {
        return new StringBuffer(getReferer());
      }

      private String getReferer() {
        return super.getHeader(HttpHeaders.REFERER);
      }
    };
    cache.saveRequest(wrapper, response);
    super.redirectUser(e, request, response);
  }
}