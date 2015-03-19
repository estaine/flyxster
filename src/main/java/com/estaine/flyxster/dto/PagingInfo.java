package com.estaine.flyxster.dto;

/**
 * Created by AndreyRykhalsky on 11.03.15.
 */
public class PagingInfo {
    private int searchId;
    private int startLine;
    private int linesToShow;
    private int resultsFound;

    public PagingInfo(int searchId, int startLine, int linesToShow, int resultsFound) {
        this.searchId = searchId;
        this.startLine = startLine;
        this.linesToShow = linesToShow;
        this.resultsFound = resultsFound;
    }

    public int getSearchId() {
        return searchId;
    }

    public void setSearchId(int searchId) {
        this.searchId = searchId;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getLinesToShow() {
        return linesToShow;
    }

    public void setLinesToShow(int linesToShow) {
        this.linesToShow = linesToShow;
    }

    public int getResultsFound() {
        return resultsFound;
    }

    public void setResultsFound(int resultsFound) {
        this.resultsFound = resultsFound;
    }
}
