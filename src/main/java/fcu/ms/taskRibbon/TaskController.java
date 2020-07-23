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

//    @LoadBalanced
    @GetMapping("/tasks")
    public void getTasks() {
        String baseUrl = "http://140.134.26.71:41394";
        WebClient webClient = WebClient.create(baseUrl);
        Mono<String> mono = webClient.get().uri("tasks").retrieve().bodyToMono(String.class);
        System.out.println("Franky-test");
        mono.subscribe(System.out::println);
    }

//
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//    @GetMapping("/tasks")
//    public void getTasks() {
//        String baseUrl = "http://140.134.26.71:41394";
//        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
//        WebClient webClient = this.webClientBuilder.uriBuilderFactory(factory).build();
//
//        Mono<String> mono = webClient.get().uri("tasks").retrieve().bodyToMono(String.class);
//        System.out.println("Franky-test");
//        mono.subscribe(System.out::println);
//    }

}
