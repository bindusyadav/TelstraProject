package com.example.telstraproject.activity;

import android.app.Application;
import android.app.Instrumentation;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.telstraproject.R;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;

public class MainActivityTest {

    @Mock
    MainActivity mainActivity;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testTitle(){
        mainActivity.title = "About Canada";
        Assert.assertTrue("check", mainActivity.title.contains("About Canada"));
    }

    @Test
    public void checkRefreshClicked(){
        final View view = mainActivity.findViewById(R.id.refresh_btn);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.requestFocus();
                view.callOnClick();
            }
        });
    }




}
