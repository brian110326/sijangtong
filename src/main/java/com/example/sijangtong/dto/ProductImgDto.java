package com.example.sijangtong.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.sijangtong.entity.Product;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgDto {
    private Long imgId;

    private String uuid;

    private String path;

    private String imgName;

    public String getImageURL() {
        String fullPath = "";

        try {
            // 한글이 있을 수 있으니 encoding 작업 필요
            fullPath = URLEncoder.encode(path + "/" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return fullPath;
    }

    public String getThumbImageURL() {
        String thumbFullPath = "";

        try {
            // 한글이 있을 수 있으니 encoding 작업 필요
            thumbFullPath = URLEncoder.encode(path + "/" + "s_" + uuid + "_" + imgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return thumbFullPath;
    }
}
