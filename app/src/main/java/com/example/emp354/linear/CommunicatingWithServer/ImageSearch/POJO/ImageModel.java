package com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO;

import java.util.List;

public class ImageModel {

    private String kind;
    private URLS url;
    private Contexts context;
    private SearchInformation searchInformation;
    private Queries queries;
    private List<Items> items;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public URLS getUrl() {
        return url;
    }

    public void setUrl(URLS url) {
        this.url = url;
    }

    public Contexts getContext() {
        return context;
    }

    public void setContext(Contexts context) {
        this.context = context;
    }

    public SearchInformation getSearchInformation() {
        return searchInformation;
    }

    public void setSearchInformation(SearchInformation searchInformation) {
        this.searchInformation = searchInformation;
    }

    public Queries getQueries() {
        return queries;
    }

    public void setQueries(Queries queries) {
        this.queries = queries;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}

