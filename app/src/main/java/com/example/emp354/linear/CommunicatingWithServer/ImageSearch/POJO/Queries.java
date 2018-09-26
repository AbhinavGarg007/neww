package com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO;

import com.nostra13.universalimageloader.utils.L;

import java.util.List;

public class Queries {
    private List<Request> request;
    private List<Request> nextPage;


    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public List<Request> getNextPage() {
        return nextPage;
    }

    public void setNextPage(List<Request> nextPage) {
        this.nextPage = nextPage;
    }
}
