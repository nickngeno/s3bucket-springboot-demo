package com.kimmy.s3bucketspringbootdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Data
@Configuration
@ConfigurationProperties(prefix = "aws")
public class AwsConfig {

    private  String accessKey;
    private  String secretKey;
    private  String region;

    @Bean
    public S3Client amazonS3(){
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider( StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
}
