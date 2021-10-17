package com.ffr.booklibrary.circulation.core.application.ports.incoming;

public interface IssueBook {

    void issueBook(IssueBookCommand issueBookCommand);
}
