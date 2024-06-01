package com.todaycloud.todaycloud.feed.service.dto;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

public record PictureDto(InputStreamResource resource, MediaType mediaType) {
}
