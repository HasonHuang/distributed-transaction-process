package com.hason.dtp.core.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hason.dtp.core.utils.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * 自定义参数解析器：实现绑定请求参数到多个 Java Bean 对象
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/7
 */
public class HandlerMethodMultiArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String ATTR_NAME = "multiParam";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(MultiRequestBody.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
            throws Exception {

//        String json = getRequestInfo(webRequest);
//        Class<?> parameterType = parameter.getParameterType();

        JSONObject para = getRequestInfo1(webRequest);
        Class<?> type = parameter.getParameterType();
        String name = parameter.getParameterName();
        if (null != para && para.containsKey(name)) {
            return JSON.parseObject(para.getString(name), type);
        }
        return null;
    }

    /**
     * 获取请求参数
     *
     * @param webRequest
     * @return json string
     * @throws IOException
     */
    private String getRequestInfo(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String method = httpServletRequest.getMethod();

        if (!method.equals("GET") && !method.equals("DELETE")) {

            // 读取Attribute，没有则从body体读取
            if (null != httpServletRequest.getAttribute(ATTR_NAME)) {
                return httpServletRequest.getAttribute(ATTR_NAME).toString();

            } else {
                StringBuilder buffer = new StringBuilder();
                BufferedReader reader = httpServletRequest.getReader();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                httpServletRequest.setAttribute(ATTR_NAME, buffer.toString());
                return buffer.toString();
            }

        }

        return JsonMapper.INSTANCE.toJson(webRequest.getParameterMap());
    }

    private JSONObject getRequestInfo1(NativeWebRequest webRequest) throws IOException {
        JSONObject para = new JSONObject();

        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String method = httpServletRequest.getMethod();
        if (!method.equals("GET") && !method.equals("DELETE")) {

            // 读取Attribute，没有则从body体读取
            if (null != httpServletRequest.getAttribute("para")) {
                try {
                    para = JSON.parseObject(httpServletRequest.getAttribute("para").toString());
                } catch (Exception e) {
                }
            } else {
                StringBuilder buffer = new StringBuilder();
                BufferedReader reader = httpServletRequest.getReader();
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                httpServletRequest.setAttribute("para", buffer.toString());

                try {
                    para = JSON.parseObject(buffer.toString());
                } catch (Exception e) {
                }
            }
        } else {
            Map<String, String[]> parameterMap = webRequest.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String key = entry.getKey();
                String values = StringUtils.join(entry.getValue());
                para.put(key, values);
            }
        }
        return para;
    }


}
