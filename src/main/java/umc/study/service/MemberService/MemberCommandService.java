package umc.study.service.MemberService;

import umc.study.domain.Member;
import umc.study.domain.enums.mapping.MemberMission;
import umc.study.web.dto.MemberRequestDTO;

public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);

    MemberMission AddMission(Long memberId, Long missionId);
}