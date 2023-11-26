package com.aws.anaghlal.ec2.intro.db;

import com.aws.anaghlal.ec2.intro.model.Employee;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

import java.sql.*;
import java.util.*;


public class DBUtils {

    //a static method to create Secrets Manager client
    public static SecretsManagerClient createSecretsManagerClient(){
        return SecretsManagerClient.builder()
                .build();
    }

    public static Map<String, String> getValue(SecretsManagerClient secretsClient, String secretName, List<String> keys) {
        Map<String, String> response = new HashMap<>();
        try {
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();

            GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);
            String secret = valueResponse.secretString();
            System.out.println(secret);


            keys.forEach(k->response.put(k,valueResponse.getValueForField(k,String.class)
                    .orElse("")));
        } catch (SecretsManagerException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }

        return response;
    }

    public static Optional<Connection> connect(String secretName) {
        Map<String, String> secrets = getValue(createSecretsManagerClient(), secretName, Arrays.asList("url", "user", "password"));
        return connect(secrets.get("url"), secrets.get("user"), secrets.get("password"));
    }

    public static Optional<Connection> connect(String url, String user, String password) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL JDBC Driver Registered!");
        }catch (Exception e){
            System.out.println("Error loading driver: " + e.getMessage());
            return Optional.empty();
        }
        try{
            System.out.println("Connecting to "+url+" with "+user+" and "+password);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
        return Optional.of(conn);
    }


    public static List<Employee> getListOfEmployees(Optional<Connection> connectionOptional) {
        String SQL = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
            connectionOptional.ifPresent(conn->{
                Statement stmt = null;
                try {
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(SQL);
                    while (rs.next()) {
                        //Retrieve by column name
                        Employee.from(rs).ifPresent(employees::add);
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            });

        return employees;

    }

}
