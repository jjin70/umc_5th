package umc.study.ApiPayload.exception.handler;

import umc.study.ApiPayload.code.BaseErrorCode;
import umc.study.ApiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}