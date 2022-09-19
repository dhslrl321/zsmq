# ZSMQ

This is an **Zola Simple Message queue (ZSMQ)** based on a SpringBoot that can be used in various places.

And it's not suitable for production at all.

#### ZSMQs main goals are

- **Low performance!!**
- High Productivity
- Easy to learn
- Ready-to-use

# Quick Start

You can easily run Zola (ZSMQ) server using docker

```shell
docker run -d -p [port]:8291 dhslrl321/zsmq
```

## gradle dependency

We provide a spring boot starter so that you can skip the complicated process and set it up easily.

```groovy
dependencies {
    implementation 'com.github.dhslrl321.zsmq:zola-messaging-sprint-boot-starter:${version}'
}
```

## publish message

```java
@RequiredArgsConstructor
public class MessageProducer {

    private final ZolaQueueMessageTemplate template;

    public void send() {
        template.convertAndSend("MY-QUEUE", "foo");
    }
}
```

## consume message

```java
@Component
@ZolaConsumer
public class MyConsumer {

    @ZolaMessageListener(queueName = "MY-QUEUE")
    public void listen(String message) {
        System.out.println("message = " + message);
    }
}
```

# Motivation

One day, I tried to use a message queue simply for studying.

It was not relevant to the message queue.

But I spent most of my time setting up message queues(RabbitMQ, SQS) and building infrastructure.

It was a very tough time just to perform convertAndSend.

So I decided to **create a server** that has **low performance** but **highly productive** message queue.

This is the beginning of zsmq.

> 'zola' is a korean slang. It means 'utterly', 'extremely', 'super', 'very' 
