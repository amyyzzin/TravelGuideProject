package com.tistory.amyyzzin.trvl.exception;

import org.springframework.http.HttpStatus;

public class OpenApiException extends AbstractException {


    @Override
    public int getStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    @Override
    public String getMessage() {
        return "공공 API로부터 정상적인 응답을 받지 못했습니다. 잠시 후 시도해주세요.";
    }
}
