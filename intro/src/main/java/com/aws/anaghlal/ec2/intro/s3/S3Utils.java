package com.aws.anaghlal.ec2.intro.s3;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;


public class S3Utils {

    //static method to create an S3 client
    public static AmazonS3Client createS3Client() {
        //create the S3 client
        AmazonS3Client s3Client = new AmazonS3Client(
                new ProfileCredentialsProvider());
        return s3Client;
    }

    //static method to read an s3 object given a client and bucket name and key
    /*public static String readS3Object(AmazonS3Client s3Client, String bucketName, String key) {
        //get the object
        String object = s3Client.getObjectAsString(bucketName, key);
        //reverse the string
        object = new StringBuilder(object).reverse().toString();
        //return the reversed string

        //check if string is a palindrome
        if (object.equals(new StringBuilder(object).reverse().toString())) {
            System.out.println("String is a palindrome");
        }
        return object;
    }*/

    //check if a character array is a palindrome without using a string


    //junit test for isPalindrome
}
