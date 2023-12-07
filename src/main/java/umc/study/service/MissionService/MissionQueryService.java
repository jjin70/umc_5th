package umc.study.service.MissionService;

import umc.study.domain.Mission;

import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long id);
}
