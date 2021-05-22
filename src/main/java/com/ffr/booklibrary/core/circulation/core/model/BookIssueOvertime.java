package com.ffr.booklibrary.core.circulation.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Duration;

@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public class BookIssueOvertime {

    private static BookIssueOvertime noOvertime = new BookIssueOvertime(null);

    private Duration overtime;

    private boolean isOvertime() {
        return this.overtime == null;
    }

    public static BookIssueOvertime NoOverTime() {
        return noOvertime;
    }
}
