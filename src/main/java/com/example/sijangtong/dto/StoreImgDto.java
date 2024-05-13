package com.example.sijangtong.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.sijangtong.entity.Store;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreImgDto {
    private Long storeImgId;

    private String stUuid;

    private String stPath;

    private String stImgName;

    public String getImageURL() {
        String fullPath = "";

        try {
            // 한글이 있을 수 있으니 encoding 작업 필요
            fullPath = URLEncoder.encode(stPath + "/" + stUuid + "_" + stImgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return fullPath;
    }

    public String getThumbImageURL() {
        String thumbFullPath = "";

        try {
            // 한글이 있을 수 있으니 encoding 작업 필요
            thumbFullPath = URLEncoder.encode(stPath + "/" + "s_" + stUuid + "_" + stImgName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return thumbFullPath;
    }
}
