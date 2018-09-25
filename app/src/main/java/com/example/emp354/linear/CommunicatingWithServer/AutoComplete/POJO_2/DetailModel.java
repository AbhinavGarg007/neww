package com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO_2;

import java.util.List;

public class DetailModel {

    private String status;
    private List<String> html_attributions;
    private Result result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }


    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
