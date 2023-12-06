package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.ApiPayload.exception.handler.FoodCategoryHandler;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.MissionRequestDTO;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public Mission plusMission(MissionRequestDTO.plusMissionDTO request){
        Mission newMission = MissionConverter.toAddMission(request);
        return missionRepository.save(newMission);
    }
}