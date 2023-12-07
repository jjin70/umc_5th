package umc.study.ApiPayload.exception.handler;

import umc.study.ApiPayload.code.BaseErrorCode;
import umc.study.ApiPayload.exception.GeneralException;

public class MemberMissionHandler extends GeneralException {
    public MemberMissionHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
