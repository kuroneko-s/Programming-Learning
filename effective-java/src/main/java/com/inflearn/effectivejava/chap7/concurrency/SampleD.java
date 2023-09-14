package com.inflearn.effectivejava.chap7.concurrency;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.inflearn.effectivejava.chap7.concurrency.CommonSupport.*;

public class SampleD {
    private static final int before = 2000;
    private static final int after = 10;

    private static final ForkJoinPool commonPool = new ForkJoinPool(after);

    @SneakyThrows
    public static void main(String[] args) {
        start();

        // range, rangeClosed 차이점 - 종료값을 포함하냐 마냐의 차이.
        commonPool.submit(new UserFlowRecursiveAction(IntStream.rangeClosed(1, 1000)
                .boxed() // Integer 로 boxing
                .collect(Collectors.toList())));

        commonPool.shutdown();
        commonPool.awaitTermination(60, TimeUnit.SECONDS);

        stop();
    }

    static class UserFlowRecursiveAction extends RecursiveAction {
        private final List<Integer> workload;

        public UserFlowRecursiveAction(List<Integer> workload) {
            this.workload = workload;
        }

        @Override
        protected void compute() {
            if(workload.size() > 1) {
                // 이런식으로 하나씩 쪼개서 재귀함수로 넘겨주나보다...
                commonPool.submit(new UserFlowRecursiveAction(workload.subList(1, workload.size())));
            }

            int user = workload.get(0);

            ForkJoinTask<String> taskA = commonPool.submit(() -> service("A", SERVICE_A_LATENCY, user));
            ForkJoinTask<String> taskB = commonPool.submit(() -> service("B", SERVICE_B_LATENCY, user));

            // join 은 get 과 에러시 발생하는 클래스가 다를 뿐 ? 그정도 ?
            IntStream.rangeClosed(1, PERSISTENCE_FORK_FACTOR)
                    .forEach(number -> commonPool.submit(() -> persistence(number, taskA.join(), taskB.join())));
        }
    }
}
