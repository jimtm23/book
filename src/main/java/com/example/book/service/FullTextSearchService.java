package com.example.book.service;

import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.queries.MatchQuery;
import com.couchbase.client.java.search.result.SearchResult;
import com.couchbase.client.java.search.result.SearchRow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FullTextSearchService {
    @Autowired
    private Cluster cluster;

    public SearchResult myMethod(String searchterm) {
        SearchResult result = null;
        try {
             result = cluster
                    .searchQuery("book_index", SearchQuery.queryString(searchterm));

            for (SearchRow row : result.rows()) {
                System.out.println("Found row: " + row);
            }

            System.out.println("Reported total rows: "
                    + result.metaData().metrics().totalRows());
        } catch (CouchbaseException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}