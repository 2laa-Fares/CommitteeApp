package com.example.tasks.committee10_2018.view;

import com.example.tasks.committee10_2018.model.stp_users;
import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.stpv_results;

public interface AddReferralInterface {
    void result(stpv_results[] results);
    void to(stp_users[] stp_users);
    void add(stpv_case newCase);
}
