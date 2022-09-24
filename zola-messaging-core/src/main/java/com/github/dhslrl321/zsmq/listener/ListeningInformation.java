package com.github.dhslrl321.zsmq.listener;

import lombok.Value;

@Value(staticConstructor = "of")
public class ListeningInformation {
    String server;
    String queueName;
    DeletionPolicy deletionPolicy;

    public boolean isSameDeletionPolicy(DeletionPolicy policy) {
        return deletionPolicy.equals(policy);
    }
}
