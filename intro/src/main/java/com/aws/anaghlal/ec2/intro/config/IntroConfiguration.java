package com.aws.anaghlal.ec2.intro.config;

//A spring configuration class

import com.aws.anaghlal.ec2.intro.db.DBReader;
import com.aws.anaghlal.ec2.intro.db.DBUtils;
import com.aws.anaghlal.ec2.intro.s3.S3BucketObjectReader;
import com.aws.anaghlal.ec2.intro.s3.S3Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;

@Configuration
public class IntroConfiguration {

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Value("${s3.bucket.object.key}")
    private String objectKey;

    @Value("${rds.jdbc.url}")
    private String jdbcUrl;

    @Value("${rds.secret.name}")
    private String secretName;

    @Bean
    public S3BucketObjectReader s3BucketObjectReader() {
        return new S3BucketObjectReader(bucketName, objectKey, S3Utils.getS3Client());
    }

    @Bean
    public DBReader dbReader() {
        Map<String, String> secretsMap = DBUtils.getValue(DBUtils.createSecretsManagerClient(),secretName, Arrays.asList("username","password"));
        return new DBReader(jdbcUrl,secretsMap.get("username"),secretsMap.get("password"));
    }

}
