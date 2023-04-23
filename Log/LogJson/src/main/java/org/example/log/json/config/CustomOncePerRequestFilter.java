package org.example.log.json.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    private static final String SESSION_ID = "sessionId";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ThreadContext.put(SESSION_ID, request.getHeader(SESSION_ID));
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(requestWrapper, responseWrapper);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        Object requestBody = getObject(requestWrapper.getContentAsByteArray());
        Object responseBody = getObject(responseWrapper.getContentAsByteArray());

        LogEnd logEnd = LogEnd
                .builder()
                .method(request.getMethod())
                .uri(request.getRequestURI())
                .request(requestBody)
                .response(responseBody)
                .status(response.getStatus())
                .time(time)
                .build();

        log.info(logEnd.getMessage());
        responseWrapper.copyBodyToResponse();
        ThreadContext.clearAll();
    }

    private Object getObject(byte[] contentAsByteArray) {
        if (contentAsByteArray.length == 0) {
            return null;
        }
        try {
            return new ObjectMapper().reader().readValue(contentAsByteArray, Object.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @Builder
    public static class LogEnd {
        private String method;
        private String uri;
        private Object response;
        private Object request;
        private long time;
        private int status;


        public HashMap<String, Object> getMessage() {
            HashMap<String, Object> message = new HashMap<>();
            message.put("method", method);
            message.put("uri", uri);
            message.put("request", request);
            message.put("response", response);
            message.put("status", status);
            message.put("time", time);
            return message;
        }
    }
}