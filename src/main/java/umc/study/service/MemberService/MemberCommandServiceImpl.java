package umc.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.ApiPayload.exception.handler.FoodCategoryHandler;
import umc.study.ApiPayload.exception.handler.MemberMissionHandler;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberMissionConverter;
import umc.study.converter.MemberPreferConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.mapping.MemberMission;
import umc.study.domain.enums.mapping.MemberPrefer;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;
import umc.study.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public MemberMission AddMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId).get();
        Mission mission = missionRepository.findById(missionId).get();
        MemberMission existMission = memberMissionRepository.findByMemberAndMission(member, mission);
        if(existMission != null) {
            throw new MemberMissionHandler(ErrorStatus.MISSION_CONFLICT);
        }
        MemberMission memberMission = MemberMissionConverter.toMemberMission();

        memberMission.setMember(member);
        memberMission.setMission(mission);

        return memberMissionRepository.save(memberMission);
    }
}