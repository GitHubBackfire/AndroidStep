package com.example.fragment.library.common.aaPackage.http;

public class ParamterInvalidException extends BaseException{
    public ParamterInvalidException() {
        super(HttpCode.CODE_PARAMETER_INVALID, "参数有误");
    }
}
