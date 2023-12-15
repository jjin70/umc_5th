package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.ApiPayload.code.status.ErrorStatus;
import umc.study.ApiPayload.exception.handler.MemberHandler;
import umc.study.domain.Member;
import umc.study.domain.enums.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.ReviewRepository;
import umc.study.repository.StoreRepository;
import umc.study.validation.annotation.ExistPage;
import umc.study.domain.enums.MissionStatus;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Store> findStore(Long storeId) {
        return storeRepository.findById(storeId);
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page,10));
        return StorePage;
    }

    @Override
    public Page<Review> getMyReviewList(@ExistPage Integer page) {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<Review> reviewPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return reviewPage;
    }

    @Override
    public Page<MemberMission> getMyMissionList(String missionStatus, @ExistPage Integer page) {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<MemberMission> usermissions = memberMissionRepository.findAllByMemberAndMissionStatus(member, MissionStatus.valueOf(missionStatus), PageRequest.of(page, 10));
        return usermissions;
    }
}