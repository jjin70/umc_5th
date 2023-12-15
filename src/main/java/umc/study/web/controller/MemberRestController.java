package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.ApiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberMissionConverter;
import umc.study.domain.Member;
import umc.study.domain.enums.mapping.MemberMission;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistMission;
import umc.study.web.dto.MemberMissionResponseDTO;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import javax.validation.Valid;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import umc.study.converter.StoreConverter;
import umc.study.domain.Review;
import umc.study.web.dto.StoreResponseDTO;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/{missionId}")
    public ApiResponse<MemberMissionResponseDTO.MemberMissionResultDTO> memberMission(@ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                                      @ExistMember @RequestParam(name = "memberId") Long memberId) {
        MemberMission memberMIssion = memberCommandService.AddMission(memberId, missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengeMission(memberMIssion));
    }

    @GetMapping("/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API",description = "내가 작성한 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getMyReviewList(@RequestParam(name = "page") Integer page){
        Page<Review> storePage = storeQueryService.getMyReviewList(page-1);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(storePage));
    }

    @GetMapping("/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 API",description = "내가 진행 중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "missionStatus", description = "미션 진행 상태, Query String 입니다!")
    })
    public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMyMissionList(@RequestParam(name = "page") Integer page, @RequestParam(name = "missionStatus") String missionStatus){
        Page<MemberMission> storePage = storeQueryService.getMyMissionList(missionStatus, page-1);
        return ApiResponse.onSuccess(StoreConverter.myMissionPreViewListDTO(storePage));
    }
}