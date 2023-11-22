package com.aws.anaghlal.ec2.intro;

import com.aws.anaghlal.ec2.intro.db.DBReader;
import com.aws.anaghlal.ec2.intro.s3.S3BucketObjectReader;
import com.aws.anaghlal.ec2.intro.s3.S3Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.internal.util.EC2MetadataUtils;

@RestController
public class IntroController {

    @Autowired
    private S3BucketObjectReader s3BucketObjectReader;

    @Autowired
    private DBReader dbReader;

    @GetMapping("/hello")
    public String hello(){
        return "Hi from "+getInstanceDetails();
    }

    @GetMapping("/s3")
    public String s3() {
        return s3BucketObjectReader.read();
    }

    @GetMapping("/reads3")
    public String s3(@RequestParam(name="bucket", required = true) String bucket,
                     @RequestParam(name="key", required = true) String key) {
        return s3BucketObjectReader.read(bucket, key);
    }

    @GetMapping("/employees")
    public String employees() {
        return dbReader.fetchAllEmployees();
    }

    private String getInstanceDetails() {
        // Getting instance Id
        String instanceId = EC2MetadataUtils.getInstanceId();

        // Getting EC2 private IP
        String privateIP = EC2MetadataUtils.getInstanceInfo().getPrivateIp();

        return instanceId+" : "+privateIP;

    }






}
