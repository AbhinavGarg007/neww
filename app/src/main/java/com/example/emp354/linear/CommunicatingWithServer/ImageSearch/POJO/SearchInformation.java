package com.example.emp354.linear.CommunicatingWithServer.ImageSearch.POJO;



public class SearchInformation {
    private double searchTime;
    private double formattedSearchTime;
    private double totalResults;
    private String formattedTotalResults;

    public double getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(double searchTime) {
        this.searchTime = searchTime;
    }

    public double getFormattedSearchTime() {
        return formattedSearchTime;
    }

    public void setFormattedSearchTime(double formattedSearchTime) {
        this.formattedSearchTime = formattedSearchTime;
    }

    public double getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(double totalResults) {
        this.totalResults = totalResults;
    }

    public String getFormattedTotalResults() {
        return formattedTotalResults;
    }

    public void setFormattedTotalResults(String formattedTotalResults) {
        this.formattedTotalResults = formattedTotalResults;
    }
}
