package tech.itpark.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploaderService {

    void upload(final MultipartFile multipartFile);
}
