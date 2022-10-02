#### You can see the full source code at [zsmq-example.git](https://github.com/dhslrl321/zsmq-example).

# Implements a Application that use Messaging Infrastructure in 5 Minutes

Let's make a application that use messaging platform in five minutes with ZSMQ.

The example implements the following situations:

<img width="1352" alt="image" src="https://user-images.githubusercontent.com/48385288/193443880-2538a642-7d87-4fd3-89dd-70cbc587f688.png">

- **order-service** publish two events.
  1. `ORDER-CONFIRMED`
  2. `ORDER-CANCELED`
- **delivery-service** consume two events.
  - `ORDER-CONFIRMED` 's payload is java object
  - `ORDER-CANCELED` 's payload is simple text (string)

# Step

The configuration order follows the quickstart's step in [README](https://github.com/dhslrl321/zsmq)

1. run messaging server and dashboard
2. gradle dependency
3. configure property
4. just U.S.E it

# 1. run messaging server and dashboard

First, run the messaging server and the dashboard using the docker.

<img width="1586" alt="image" src="https://user-images.githubusercontent.com/48385288/193444017-82f42de5-eee0-4273-bed2-9d860f7ec9e5.png">

Make sure it's done well.

# 2. gradle dependency

gradle 의존성을 추가하기 위해서 두개의 spring application 을 생성해줍니다.

Create two spring applications.

<img width="171" alt="image" src="https://user-images.githubusercontent.com/48385288/193444059-7bf7e383-ce4d-4f61-917d-739bb292757f.png">

and add gradle dependencies to `build.gradle`

<img width="1390" alt="image" src="https://user-images.githubusercontent.com/48385288/193444105-bf8c6a7b-2fe2-40b0-81cf-a679de73e5b8.png">

check the [release note](https://github.com/dhslrl321/zsmq/releases) for version

# 3. configure property

The two applications have different uses.

1. order-service (produce)
2. delivery-service (consume)

## order-service, produce

order-service is an application that publish a message.

Set 'application.yml' as follows

<img width="436" alt="image" src="https://user-images.githubusercontent.com/48385288/193444204-e2f5be7a-00a0-47f3-9f09-11a8489a00b9.png">

Writing 'zsmq.listening' as false means that you will not run the message listener thread.

#### Now let's implemnts an application!

### Order.java

Implement simple Order objects. We will forward the Order object to 'delivery-service'.

```java
@Value(staticConstructor = "of")
public class Order {
    String orderId;
    String address;
    int price;
}
```

### MessagePublisher.java

And implements a simple message publisher.

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

You can easily convert and send using `ZolaQueueMessageTemplate`

You can forward it in object format or in simple string format.

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

Let's implement a simple controller.

There are two apis here.

1. `orders/{index}/confirm` : publish objects
2. `orders/{index}/cancel` : publish text, string

We are now ready to publish the message.

## delivery-service, consume

Because `delivery-service` will consume the message, the yml listening thread must be activated as follows

<img width="431" alt="image" src="https://user-images.githubusercontent.com/48385288/193455483-20358a1f-4531-4267-8214-3a6044bded05.png">

set `zsmq.listening` true

Create the next two processors to consuming

### OrderCanceledProcessor.java

when `order-service` s `orders/{index}/cancel` are called, this will works

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

When using '@ZolaMessageListener', the parameters of the method must be String type and single argument.

Pass consumed data with the corresponding parameters.

### OrderConfirmedProcessor.java

when `order-service` s `orders/{index}/confirm` are called, this will works

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

If you publish the object, you will receive the data in the form of json.

'ZolaJsonSerializer' provided by zsmq makes it easy to deserialize json.

# Let's run!

All preparations are now complete.

Let's check the results

First, you need to create a queue.

Access the dashboard running `localhost:8290`

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193455864-8f057429-10e6-4b3d-91a9-5588d6db7358.png">

We need to create two queues. Click on the Create QUEUE menu on the tab on the right and add a queue

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193455885-6bdbdd16-e8f5-465f-8319-da94cfc6f0d4.png">

Add both queues.

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193455898-64ab49b3-200c-4358-ae0b-40e3bef75de4.png">

Run `order-service` 와 `delivery-service` application

and `order-service` request api!

<img width="1228" alt="image" src="https://user-images.githubusercontent.com/48385288/193456033-aff5fc67-8ea3-4ba4-9af3-3b222823afa9.png">

Since the delivery-service was previously required to output the log, you can see that the log is taken as shown below.

<img width="1481" alt="image" src="https://user-images.githubusercontent.com/48385288/193456066-8ff8a0f4-c781-466a-b1c3-ca402e9a9749.png">

One is printed as it is in json form, and the other is printed as it is taken out of the object.

Now if you call the rest of the cancel, you will get the same result.

<img width="1272" alt="image" src="https://user-images.githubusercontent.com/48385288/193456156-9a8d55a8-4bce-4f34-995f-24fc73b67ffb.png">

<img width="1435" alt="image" src="https://user-images.githubusercontent.com/48385288/193456187-83c9ec37-04ff-4919-a522-78696d15ac7b.png">

#### You can see the full source code at [zsmq-example.git](https://github.com/dhslrl321/zsmq-example).
