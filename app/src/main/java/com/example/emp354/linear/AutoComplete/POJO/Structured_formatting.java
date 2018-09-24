package com.example.emp354.linear.AutoComplete.POJO;


import com.example.emp354.linear.AutoComplete.POJO.Matched_substrings;

import java.util.List;

public class Structured_formatting {

    private String main_text;
    private String secondary_text;
    private List<Matched_substrings> main_text_matched_substrings;

    public String getMain_text() {
        return main_text;
    }

    public void setMain_text(String main_text) {
        this.main_text = main_text;
    }

    public String getSecondary_text() {
        return secondary_text;
    }

    public void setSecondary_text(String secondary_text) {
        this.secondary_text = secondary_text;
    }

    public List<Matched_substrings> getMain_text_matched_substrings() {
        return main_text_matched_substrings;
    }

    public void setMain_text_matched_substrings(List<Matched_substrings> main_text_matched_substrings) {
        this.main_text_matched_substrings = main_text_matched_substrings;
    }
}
