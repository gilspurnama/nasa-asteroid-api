package com.nasa.nasaasteroidapi.dto;

public class ResponseDto<T> {
    public T payload;
    public String message;
    public Integer code;

    public ResponseDto(T payload) {
        this(payload, null);
    }

    public ResponseDto(T payload, String message) {
        this.payload = payload;
        this.message = message;
    }
}
