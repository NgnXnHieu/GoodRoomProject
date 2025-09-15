package com.example.login_logout.presentation.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.login_logout.R;
import com.example.login_logout.data.Service.ApiServices;
import com.example.login_logout.data.api2.UserApi;
import com.example.login_logout.data.model2.UserDTO;
import com.example.login_logout.presentation.view.Login;
import com.example.login_logout.newui.old_activity.ResetPasswordActivity;
import com.example.login_logout.utils.PreferencesManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private TextView tvUserName, tvUserEmail, tvUserBadge;
    private Button logoutBTN, btnChangePassword;
    private PreferencesManager preferencesManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        preferencesManager = new PreferencesManager(requireContext());

        // Ánh xạ view
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserEmail = view.findViewById(R.id.tvUserEmail);
        tvUserBadge = view.findViewById(R.id.tvUserBadge);
        logoutBTN = view.findViewById(R.id.logoutBTN);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);

        displayUserInfo();

        // Đăng xuất
        logoutBTN.setOnClickListener(v -> handleLogout());

        // Đổi mật khẩu theo luồng bạn muốn
        btnChangePassword.setOnClickListener(v -> handleChangePassword());

        return view;
    }

    private void displayUserInfo() {
        String userName = preferencesManager.getUserName();
        String email = preferencesManager.getEmail();

        if (userName != null && !userName.isEmpty()) {
            tvUserName.setText(userName);
        }

        if (email != null && !email.isEmpty()) {
            tvUserEmail.setText(email);
        }

        tvUserBadge.setText("Người dùng");
    }

    private void handleLogout() {
        preferencesManager.clearUser();

        Intent intent = new Intent(getActivity(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();

        Toast.makeText(requireContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
    }

    private void handleChangePassword() {
        String email = preferencesManager.getEmail();

        if (email == null || email.isEmpty()) {
            Toast.makeText(getContext(), "Không tìm thấy email trong Preferences!", Toast.LENGTH_SHORT).show();
            return;
        }

        UserApi userApi = ApiServices.getInstance().getUserApi();

        // ✅ Gửi code về mail
        Map<String, String> data = new HashMap<>();
        data.put("email", email);

        userApi.forgotPassword(data).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Mã reset đã được gửi!", Toast.LENGTH_SHORT).show();

                    // 👉 Chuyển sang ResetPasswordActivity và truyền email đi kèm
                    Intent intent = new Intent(requireContext(), ResetPasswordActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);

                } else {
                    Toast.makeText(getContext(), "Không gửi được mã reset!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
