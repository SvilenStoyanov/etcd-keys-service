package com.svistoyanov.eks.exception;

import com.svistoyanov.eks.api.dto.EksErrorDto;
import com.svistoyanov.eks.api.dto.ErrorCodesDto;

import java.util.List;

public class EksResponseFactory extends AbstractResponseFactory<EksErrorDto> {

    @Override
    protected EksErrorDto createResponse(int errorCode, List<String> messages) {
        final var errorCodeDto = resolve(errorCode);
        return new EksErrorDto(errorCodeDto, messages);
    }

    private ErrorCodesDto resolve(int statusCode) {
        return switch (statusCode) {
            case 400 -> ErrorCodesDto.VALIDATION;
            case 401, 403 -> ErrorCodesDto.FORBIDDEN;
            case 404 -> ErrorCodesDto.NOT_FOUND;
            case 409 -> ErrorCodesDto.CONFLICT;
            case 503 -> ErrorCodesDto.SERVICE_UNAVAILABLE;
            default -> ErrorCodesDto.INTERNAL_SERVER_ERROR;
        };
    }
}
