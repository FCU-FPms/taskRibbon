package fcu.ms.taskRibbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/tasks")
    public Mono<String> getTasks() {
        return webClientBuilder.build().get().uri("http://MS-PROVIDER-DEVELOP/tasks")
                .retrieve().bodyToMono(String.class);

    }

    @GetMapping("/log-task-instance")
    public void logTaskInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("MS-PROVIDER-DEVELOP");
        TaskController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

}
