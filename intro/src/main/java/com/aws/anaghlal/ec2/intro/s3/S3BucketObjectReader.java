package com.aws.anaghlal.ec2.intro.s3;

import com.amazonaws.services.s3.AmazonS3Client;

public class S3BucketObjectReader {

    private String bucketName;
    private String objectKey;
    private AmazonS3Client s3Client;

    public S3BucketObjectReader(String bucketName, String objectKey, AmazonS3Client s3Client) {
        this.bucketName = bucketName;
        this.objectKey = objectKey;
        this.s3Client = s3Client;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getObjectKey() {
        return objectKey;
    }


    public String read() {
        return s3Client.getObjectAsString(bucketName, objectKey);
    }

    public String readReverse() {
        return new StringBuilder(read()).reverse().toString();
    }

    public boolean isS3ObjectPalindrome() {
        return read().equals(readReverse());
    }

    //a method read from s3 bucket and key given as parameters
    //returns the content of the object as a string
    public String read(String bucketName, String objectKey) {
        return s3Client.getObjectAsString(bucketName, objectKey);
    }

}
