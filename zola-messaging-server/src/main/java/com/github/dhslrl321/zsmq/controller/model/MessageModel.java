package com.github.dhslrl321.zsmq.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {
    private HeaderModel header;
    private PayloadModel payload;
}
