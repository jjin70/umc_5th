package umc.study.service.MissionService;

import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.web.dto.MissionRequestDTO;

public interface MissionCommandService {

    public Mission plusMission(MissionRequestDTO.plusMissionDTO request);
}