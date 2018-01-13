package com.hason.dtp.core.exception;

import com.hason.dtp.core.support.MediaTypes;
import com.hason.dtp.core.utils.JsonMapper;
import com.hason.dtp.core.utils.result.Result;
import com.hason.dtp.core.utils.result.ResultBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Restful API 异常处理器
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private JsonMapper jsonMapper = JsonMapper.INSTANCE;

    @ExceptionHandler(value = { ServiceException.class })
    public final ResponseEntity<Result> handleServiceException(ServiceException ex, HttpServletRequest request) {
        // 注入servletRequest，用于出错时打印请求URL与来源地址
        logError(ex, request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        ErrorResult error = new ErrorResult(ex.getCode().getCode(), ex.getMessage());
        Result<?> result = ResultBuilder.newInstance(error);
        return new ResponseEntity<>(result, headers, HttpStatus.valueOf(ex.getCode().getHttpStatus()));
    }

    /**
     * 处理 {@link CheckException}
     */
    @ExceptionHandler(value = { CheckException.class })
    public final ResponseEntity<Result> handleCheckException(CheckException ex, HttpServletRequest request) {
        // 注入servletRequest，用于出错时打印请求URL与来源地址
        logError(ex, request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaTypes.JSON_UTF_8));
        ErrorResult error = new ErrorResult(ErrorCode.BAD_REQUEST.getCode(), ex.getMessage());
        Result<?> result = ResultBuilder.newInstance(error);
        return new ResponseEntity<>(result, headers, HttpStatus.valueOf(ErrorCode.BAD_PARAM.getHttpStatus()));
    }

    /**
     * 重载ResponseEntityExceptionHandler的方法，加入日志
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        logError(ex);

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute("javax.servlet.error.exception", ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }

    public void logError(Exception ex) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        logger.error(jsonMapper.toJson(map), ex);
    }

    public void logError(Exception ex, HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("from", request.getRemoteAddr());
        String queryString = request.getQueryString();
        map.put("path", queryString != null ? (request.getRequestURI() + "?" + queryString) : request.getRequestURI());

        logger.error(jsonMapper.toJson(map), ex);
    }


}
