package com.onlineCustomerServiceCenter.admin.exceptions;

public class IssueNotFoundException extends RuntimeException {

    public IssueNotFoundException(String message) {
        super(message);
    }
}
