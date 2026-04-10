package com.project.retailstorefeedback.Models;

import java.util.List;
import java.util.Map;

public class FeedbackSummary {
    private int totalFeedback;

    // Fixed naming convention (camelCase)
    private Map<String, Integer> sentimentCounts;
    private Map<String, Integer> categoryCounts;
    private Map<String, Integer> departmentCounts;

    // Fixed Type: This must hold EnhancedFeedback objects, not Integers
    private List<EnhancedFeedback> recentFeedback;

    public FeedbackSummary() {}

    public FeedbackSummary(int totalFeedback, Map<String, Integer> sentimentCounts,
                           Map<String, Integer> categoryCounts, Map<String, Integer> departmentCounts,
                           List<EnhancedFeedback> recentFeedback) {
        this.totalFeedback = totalFeedback;
        this.sentimentCounts = sentimentCounts;
        this.categoryCounts = categoryCounts;
        this.departmentCounts = departmentCounts;
        this.recentFeedback = recentFeedback;
    }

    public int getTotalFeedback() {
        return totalFeedback;
    }

    public void setTotalFeedback(int totalFeedback) {
        this.totalFeedback = totalFeedback;
    }

    public Map<String, Integer> getSentimentCounts() {
        return sentimentCounts;
    }

    public void setSentimentCounts(Map<String, Integer> sentimentCounts) {
        this.sentimentCounts = sentimentCounts;
    }

    public Map<String, Integer> getCategoryCounts() {
        return categoryCounts;
    }

    public void setCategoryCounts(Map<String, Integer> categoryCounts) {
        this.categoryCounts = categoryCounts;
    }

    public Map<String, Integer> getDepartmentCounts() {
        return departmentCounts;
    }

    public void setDepartmentCounts(Map<String, Integer> departmentCounts) {
        this.departmentCounts = departmentCounts;
    }

    // UPDATED: Matches the Service call summary.setRecentFeedback(...)
    public List<EnhancedFeedback> getRecentFeedback() {
        return recentFeedback;
    }

    public void setRecentFeedback(List<EnhancedFeedback> recentFeedback) {
        this.recentFeedback = recentFeedback;
    }
}