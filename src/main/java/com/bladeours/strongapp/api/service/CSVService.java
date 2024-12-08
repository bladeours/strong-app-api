package com.bladeours.strongapp.api.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CSVService {
    void putDataInDatabase(MultipartFile file) throws IOException;
}
