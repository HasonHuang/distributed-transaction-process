package com.hason.dtp.test.account.web;

import com.hason.dtp.account.entity.User;
import com.hason.dtp.core.utils.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

/**
 * 模拟并发注册
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/15
 */
public class MockConcurrent {

    private static BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();
    private static ExecutorService pool =
            new ThreadPoolExecutor(5, 5, 1, TimeUnit.MILLISECONDS, queue);

    public static void main(String[] args) {

        int threads = 3;
        while (threads-- > 0) {
            pool.execute(MockConcurrent::task);
        }
    }

    private static final String url = "http://localhost:8001/register";
    /** 注册用户名的后缀 */
    private static volatile int num = 5153;
    /** 时间间隔 */
    private static final long INTERVAL = 10;

    public static void task() {
        RestTemplate template = new RestTemplateBuilder().build();
        while (true) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("username", "hason" + num++);
            params.add("password", "123");

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
            ParameterizedTypeReference<Result<User>> reference = new ParameterizedTypeReference<Result<User>>() {};

            ResponseEntity<Result<User>> response = template.exchange(url, HttpMethod.POST, entity, reference);
            if (response.getStatusCode() == HttpStatus.OK) {
                Result<User> result = response.getBody();
                if (result.success()) {
                    System.out.println("成功注册：" + result.getData().getUsername());
                } else {
                    System.out.println("注册失败：" + result.getError().getMessage());
                }

            } else {
                System.out.println("网络错误： " + response.getStatusCodeValue() + "  " + response.getBody());
            }

            sleep(INTERVAL);
        }
    }

    private static void sleep(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
    }

}
