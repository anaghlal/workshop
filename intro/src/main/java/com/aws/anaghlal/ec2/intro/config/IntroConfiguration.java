package com.aws.anaghlal.ec2.intro.config;

//A spring configuration class

import com.aws.anaghlal.ec2.intro.s3.S3BucketObjectReader;
import com.aws.anaghlal.ec2.intro.s3.S3Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntroConfiguration {

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Value("${s3.bucket.object.key}")
    private String objectKey;

    @Bean
    public S3BucketObjectReader s3BucketObjectReader() {
        return new S3BucketObjectReader(bucketName, objectKey, S3Utils.createS3Client());
    }
}
