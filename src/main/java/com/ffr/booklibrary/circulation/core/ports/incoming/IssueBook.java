package com.ffr.booklibrary.circulation.core.ports.incoming;

import com.ffr.booklibrary.circulation.core.model.IssueBookCommand;

public interface IssueBook {

    void issueBook(IssueBookCommand issueBookCommand);
}
