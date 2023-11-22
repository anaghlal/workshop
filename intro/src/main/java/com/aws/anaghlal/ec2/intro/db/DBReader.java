package com.aws.anaghlal.ec2.intro.db;

import com.aws.anaghlal.ec2.intro.model.Employee;

import java.sql.Connection;
import java.util.Optional;
import java.util.stream.Collectors;

public class DBReader {
    private String jdbcUrl;

    private String username;

    private String password;

    private Connection connection = null;

    public DBReader(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        DBUtils.connect(jdbcUrl, username, password).ifPresent(conn->connection=conn);
    }

    public String fetchAllEmployees(){
        return DBUtils.getListOfEmployees(Optional.of(connection)).stream().map(Employee::toString).collect(Collectors.joining());
    }
}
