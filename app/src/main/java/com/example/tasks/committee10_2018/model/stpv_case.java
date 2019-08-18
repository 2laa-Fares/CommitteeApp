package com.example.tasks.committee10_2018.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by 2laa on 4/30/2018.
 */

public class stpv_case implements Serializable {
    public int CASE_NO;
    public String CASE_DATE_H;
    public Integer CASE_TYPE;
    public String INCOMING_NO;
    public String INCOMING_DATE_H;
    public Integer NUMBER_OF_ATTACHMENTS;
    public String CASE_TITLE;
    public Integer CASE_SOURCE;
    public String SOURCE_NO;
    public String SOURCE_DATE_H;
    public Integer CASE_STATUS;
    public String COMPLAINANT_NAME;
    public String COMPLAINANT_DOCUMENT_NO;
    public String COMPLAINANT_HOME_ADDRESS;
    public String COMPLAINANT_JOB_ORG_ADDRESS;
    public String COMPLAINANT_MOBILE_NO;
    public String COMPLAINANT_LAND_PHONE_NO;
    public String COMPLAINANT_RELATIVE_NAME;
    public String COMPLAINANT_RELATIVE_PHONE_NO;
    public String COMPLAINANT_REMARKS;
    public String RESPONDENT_NAME;
    public String RESPONDENT_DOCUMENT_NO;
    public String RESPONDENT_HOME_ADDRESS;
    public String RESPONDENT_JOB_ORG_ADDRESS;
    public String RESPONDENT_MOBILE_NO;
    public String RESPONDENT_LAND_PHONE_NO;
    public String RESPONDENT_RELATIVE_NAME;
    public String RESPONDENT_RELATIVE_PHONE_NO;
    public String RESPONDENT_REMARKS;
    public Integer JUDGE_NO;
    public String USER_NAME;
    public String JUDGEMENT_RECEIVE_DATE_H;
    public String CASE_SUBJECT;
    public String CASE_SUMMARY;
    public Integer CREATE_USER_NO;
    public Integer RESULT;
    public boolean FINISHED;
    public String SourceName;
    public String Statue;
    public String attachment_Type;
    public String COMPLAINANT_NATIONALITY;
    public String RESPONDENT_NATIONALITY;
    public String COMPLAINANT_DOCUMENT_TYPE;
    public String RESPONDENT_DOCUMENT_TYPE;
    public String COMPLAINANT_RELATIVE_TYPE;
    public String RESPONDENT_RELATIVE_TYPE;
    public String COMPLAINANT_GENDER;
    public String RESPONDENT_GENDER;
    public int TransactionCase;
    public String NOTES;
    public String ToUesr;
    public String FromUser;

    /*protected stpv_case(Parcel in) {
        CASE_NO = in.readInt();
        CASE_DATE_H = in.readString();
        if (in.readByte() == 0) {
            CASE_TYPE = null;
        } else {
            CASE_TYPE = in.readInt();
        }
        INCOMING_NO = in.readString();
        INCOMING_DATE_H = in.readString();
        if (in.readByte() == 0) {
            NUMBER_OF_ATTACHMENTS = null;
        } else {
            NUMBER_OF_ATTACHMENTS = in.readInt();
        }
        CASE_TITLE = in.readString();
        if (in.readByte() == 0) {
            CASE_SOURCE = null;
        } else {
            CASE_SOURCE = in.readInt();
        }
        SOURCE_NO = in.readString();
        SOURCE_DATE_H = in.readString();
        if (in.readByte() == 0) {
            CASE_STATUS = null;
        } else {
            CASE_STATUS = in.readInt();
        }
        COMPLAINANT_NAME = in.readString();
        COMPLAINANT_DOCUMENT_NO = in.readString();
        COMPLAINANT_HOME_ADDRESS = in.readString();
        COMPLAINANT_JOB_ORG_ADDRESS = in.readString();
        COMPLAINANT_MOBILE_NO = in.readString();
        COMPLAINANT_LAND_PHONE_NO = in.readString();
        COMPLAINANT_RELATIVE_NAME = in.readString();
        COMPLAINANT_RELATIVE_PHONE_NO = in.readString();
        COMPLAINANT_REMARKS = in.readString();
        RESPONDENT_NAME = in.readString();
        RESPONDENT_DOCUMENT_NO = in.readString();
        RESPONDENT_HOME_ADDRESS = in.readString();
        RESPONDENT_JOB_ORG_ADDRESS = in.readString();
        RESPONDENT_MOBILE_NO = in.readString();
        RESPONDENT_LAND_PHONE_NO = in.readString();
        RESPONDENT_RELATIVE_NAME = in.readString();
        RESPONDENT_RELATIVE_PHONE_NO = in.readString();
        RESPONDENT_REMARKS = in.readString();
        if (in.readByte() == 0) {
            JUDGE_NO = null;
        } else {
            JUDGE_NO = in.readInt();
        }
        USER_NAME = in.readString();
        JUDGEMENT_RECEIVE_DATE_H = in.readString();
        CASE_SUBJECT = in.readString();
        CASE_SUMMARY = in.readString();
        if (in.readByte() == 0) {
            CREATE_USER_NO = null;
        } else {
            CREATE_USER_NO = in.readInt();
        }
        if (in.readByte() == 0) {
            RESULT = null;
        } else {
            RESULT = in.readInt();
        }
        FINISHED = in.readByte() != 0;
        SourceName = in.readString();
        Statue = in.readString();
        attachment_Type = in.readString();
        COMPLAINANT_NATIONALITY = in.readString();
        RESPONDENT_NATIONALITY = in.readString();
        COMPLAINANT_DOCUMENT_TYPE = in.readString();
        RESPONDENT_DOCUMENT_TYPE = in.readString();
        COMPLAINANT_RELATIVE_TYPE = in.readString();
        RESPONDENT_RELATIVE_TYPE = in.readString();
        COMPLAINANT_GENDER = in.readString();
        RESPONDENT_GENDER = in.readString();
        TransactionCase = in.readInt();
        NOTES = in.readString();
        ToUesr = in.readString();
        FromUser = in.readString();
    }

    public static final Creator<stpv_case> CREATOR = new Creator<stpv_case>() {
        @Override
        public stpv_case createFromParcel(Parcel in) {
            return new stpv_case(in);
        }

        @Override
        public stpv_case[] newArray(int size) {
            return new stpv_case[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(CASE_NO);
        parcel.writeString(CASE_DATE_H);
        parcel.writeInt(CASE_TYPE);
        parcel.writeString(INCOMING_NO);
        parcel.writeString(INCOMING_DATE_H);
        parcel.writeInt(NUMBER_OF_ATTACHMENTS);
        parcel.writeString(CASE_TITLE);
        parcel.writeInt(CASE_SOURCE);
        parcel.writeString(SOURCE_NO);
        parcel.writeString(SOURCE_DATE_H);
        parcel.writeInt(CASE_STATUS);
        parcel.writeString(COMPLAINANT_NAME);
        parcel.writeString(COMPLAINANT_DOCUMENT_NO);
        parcel.writeString(COMPLAINANT_HOME_ADDRESS);
        parcel.writeString(COMPLAINANT_JOB_ORG_ADDRESS);
        parcel.writeString(COMPLAINANT_MOBILE_NO);
        parcel.writeString(COMPLAINANT_LAND_PHONE_NO);
        parcel.writeString(COMPLAINANT_RELATIVE_NAME);
        parcel.writeString(COMPLAINANT_RELATIVE_PHONE_NO);
        parcel.writeString(COMPLAINANT_REMARKS);
        parcel.writeString(RESPONDENT_NAME);
        parcel.writeString(RESPONDENT_DOCUMENT_NO);
        parcel.writeString(RESPONDENT_HOME_ADDRESS);
        parcel.writeString(RESPONDENT_JOB_ORG_ADDRESS);
        parcel.writeString(RESPONDENT_MOBILE_NO);
        parcel.writeString(RESPONDENT_LAND_PHONE_NO);
        parcel.writeString(RESPONDENT_RELATIVE_NAME);
        parcel.writeString(RESPONDENT_RELATIVE_PHONE_NO);
        parcel.writeString(RESPONDENT_REMARKS);
        parcel.writeInt(JUDGE_NO);
        parcel.writeString(USER_NAME);
        parcel.writeString(JUDGEMENT_RECEIVE_DATE_H);
        parcel.writeString(CASE_SUBJECT);
        parcel.writeString(CASE_SUMMARY);
        parcel.writeInt(CREATE_USER_NO);
        parcel.writeInt(RESULT);
        parcel.writeInt(FINISHED?1:0);
        parcel.writeString(SourceName);
        parcel.writeString(Statue);
        parcel.writeString(attachment_Type);
        parcel.writeString(COMPLAINANT_NATIONALITY);
        parcel.writeString(RESPONDENT_NATIONALITY);
        parcel.writeString(COMPLAINANT_DOCUMENT_TYPE);
        parcel.writeString(RESPONDENT_DOCUMENT_TYPE);
        parcel.writeString(COMPLAINANT_RELATIVE_TYPE);
        parcel.writeString(RESPONDENT_RELATIVE_TYPE);
        parcel.writeString(COMPLAINANT_GENDER);
        parcel.writeString(RESPONDENT_GENDER);
        parcel.writeInt(TransactionCase);
        parcel.writeString(NOTES);
        parcel.writeString(ToUesr);
        parcel.writeString(FromUser);
    }*/
}
