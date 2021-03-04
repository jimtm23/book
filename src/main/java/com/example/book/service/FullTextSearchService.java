package com.example.book.service;

import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.search.HighlightStyle;
import com.couchbase.client.java.search.SearchOptions;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.queries.MatchQuery;
import com.couchbase.client.java.search.result.SearchResult;
import com.couchbase.client.java.search.result.SearchRow;
import com.example.book.Request.FTSResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FullTextSearchService {
    @Autowired
    private Cluster cluster;

    public List<FTSResponse> myMethod(String searchterm) {
        SearchResult result = null;
        List<FTSResponse> responses = new ArrayList<>();
        try {
//            MatchQuery mq = SearchQuery.match(searchterm).field("title").fuzziness(1);
            result = cluster.searchQuery("book_index", SearchQuery.queryString(searchterm), SearchOptions.searchOptions().highlight(HighlightStyle.HTML, "title", "description", "authors.fullName")); //

            for (SearchRow row : result.rows()) {
                FTSResponse response = new FTSResponse();
                response.id(row.id());
                response.author(getRowData(row, "authors.fullName"));
                response.description(getRowData(row, "description"));
                response.score(row.score());
                response.title(getRowData(row, "title"));
                responses.add(response);
            }

            System.out.println("Reported total rows: "
                    + result.metaData().metrics().totalRows());
        } catch (CouchbaseException ex) {
            ex.printStackTrace();
        }
        return responses;
    }

    private String getRowData(SearchRow row, String field) {
        return row.fragments().get(field).get(0);
    }
}