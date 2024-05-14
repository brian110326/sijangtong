package com.example.sijangtong.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Serializable : 객체 상태로 입출력

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDto implements Serializable {
    // 폴더, uuid, 실제 파일명 정보 저장하는 dto

    private String folderPath;

    private String uuid;

    private String fileName;

    // 저장된 파일의 위치
    // getImageURL 메소드 생성 시 imageURL 속성 자동으로 생성
    public String getImageURL() {
        String fullPath = "";

        try {
            // 한글이 있을 수 있으니 encoding 작업 필요
            fullPath = URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return fullPath;
    }

    public String getThumbImageURL() {
        String thumbFullPath = "";

        try {
            // 한글이 있을 수 있으니 encoding 작업 필요
            thumbFullPath = URLEncoder.encode(folderPath + "/" + "s_" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return thumbFullPath;
    }
}