package com.todaycloud.todaycloud.feed.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="Feed")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime finishTime;

    //산책 시간(분)
    @Column
    private Long duration;

    @Column
    private Long userId;

    @Column
    private Long likeCount;

    @Column
    private Long pictureId;

}
