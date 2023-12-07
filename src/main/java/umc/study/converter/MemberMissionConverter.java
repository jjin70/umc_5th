package umc.study.converter;

import umc.study.domain.enums.MissionStatus;
import umc.study.domain.enums.mapping.MemberMission;
import umc.study.web.dto.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.MemberMissionResultDTO toChallengeMission(MemberMission memberMission) {
        return MemberMissionResponseDTO.MemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission() {
        return MemberMission.builder()
                .build();
    }
}
