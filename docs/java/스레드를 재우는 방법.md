# Thread 를 재우는 방법

- Thread.sleep()
- ThreadUtils.sleep()
- TimeUnit

```java
class ThreadTest {
    @Test
    void sleep1() throws Exception {
        // Call to 'Thread.sleep()' in a loop, probably busy-waiting
        while(true) {
            System.out.println("hello world");
            Thread.sleep(1000);
        }
    }
    @Test
    void sleep2() throws Exception {

        while(true) {
            System.out.println("hello world");
            ThreadUtils.sleep(Duration.of(1000, ChronoUnit.MILLIS));
        }
    }
    @Test
    void sleep3() throws Exception {

        while(true) {
            System.out.println("hello world");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
```

### References

- [https://stackoverflow.com/questions/66764515/how-to-deal-with-call-to-thread-sleep-in-a-loop-probably-busy-waiting](https://stackoverflow.com/questions/66764515/how-to-deal-with-call-to-thread-sleep-in-a-loop-probably-busy-waiting)