package org.example.statistic.config;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
@Log4j2
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    private static final String TRACE_ID = "trace_id";

    private static final String REQUEST_ID = "requestId";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        MDC.put(REQUEST_ID, request.getHeader(REQUEST_ID));
        Span span = Span.current();

        span.setAttribute(REQUEST_ID, request.getHeader(REQUEST_ID));
        MDC.put(TRACE_ID, Span.current().getSpanContext().getTraceId());
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
                request.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
                response.getCharacterEncoding());
        log.info(
                "METHOD={}; URI={}; REQUEST={}; STATUS={}; RESPONSE={}; TIME={}",
                request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
                time);
        span.setAttribute(REQUEST_ID, request.getHeader(REQUEST_ID));
        responseWrapper.copyBodyToResponse();
        MDC.clear();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) throws UnsupportedEncodingException {
        return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
    }
}