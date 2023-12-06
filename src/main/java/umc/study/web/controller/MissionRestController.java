package umc.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.ApiPayload.ApiResponse;
import umc.study.ApiPayload.code.status.SuccessStatus;
import static umc.study.ApiPayload.code.status.SuccessStatus._OK;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import javax.validation.Valid;



@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/missions")
    public ApiResponse<MissionResponseDTO.toAddMissionDTO> addMission(@RequestBody @Valid MissionRequestDTO.plusMissionDTO request){
        Mission mission = missionCommandService.plusMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionDTO(mission));
    }
}