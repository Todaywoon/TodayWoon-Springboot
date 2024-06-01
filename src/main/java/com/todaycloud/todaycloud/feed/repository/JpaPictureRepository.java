package com.todaycloud.todaycloud.feed.repository;

import com.todaycloud.todaycloud.feed.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPictureRepository extends JpaRepository<Picture, Long> {
}
