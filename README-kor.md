### 당신이 경험한 Message Queue 중에 가장 단순한 Message Queue

<div align="center">

#### [home (engish version)](https://github.com/dhslrl321/zsmq)

#### [korean version docs](https://github.com/dhslrl321/zsmq/blob/main/README-kor.md)

<a href="https://github.com/dhslrl321/zsmq/wiki/Getting-Started-(5min)---korean-ver">📗Getting Started</a> || <a href="https://github.com/dhslrl321/zsmq/wiki/ZSMQ's-Architecture">Overview</a> || <a href="https://github.com/dhslrl321/zsmq/wiki/Docs-(kor-ver)">Docs</a> || <a href="https://github.com/dhslrl321/zsmq/wiki/Change-Log">Change Log</a>

</div>

# ZSMQ

ZSMQ 는 java 로 개발된 **졸라 간단한 메시지 큐, Zola Simple Message Queue** 입니다.

> 'zola' is a korean slang. It means 'utterly', 'extremely', 'super', 'very'

ZSMQ 는 운영 환경이 아닌 다양한 곳 (PoC 혹은 특정 분야의 학습) 에서 사용될 수 있습니다.

성능은 중요하지 않지만 아주 간단한 메시지 큐가 필요할 때 zsmq 는 최고의 선택입니다.

### ZSMQ 의 목표는 다음과 같습니다.

- **단순함**
- **쉬움**
- **직관적인 설정 및 사용**
- **당신이 message queue 이외에 것들에 집중하는 것을** 

# Quick Start

ZSMQ 는 빠르고 쉽게 사용할 수 있는 시스템이기 때문에 최소한의 설정을 목표로 합니다.

> 자세한 설정들을 확인하시려면 [example](https://github.com/dhslrl321/zsmq-example) 을 확인하세요. zsmq 를 이용한 간단한 예제가 존재합니다.

아래의 4가지 단계만 거치면 쉽게 메시지 큐 서버 하나를 사용할 수 있습니다.

1. messaging server 와 dashboard 실행
2. gradle 의존성 추가
3. configure property
4. 걍 쓰세요!

## 1. messaging server 와 dashboard 실행

zsmq 는 두가지 컴포넌트를 제공합니다.

아래의 두가지 컴포넌트가 모두 실행되어야 합니다.

1. 메시징 서버
2. 서버 대시보드

> 자세한 사항은 [wiki 의 zsmq 의 architecture](https://github.com/dhslrl321/zsmq/wiki/ZSMQ's-Architecture) 에서 확인할 수 있습니다.

메시지 서버는 message queue 를 관리합니다. message 를 publish 하고 subscribe 하는 대상이 messaging server 입니다.

dashboard 는 messaging server 에 대한 view 를 제공합니다. 메시지 큐를 확인하고 메시지 큐를 생성/삭제합니다.

```shell
docker run --rm -d -p 8290:3000 dhslrl321/zsmq:dashboard.1.0.1
docker run --rm -d -p 8291:8291 dhslrl321/zsmq:server.1.0.0
```

> 만약 포트를 변경해야 하는 일이 생긴다면 issue 에 추가해주세요

## 2. gradle 의존성 추가

zsmq 를 사용하기 위해서는 2가지 의존성이 필요합니다.

1. zola-messaging-core
2. zola-messaging-spring-sdk

spring 에서 사용할 때는 위의 의존성들을 직접 추가하여 bean 으로 등록할 수 있습니다.

#### 하지만 ZSMQ 는 위와 같은 복잡한 설정 과정을 생략하고 쉽게 세팅할 수 있도록 spring-boot-starter 를 제공합니다.

의존성은 [jitpack](https://jitpack.io/#dhslrl321/zsmq) repository 에서 다운받을 수 있습니다.

다음 블록을 `build.gradle` 에 추가하세요.

```groovy
repositories {
    // ... other maven repository
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.dhslrl321.zsmq:zola-messaging-sprint-boot-starter:1.0.0'
}
```

수동으로 설정하는 방법을 포함하여 더욱 많은 정보를 얻기 위해서는 [reference guide](https://github.com/dhslrl321/zsmq/wiki/Reference-Guide) 을 확인하세요.

## 3. configure property

마지막으로 메시징 서버에 대한 정보를 `application.property` 혹은 `application.yml` 에 추가해야 합니다.

```yaml
zsmq:
  url: http://localhost:8291
  listening: false
```

- `zsmq.url` : 메시지 큐를 관리하는 메시징 서버 주소입니다.
- `zsmq.listening` : 리스닝 스레드를 자동으로 등록할지 말지 결정합니다
  - 리스너 스레드는 consuming 할 때 사용됩니다.

## 4. 걍 쓰세요!

이제 모든 설정은 끝났습니다.

그냥 사용하세요!

#### 1. dashboard 에 들어가서 큐를 하나 생성하세요. 설정을 제대로 마쳤다면 `localhost:8290` 로 들어가면 됩니다.

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193419660-c2eff6c0-470a-4602-8b26-18bfda08b18c.png">

<img width="1432" alt="image" src="https://user-images.githubusercontent.com/48385288/193419674-e033cb1a-8594-46ca-a020-7a11ce534c1c.png">

#### 2. 애플리케이션을 개발하세요

- 만약 **메시지를 publish** 하고싶다면 `ZolaQueueMessagingTemplate` 를 사용하면 됩니다.
- 만약 **메시지를 consume** 하고싶다면 `@ZolaConsumer` 와 `@ZolaMessageListener` 어노테이션을 사용하면 됩니다.

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

message 를 consume 할 때는 `zsmq.listening` 속성이 true 여야 합니다.

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

> 자세한 설정들을 확인하시려면 [example](https://github.com/dhslrl321/zsmq-example) 을 확인하세요. zsmq 를 이용한 간단한 예제가 존재합니다.

# Motivation

어느날, 어떤 학습을 하기 위해서 메시지 큐 하나가 필요했던 적이 있습니다.

그 학습에는 메시지 큐에 대한 지식이 깊게 필요하지 않았지만 학습을 이어가기 위해서는 메시지 큐가 꼭 필요했었습니다.

그리고 저는 메시지 큐를 설정하고 생성 및 사용하는데에 아주 많은 시간을 사용했습니다.

그래서 저는 낮은 성능일지라도 매우 쉽고 빠르게 설정하고 생성할 수 있는 큐를 만들자는 결심을 하였고 이것이 바로 zsmq 의 시작입니다.

> 'zola' is a korean slang. It means 'utterly', 'extremely', 'super', 'very'
