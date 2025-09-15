package com.example.login_logout.newui.old_activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login_logout.R;
import com.example.login_logout.data.Service.ApiServices;
import com.example.login_logout.data.api2.UserApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnSendEmail;
    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtEmail = findViewById(R.id.edtEmail);
        btnSendEmail = findViewById(R.id.btnForgot);

        userApi = ApiServices.getInstance().getUserApi();

        btnSendEmail.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, String> emailData = new HashMap<>();
            emailData.put("email", email);

            userApi.forgotPassword(emailData).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ForgotPasswordActivity.this, "Email reset đã được gửi!", Toast.LENGTH_SHORT).show();

                        // Chuyển sang ResetPasswordActivity và gửi kèm email
                        Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Gửi email thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ForgotPasswordActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
