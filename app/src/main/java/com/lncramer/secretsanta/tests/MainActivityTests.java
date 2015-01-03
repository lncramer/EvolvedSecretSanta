package com.lncramer.secretsanta.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.lncramer.secretsanta.R;
import com.lncramer.secretsanta.activities.MainActivity;
import com.lncramer.secretsanta.services.ICreateRow;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity _activity;
    private ICreateRow _rowCreator;
    private EditText _nameElement;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    // TODO: Make this actually do something
    @Module(injects = MainActivity.class)
    final class TestModule {
        @Provides ICreateRow provideRowCreator() {
            _rowCreator = mock(ICreateRow.class);
            return _rowCreator;
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        _activity = getActivity();
        _nameElement = (EditText) _activity.findViewById(R.id.name);
    }

    public void test_preconditions() {
        assertNotNull("_activity is null", _activity);
        assertNotNull("_nameElement is null", _nameElement);
    }

    // TODO: Unbreakify
//    public void test_should_not_create_row_when_name_is_invalid() {
////        _nameElement.setText(" ");
//        String name = _nameElement.getText().toString();
//
//        assertEquals(name, "");
//
//        _activity.addName(new Button(_activity));
//
//        verify(_rowCreator, never()).createRow(name, _activity);
//    }
}
