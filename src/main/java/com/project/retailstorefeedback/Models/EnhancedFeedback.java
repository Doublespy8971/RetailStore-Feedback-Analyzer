package com.project.retailstorefeedback.Models;

public class EnhancedFeedback extends FeedbackEntry {
    private String category;
    private String actionableInsight;

    public EnhancedFeedback(FeedbackEntry entry) {
        this.setId(entry.getId());
        this.setCustomer(entry.getCustomer());
        this.setDepartment(entry.getDepartment());
        this.setDate(entry.getDate());
        this.setComment(entry.getComment());
        this.setSentiment(entry.getSentiment());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActionableInsight() {
        return actionableInsight;
    }

    public void setActionableInsight(String actionableInsight) {
        this.actionableInsight = actionableInsight;
    }
}
