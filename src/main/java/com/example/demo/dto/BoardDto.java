package com.example.demo.dto;

import com.example.demo.domain.entity.Board;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;

    @NotBlank(message = "필수 입력 값입니다.")
    private String study_project;

    @NotBlank(message = "숫자만 입력이 가능합니다.")
    private String person_num;

    @NotBlank(message = "온라인 또는 오프라인만 선택할 수 있습니다.")
    private String online_offline;

    @NotBlank(message = "숫자만 입력이 가능합니다.")
//    @Pattern(regexp = "(?=.*[0-9])",message  ="숫자만 사용가능")
    private String duration;

    @NotBlank(message = "필수 입력 값입니다.")
    private String skill;

    @NotBlank(message = "필수 입력 값입니다.")
    private String date;

    @NotBlank(message = "필수 입력 값입니다.")
    private String calling;

    @NotBlank(message = "필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "필수 입력 값입니다.")
    private String input_content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .study_project(study_project)
                .person_num(person_num)
                .online_offline(online_offline)
                .duration(duration)
                .skill(skill)
                .date(date)
                .calling(calling)
                .title(title)
                .input_content(input_content)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String study_project, String person_num, String online_offline, String duration, String skill, String date, String calling, String title, String input_content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.study_project = study_project;
        this.person_num = person_num;
        this.online_offline = online_offline;
        this.duration = duration;
        this.skill = skill;
        this.date = date;
        this.calling = calling;
        this.title = title;
        this.input_content = input_content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }


}