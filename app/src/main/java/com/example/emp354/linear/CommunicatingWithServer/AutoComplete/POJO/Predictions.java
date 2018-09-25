package com.example.emp354.linear.CommunicatingWithServer.AutoComplete.POJO;

import java.util.List;

public class Predictions {

   private String description;
   private String id;
   private String place_id;
   private String reference;
   private List<String> types;
   private List<Terms> terms;
   private List<Matched_substrings> matched_substrings;
   private Structured_formatting structured_formatting;

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPlace_id() {
      return place_id;
   }

   public void setPlace_id(String place_id) {
      this.place_id = place_id;
   }

   public String getReference() {
      return reference;
   }

   public void setReference(String reference) {
      this.reference = reference;
   }

   public List<String> getTypes() {
      return types;
   }

   public void setTypes(List<String> types) {
      this.types = types;
   }

   public List<Terms> getTerms() {
      return terms;
   }

   public void setTerms(List<Terms> terms) {
      this.terms = terms;
   }

   public List<Matched_substrings> getMatched_substrings() {
      return matched_substrings;
   }

   public void setMatched_substrings(List<Matched_substrings> matched_substrings) {
      this.matched_substrings = matched_substrings;
   }

   public Structured_formatting getStructured_formatting() {
      return structured_formatting;
   }

   public void setStructured_formatting(Structured_formatting structured_formatting) {
      this.structured_formatting = structured_formatting;
   }
}
