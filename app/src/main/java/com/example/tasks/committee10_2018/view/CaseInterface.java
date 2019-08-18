package com.example.tasks.committee10_2018.view;

import com.example.tasks.committee10_2018.model.stpv_case;
import com.example.tasks.committee10_2018.model.syscod;

public interface CaseInterface {
    void getCases(stpv_case[] cases);
    void noCases(String message);
    void getCase(stpv_case stpv_case);
    void noCase(String message);
    void status(syscod[] status);
}
