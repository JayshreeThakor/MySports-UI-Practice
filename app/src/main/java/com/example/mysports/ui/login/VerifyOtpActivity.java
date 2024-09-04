package com.example.mysports.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import com.example.mysports.R;
import com.example.mysports.ui.home.MainActivity;

import java.util.Arrays;
import java.util.List;

public class VerifyOtpActivity extends AppCompatActivity {

    private List<EditText> otpEditTexts;
    private int backgroundDrawableEnabled;
    private int backgroundDrawableDefault;
    private AppCompatButton btnVerifyOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        btnVerifyOtp = findViewById(R.id.btnSubmit);

        btnVerifyOtp.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        backgroundDrawableEnabled = R.drawable.bg_edittext_enabled;
        backgroundDrawableDefault = R.drawable.selector_edittext;

        otpEditTexts = Arrays.asList(
                findViewById(R.id.otpEditText1),
                findViewById(R.id.otpEditText2),
                findViewById(R.id.otpEditText3),
                findViewById(R.id.otpEditText4),
                findViewById(R.id.otpEditText5)
        );
        setupOtpFields();
    }

    private void setupOtpFields() {
        for (int i = 0; i < otpEditTexts.size(); i++) {
            otpEditTexts.get(i).addTextChangedListener(createTextWatcher(i));
            otpEditTexts.get(i).setOnKeyListener(createKeyListener(i));
        }
    }

    private TextWatcher createTextWatcher(final int index) {
        return new TextWatcher() {
            private int previousLength = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previousLength = s != null ? s.length() : 0;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int currentTextLength = s != null ? s.length() : 0;

                if (currentTextLength == 1) {
                    updateAllBackgrounds();
                    if (index < otpEditTexts.size() - 1 && s.toString().trim().equals(s.toString())) {
                        otpEditTexts.get(index + 1).requestFocus();
                    }
                } else if (currentTextLength == 0 && previousLength == 1) {
                    updateAllBackgrounds();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                previousLength = s != null ? s.length() : 0;
            }
        };
    }

    private View.OnKeyListener createKeyListener(final int index) {
        return new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Handle backspace to manage empty fields and adjust backgrounds
                    if (otpEditTexts.get(index).getText().toString().isEmpty() && index > 0) {
                        EditText previousEditText = otpEditTexts.get(index - 1);
                        previousEditText.requestFocus();
                        previousEditText.setText("");
                        updateAllBackgrounds(); // Update backgrounds right after deletion
                        return true;
                    }
                }
                return false;
            }
        };
    }

    private void updateAllBackgrounds() {
        for (EditText editText : otpEditTexts) {
            if (!editText.getText().toString().isEmpty()) {
                editText.setBackgroundResource(backgroundDrawableEnabled);
            } else {
                editText.setBackgroundResource(backgroundDrawableDefault);
            }
        }
    }

}