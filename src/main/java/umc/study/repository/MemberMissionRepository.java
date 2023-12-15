package umc.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.enums.mapping.MemberMission;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    MemberMission findByMemberAndMission(Member member, Mission mission);

    Page<MemberMission> findAllByMemberAndMissionStatus(Member member, MissionStatus missionStatus, PageRequest pageRequest);
}
