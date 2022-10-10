### It is the simplest Message Queue you've ever experienced.

<div align="center">

#### [home (engish version)](https://github.com/dhslrl321/zsmq)

#### [korean version docs](https://github.com/dhslrl321/zsmq/blob/main/README-kor.md)

<a href="https://github.com/dhslrl321/zsmq/wiki/Getting-Started-(5min)-eng-ver">📗Getting Started</a> || <a href="https://github.com/dhslrl321/zsmq/wiki/ZSMQ's-Architecture">Overview</a> || <a href="https://github.com/dhslrl321/zsmq/wiki/Docs-(eng-ver)">Docs</a>

</div>

# ZSMQ

ZSMQ (Zola Simple Message Queue) is a very simple message queue created in java.

> 'zola' is a korean slang. It means 'utterly', 'extremely', 'super', 'very'

ZSMQ can be used in a variety of situations (ex, Poc or study), except in the operating environment.

ZSMQ is the best choice if you don't consider performance.

### ZSMQ aims to have

- **Simplest**
- **Easiest**
- **intuitive configuration**
- **you focus on other than message queue**

# Quick Start

ZSMQ is **fast** and **easy to use**, so we aim to **minimize** the configuration.

> Check [example](https://github.com/dhslrl321/zsmq-example) to see the detailed configuration; there is a simple example using zsmq.

You can quickly create a great message queue by following these steps!

1. run messaging server and dashboard
2. gradle dependency
3. configure property
4. Just U.S.E it!!

## 1. run messaging server and dashboard

ZSMQ provides two components:

Both of the following components must be run

1. Messaging Server
2. Message Server Dashboard

The **Messaging Server** manages the MQ. The destination to publish and subscribe to a message is **Messaging Server**.

The **dashboard** provides a view of the server. Describe the message queue and crreate/delete the MQ.

#### You can easily run Zola (ZSMQ) server using docker

```shell
docker run --rm -d -p 8290:3000 dhslrl321/zsmq:dashboard.1.0.1
docker run --rm -d -p 8291:8291 dhslrl321/zsmq:server.1.0.0
```

> messaging server and dashboard's port must be 8290, 8291 ! If you need to change the port, please raise it to issue.

## 2. gradle dependency

Two dependencies are required to use zsmq.

1. zola-messaging-core
2. zola-messaging-spring-sdk

When used with spring framework, You can manually add dependencies one by one.

#### However, We provide a `spring-boot-starter` so that you can skip the complicated process and set it up easily.

It can be downloaded from the [jitpack](https://jitpack.io/#dhslrl321/zsmq) repository.

Add the following blocks to `build.gradle`

```groovy
repositories {
    // ... other maven repository
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.dhslrl321.zsmq:zola-messaging-spring-boot-starter:${version}'
}
```

You can manually set bin without using the spring boot starter.

Check the [reference guide](https://github.com/dhslrl321/zsmq/wiki/Reference-Guide) for more information

## 3. configure property

Finally, The url of the Zola Messaging Server must be specified in `application.yml`

```yaml
zsmq:
  url: http://localhost:8291
  listening: false
```

- `zsmq.url` : The **Messaging Server** url that manages the MQ.
- `zsmq.listening` : Decide whether to register the listening thread automatically or not.
  - The listener thread is used when consuming.

## 4. Just U.S.E it !

Now you are done with all settings

You have to use it as it is.

#### 1. Open the dashboard, Create a queue. If you are setting properly just go into `localhost:8290`

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193419660-c2eff6c0-470a-4602-8b26-18bfda08b18c.png">

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193419674-e033cb1a-8594-46ca-a020-7a11ce534c1c.png">

#### 2. write the code in your application

- If you want to **publish a message**, please use `ZolaQueueMessagingTemplate`.
- If you want to **consume a message**, please use '@ZolaConsumer' and `@ZolaMessageListener`

### publish message

```java
@RequiredArgsConstructor
public class MessageProducer {

    private final ZolaQueueMessageTemplate template;

    public void send() {
        template.convertAndSend("MY-QUEUE", "foo");
    }
}
```

### consume message

The 'zsmq.listening' attribute must be true when consuming a message.

```java
@Component
@ZolaConsumer
public class MyConsumer {

    @ZolaMessageListener(queueName = "MY-QUEUE", deletionPolicy = DeletionPolicy.ALWAYS)
    public void listen(String message) {
        System.out.println("message = " + message);
    }
}
```

> Check [example](https://github.com/dhslrl321/zsmq-example) to see the detailed configuration; there is a simple example using zsmq.

# Motivation

One day, I tried to use a message queue simply for studying.

It was not relevant to the message queue.

But I spent most of my time setting up message queues(RabbitMQ, SQS) and building infrastructure.

It was a very tough time just to perform convertAndSend.

So I decided to **create a server** that has **low performance** but **highly productive** message queue.

This is the beginning of zsmq.
