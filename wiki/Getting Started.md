#### 해당 내용은 [zsmq-example.git](https://github.com/dhslrl321/zsmq-example) 에서 전체 소스코드를 확인할 수 있습니다.

# 5분만에 메시징 인프라 구성하기

ZSMQ 를 이용해서 5분만에 메시징 플랫폼을 구성해봅시다.

예제는 다음과 같은 상황을 구현합니다.

<img width="1352" alt="image" src="https://user-images.githubusercontent.com/48385288/193443880-2538a642-7d87-4fd3-89dd-70cbc587f688.png">

- **order-service** 에서 두가지 이벤트를 발행합니다.
  1. `ORDER-CONFIRMED`
  2. `ORDER-CANCELED`
- **delivery-service** 에서는 두가지 이벤트를 consume 합니다
  - `ORDER-CONFIRMED` 의 payload 는 객체 형태입니다.
  - `ORDER-CANCELED` 의 payload 는 String 형태입니다.

# 순서

구성 순서는 [README](https://github.com/dhslrl321/zsmq) 에 존재하는 quick start 를 그대로 따릅니다.

1. run messaging server and dashboard
2. gradle dependency
3. configure property
4. just U.S.E it

# 1. run messaging server and dashboard

우선 도커를 이용해서 messaging 서버와 dashboard 를 실행시킵니다.

<img width="1586" alt="image" src="https://user-images.githubusercontent.com/48385288/193444017-82f42de5-eee0-4273-bed2-9d860f7ec9e5.png">

잘 실행된 것을 확인합니다.

# 2. gradle dependency

gradle 의존성을 추가하기 위해서 두개의 spring application 을 생성해줍니다.

<img width="171" alt="image" src="https://user-images.githubusercontent.com/48385288/193444059-7bf7e383-ce4d-4f61-917d-739bb292757f.png">

그리고 각각의 `build.gradle` 에서 의존성을 추가합니다.

<img width="1390" alt="image" src="https://user-images.githubusercontent.com/48385288/193444105-bf8c6a7b-2fe2-40b0-81cf-a679de73e5b8.png">

version 은 [release note](https://github.com/dhslrl321/zsmq/releases) 를 확인해주세요

# 3. configure property

두 애플리케이션의 용도는 서로 다릅니다.

1. order-service (produce)
2. delivery-service (consume)

## order-service, produce

order-service 는 메시지를 발행하는 애플리케이션입니다.

다음과 같이 `application.yml` 설정하세요

<img width="436" alt="image" src="https://user-images.githubusercontent.com/48385288/193444204-e2f5be7a-00a0-47f3-9f09-11a8489a00b9.png">

`zsmq.listening` 을 false 로 준다는 것은 메시지 리스너 스레드를 실행시키지 않겠다는 뜻입니다.

#### 이제 애플리케이션을 개발해봅시다!

### Order.java

간단한 Order 객체를 정의하고 Order 객체를 `delivery-service` 로 전달할 것입니다.

```java
@Value(staticConstructor = "of")
public class Order {
    String orderId;
    String address;
    int price;
}
```

### MessagePublisher.java

그리고 간단한 message publisher 를 구현해줍니다.

```java
@Component
@RequiredArgsConstructor
public class MessagePublisher {
    private final ZolaQueueMessageTemplate template;

    public void sendConfirmedMessage(Order order) {
        template.convertAndSend("ORDER-CONFIRMED", order);
    }

    public void sendCanceledMessage(String orderId) {
        template.convertAndSend("ORDER-CANCELED", orderId);
    }
}
```

`ZolaQueueMessageTemplate` 를 이용해서 convert 와 send 를 쉽게 수행할 수 있습니다.

객체 형식으로 전달할 수도 있고 단순 문자열 형식으로 전달할 수 있습니다.

### OrderController.java

```java
@RestController
@RequiredArgsConstructor
public class OrderController {

    private static final Order[] SAMPLE_ORDERS = {
            Order.of(UUID.randomUUID().toString(), "Seoul", 125_000_000),
            Order.of(UUID.randomUUID().toString(), "New York", 25_602_900),
            Order.of(UUID.randomUUID().toString(), "singapore", 5_120_000),
            Order.of(UUID.randomUUID().toString(), "tokyo", 9_000_000),
    };

    private final MessagePublisher publisher;

    @GetMapping("/orders/{index}/confirm")
    public boolean confirm(@PathVariable int index) {
        try {
            Order order = SAMPLE_ORDERS[index];
            publisher.sendConfirmedMessage(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/orders/{index}/cancel")
    public boolean cancel(@PathVariable int index) {
        try {
            String orderId = SAMPLE_ORDERS[index].getOrderId();
            publisher.sendCanceledMessage(orderId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
```

간단한 controller 를 구현해줍시다.

2개의 api 가 존재합니다.

1. `orders/{index}/confirm` : 객체를 publish
2. `orders/{index}/cancel` : 문자열을 publish

이제 message 를 produce 하는 쪽은 모든 준비가 끝났습니다.

## delivery-service, consume

`delivery-service` 는 메시지를 consume 할 것이기 때문에 다음과 같이 yml 에 리스닝 스레드를 활성화시켜줘야 합니다.

<img width="431" alt="image" src="https://user-images.githubusercontent.com/48385288/193455483-20358a1f-4531-4267-8214-3a6044bded05.png">

`zsmq.listening` 를 true 로 설정하세요.

두개의 큐를 consuming 할 다음 두 processor 를 만들어주세요.

### OrderCanceledProcessor.java

`order-service` 의 `orders/{index}/cancel` 가 호출되면 발행되는 queue 를 consume 합니다.

```java
@Component
@ZolaConsumer
@Slf4j
public class OrderCanceledProcessor {
    @ZolaMessageListener(queueName="ORDER-CANCELED", deletionPolicy = DeletionPolicy.ALWAYS)
    public void listen(String message) {
        log.info("order was canceled id: [{}]", message);
    }
}
```

`@ZolaMessageListener` 를 사용할 때 메서드의 파라미터는 하나의 String 타입 이어야 합니다.

해당 매개변수로 consume 한 데이터를 전달합니다.

### OrderConfirmedProcessor.java

`order-service` 의 `orders/{index}/confirm` 가 호출되면 발행되는 queue 를 consume 합니다.

```java
@Component
@ZolaConsumer
@Slf4j
public class OrderConfirmedProcessor {

    @ZolaMessageListener(queueName="ORDER-CONFIRMED", deletionPolicy = DeletionPolicy.ALWAYS)
    public void listen(String message) {
        log.info("json : {}", message);
        Order order = ZolaJsonSerializer.getInstance().deserialize(message, Order.class);
        log.info("order was confirmed : id : {}, address: {}, price: {}", order.getOrderId(), order.getAddress(), order.getPrice());
    }
}
```

Object 를 publish 한다면 json 형태로 데이터를 받아옵니다.

zsmq 에서 제공하는 `ZolaJsonSerializer` 를 사용하면 json 을 쉽게 deserialize 할 수 있습니다.

# 실행해봅시다!

이제 모든 개발이 끝났습니다.

실행 결과를 확인해봅시다

우선 큐를 만들어야 합니다.

`localhost:8290` 으로 실행되는 dashboard 에 접속하세요.

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193455864-8f057429-10e6-4b3d-91a9-5588d6db7358.png">

우리는 2개의 큐를 생성해야 합니다. 오른쪽의 탭에서 QUEUE 생성 메뉴를 클릭하고 큐를 추가하세요

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193455885-6bdbdd16-e8f5-465f-8319-da94cfc6f0d4.png">

두개의 큐를 모두 추가해줍니다.

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193455898-64ab49b3-200c-4358-ae0b-40e3bef75de4.png">

그리고 `order-service` 와 `delivery-service` 를 실행시켜주세요

그리고 `order-service` 에 api 를 전송해봅시다.

<img width="1228" alt="image" src="https://user-images.githubusercontent.com/48385288/193456033-aff5fc67-8ea3-4ba4-9af3-3b222823afa9.png">

그럼 delivery-service 에는 이전에 로그를 출력하도록 했기 때문에 아래와 같이 로그가 찍히는 것을 확인할 수 있습니다.

<img width="1481" alt="image" src="https://user-images.githubusercontent.com/48385288/193456066-8ff8a0f4-c781-466a-b1c3-ca402e9a9749.png">

하나는 json 형태 그대로, 다른 하나는 객체에서 꺼내온대로 잘 출력되었네요.

이제 나머지 cancel 도 호출하면 동일하게 결과가 나올 것입니다.

<img width="1272" alt="image" src="https://user-images.githubusercontent.com/48385288/193456156-9a8d55a8-4bce-4f34-995f-24fc73b67ffb.png">

<img width="1435" alt="image" src="https://user-images.githubusercontent.com/48385288/193456187-83c9ec37-04ff-4919-a522-78696d15ac7b.png">

#### 해당 내용은 [zsmq-example.git](https://github.com/dhslrl321/zsmq-example) 에서 전체 소스코드를 확인할 수 있습니다.
