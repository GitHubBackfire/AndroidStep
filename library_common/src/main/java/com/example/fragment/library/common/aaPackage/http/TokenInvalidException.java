package com.example.fragment.library.common.aaPackage.http;

public class TokenInvalidException extends BaseException {
    public TokenInvalidException() {
        super(HttpCode.CODE_TOKEN_INVALID, "Token失效");
    }
}
