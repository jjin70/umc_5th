package umc.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.enums.mapping.MemberMission;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

    Page<Review> getReviewList(Long StoreId, Integer page);

    Page<Review> getMyReviewList(Integer page);

    Page<MemberMission> getMyMissionList(String missionStatus, Integer page);
}