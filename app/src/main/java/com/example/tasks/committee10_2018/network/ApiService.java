package com.example.tasks.committee10_2018.network;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("login/")
    Single<String> login(@Query("name") String userName, @Query("pass") String pass);

    @GET("Issues/")
    Single<String[]> issues(@Query("categorie_num") String cat, @Query("userID") String userID);

    @GET("SearchIssue/")
    Single<String> searchIssue(@Query("categorie_num") String cat, @Query("userID") String userID, @Query("caseNo") String no);

    @GET("SearchIssues/")
    Single<String[]> searchIssues(@Query("categorie_num") String cat, @Query("userID") String userID, @Query("Is_Finished") String finished, @Query("Result") String result);

    @GET("File/")
    Single<String[]> files(@Query("pass") String pass);

    @GET("ReferredTo/")
    Single<String[]> referredTo(@Query("CATEGORY_NO") String cat);

    @GET("Result/")
    Single<String[]> result(@Query("CATEGORY_NO") String cat);

    @GET("IssueStatus/")
    Single<String[]> status();

    @GET("Referrals/")
    Single<String> addReferral(@Query("UID") String userID, @Query("caseNo") String caseNo, @Query("judgeNo") String judgeNo, @Query("caseType") String caseType, @Query("result") String result, @Query("notes") String notes);

    @GET("File/")
    Single<String[]> getFiles(@Query("pass") String caseNo);

    @GET("ViewReferrales/")
    Single<String[]> getReferrals(@Query("CaseNO") String caseNo);

    @GET("Actions/")
    Single<String[]> getActions(@Query("CaseNO") String caseNo);

    @GET("Action/")
    Single<String> addAction(@Query("CASE_NO") String caseNo, @Query("ACTION_TAKEN") String taken, @Query("ACTION_NOTE") String note, @Query("ACTION_USERNO") String userID );

    @GET("ActionToken/")
    Single<String[]> actionToken();
}
