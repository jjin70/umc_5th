package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistStore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class MissionRequestDTO {

    @Getter
    public static class plusMissionDTO{
        @NotNull
        Integer missionPoint;
        @NotBlank
        String missionBody;
        @NotNull
        LocalDate missionDate;
        @ExistStore
        Long storeId;
    }
}
