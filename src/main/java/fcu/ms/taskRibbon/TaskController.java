package fcu.ms.taskRibbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@RestController

public class TaskController {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/tasks")
    public void getTasks() {
        Mono<String> mono = webClientBuilder.build().get().uri("http://MS-PROVIDER-DEVELOP/tasks")
                .retrieve().bodyToMono(String.class);
        System.out.println("Franky-test");
        mono.subscribe(System.out::println);
    }

}
