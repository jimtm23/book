package com.example.book;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchBaseConfig extends AbstractCouchbaseConfiguration {
    @Override
    public String getConnectionString() {
        /*192.168.1.10*/
        return "couchbase://127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "admin";
    }

    @Override
    public String getPassword() {
        return "admin1";
    }

    @Override
    public String getBucketName() {
        return "books";
    }
}
