package com.svistoyanov.eks.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class EksErrorDto extends AbstractDto {

    @JsonProperty("errorCode")
    private ErrorCodesDto errorCode;

    @JsonProperty("messages")
    private List<String> messages = new ArrayList<>();

    public EksErrorDto() {
    }

    public EksErrorDto(ErrorCodesDto errorCode, List<String> messages) {
        this.errorCode = errorCode;
        this.messages = messages;
    }

    public ErrorCodesDto getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodesDto errorCode) {
        this.errorCode = errorCode;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
