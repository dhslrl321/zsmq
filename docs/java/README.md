# 기술적 고민

- **객체지향적 고민**
- **메시지를 주고 받을 수 있도록 하는 고민**
- **더 쉽게 사용할 수 있도록 하는 고민**
- **비동기로 실행시킬 리스너 메서드를 찾는 고민**
- **비동기로 실행시키는 고민**
- **Polling 최적화**

### 객체지향적 고민

- 변하는 것과 변하지 않는 것을 구분
- OCP
- Composite Pattern
- Strategy Pattern

### 메시지를 주고 받을 수 있도록 하는 고민

- message convert and send, Serializer

### 더 쉽게 사용할 수 있도록 하는 고민

- 목표는 **제품이 되게 하자!**
- 제품이 되려면 우선 사용하기 쉬워야 한다
- Spring 에서 쉽게 사용할 수 있도록 Auto Configuration 을 제공하자
- spring boot starter 로 관련 설정을 빠르고 쉽게 구성할 수 있도록 하자

### 비동기로 실행시킬 리스너 메서드를 찾는 고민

- zola listener 를 식별할 수 있어야 한다
- bean finder & Reflection

### 비동기로 실행시키는 고민

- 클라이언트가 등록한 메시지 리스너들을 비동기 환경에서 실행시켜야 한다.
- MessageListening Executor

### References

- [https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html)
