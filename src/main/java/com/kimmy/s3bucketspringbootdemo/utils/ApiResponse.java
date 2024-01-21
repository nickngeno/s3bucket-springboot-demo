package com.kimmy.s3bucketspringbootdemo.utils;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    private WsHeader header;
    private Object body;

    public  static ApiResponse  success (String requestId, int responseCode, String responseMessage, String timestamp, Object data){
        WsHeader wsHeader = WsHeader.builder().requestId(requestId).responseCode(responseCode).responseMessage(responseMessage).timestamp(timestamp).build();
        return  new ApiResponse(wsHeader, data);
    }

    public static ApiResponse error (String requestId, int responseCode, String responseMessage, String timestamp, Object data){
        WsHeader wsHeader = WsHeader.builder().requestId(requestId).responseCode(responseCode).responseMessage(responseMessage).timestamp(timestamp).build();
        return  new ApiResponse(wsHeader, data);
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class  WsHeader {
        private String  requestId;
        private int responseCode;
        private String  responseMessage;
        private String timestamp;
    }

}
