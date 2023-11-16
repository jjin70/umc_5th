package umc.study.domain;

import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.mapping.MemberAgree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    private String address;

    private Float score;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

//    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
//    private List<Review> ReiviewList = new ArrayList<>();
}
