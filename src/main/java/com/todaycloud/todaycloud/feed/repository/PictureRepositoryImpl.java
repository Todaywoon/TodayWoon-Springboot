package com.todaycloud.todaycloud.feed.repository;

import com.todaycloud.todaycloud.common.exception.ErrorCode;
import com.todaycloud.todaycloud.common.exception.ResponseException;
import com.todaycloud.todaycloud.feed.domain.Picture;
import com.todaycloud.todaycloud.feed.domain.PictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PictureRepositoryImpl implements PictureRepository {

    private final JpaPictureRepository jpaPictureRepository;

    @Override
    public Picture findById(Long id) {
        return jpaPictureRepository.findById(id).orElseThrow(() -> new ResponseException(ErrorCode.NOT_EXIST_IMAGE));
    }
}
