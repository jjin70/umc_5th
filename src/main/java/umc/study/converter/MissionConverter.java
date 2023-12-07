package umc.study.converter;

import umc.study.domain.Mission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static Mission toAddMission(MissionRequestDTO.plusMissionDTO request){
        return Mission.builder()
//                .missionPoint(request.getMissionPoint())
//                .missionCondition(request.getMissionCondition())
//                .missionDate(request.getMissionDate())
//                .storeId(request.getStoreId())
                .build();
    }

    public static MissionResponseDTO.toAddMissionDTO toAddMissionDTO (Mission mission) {
        return MissionResponseDTO.toAddMissionDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}