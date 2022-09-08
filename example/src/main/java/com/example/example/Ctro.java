package com.example.example;

import com.github.dhslrl321.zsmq.listener.ZolaListenerContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Ctro {

    private final ZolaListenerContainer container;

    @GetMapping
    public void a() {
        container.listen();
    }
}
