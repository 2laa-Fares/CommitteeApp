package com.example.tasks.committee10_2018.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tasks.committee10_2018.R;
import com.example.tasks.committee10_2018.app.App;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CaseTabsDetialsActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    android.support.v4.view.ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_tabs_detials);
        App.setContext(this);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        ActionFragmentActivity showActions = new ActionFragmentActivity();
        showActions.setArguments(bundle);
        pagerAdapter.addFragments(showActions);

        Attachments attachments = new Attachments();
        attachments.setArguments(bundle);
        pagerAdapter.addFragments(attachments);

        ReferralFragmentActivity showReferralsFragment = new ReferralFragmentActivity();
        showReferralsFragment.setArguments(bundle);
        pagerAdapter.addFragments(showReferralsFragment);

        IssueSecondParty secondParty = new IssueSecondParty();
        secondParty.setArguments(bundle);
        pagerAdapter.addFragments(secondParty);

        IssueFirstParty firstParty = new IssueFirstParty();
        firstParty.setArguments(bundle);
        pagerAdapter.addFragments(firstParty);

       IssueContent caseInfoFragment = new IssueContent();
        caseInfoFragment.setArguments(bundle);
        pagerAdapter.addFragments(caseInfoFragment);

        viewPager.setPageTransformer(true, new CubeInDepthTransformation());
        viewPager.setAdapter(pagerAdapter);

        String type = bundle.getString("tabType");
        if (type != null && type.equals("caseInfo")) {
            viewPager.setCurrentItem(5);
        } else if (type != null && type.equals("caseFirstParty")) {
            viewPager.setCurrentItem(4);
        } else if (type != null && type.equals("caseSecoundParty")) {
            viewPager.setCurrentItem(3);
        } else if (type != null && type.equals("referrals")) {
            viewPager.setCurrentItem(2);
        } else if (type != null && type.equals("CaseAttachment")) {
            viewPager.setCurrentItem(1);
        } else if (type != null && type.equals("actions")) {
            viewPager.setCurrentItem(0);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CaseTabsDetialsActivity.this, CaseTabs.class);
        Bundle bundle = getIntent().getExtras();
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }
}
