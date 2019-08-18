package com.example.tasks.committee10_2018.repositories;

public interface AttachmentsRepository {
    void getAttachments(OnRequestCompletedListener listener , String caseNo);
}
