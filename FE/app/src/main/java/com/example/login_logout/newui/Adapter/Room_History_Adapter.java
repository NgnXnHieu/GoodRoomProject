package com.example.login_logout.newui.Adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login_logout.R;
import com.example.login_logout.data.Service.ApiServices;
import com.example.login_logout.newui.Api.BookingApi;
import com.example.login_logout.newui.Model.BookingBill;

import java.util.List;
import java.util.Random;

import retrofit2.Call;

public class Room_History_Adapter extends RecyclerView.Adapter<Room_History_Adapter.RoomViewHolder> {

    private List<BookingBill> bookingBillList;

    // Mảng chứa các ID tài nguyên ảnh từ home1 đến home18
    private final int[] homeImages = {
            R.drawable.home1, R.drawable.home2, R.drawable.home3, R.drawable.home4,
            R.drawable.home5, R.drawable.home6, R.drawable.home7, R.drawable.home8,
            R.drawable.home9, R.drawable.home10, R.drawable.home11, R.drawable.home12,
            R.drawable.home13, R.drawable.home14, R.drawable.home15, R.drawable.home16,
            R.drawable.home17, R.drawable.home18
    };

    public Room_History_Adapter(List<BookingBill> bookingBillList) {
        this.bookingBillList = bookingBillList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        BookingBill booking = bookingBillList.get(position);

        holder.txtTitle.setText(booking.getRoomName());
        holder.txtAddress.setText("Địa chỉ: " + booking.getAddress());
        holder.txtStatus.setText("Ngày nhận phòng: " + booking.getCheckInDate());
        holder.txtTotal.setText("Tổng tiền: " + booking.getTotalPrice() + " USD");
        holder.txtDeposit.setText("Tiền cọc: " + booking.getDepositPrice() + " USD");

        // ảnh ngẫu nhiên
        int randomImageIndex = getRandomImageIndex();
        holder.imgHomestay.setImageResource(homeImages[randomImageIndex]);

        holder.imgOptions.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
            popupMenu.getMenuInflater().inflate(R.menu.menu_room_history, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.menu_cancel_room) {

                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Xác nhận hủy phòng")
                            .setMessage("Bạn có chắc muốn hủy phòng: " + booking.getRoomName() + " ?")
                            .setPositiveButton("Hủy phòng", (dialog, which) -> {
                                // Gọi API hủy phòng
                                BookingApi api = ApiServices.getInstance().getBookingApi();
                                Call<Void> call = api.deleteBooking(booking.getBookingId());

                                call.enqueue(new retrofit2.Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, retrofit2.Response<Void> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(v.getContext(),
                                                    "Đã hủy phòng: " + booking.getRoomName(),
                                                    Toast.LENGTH_SHORT).show();

                                            // Xóa item khỏi danh sách
                                            int pos = holder.getAdapterPosition();
                                            if (pos != RecyclerView.NO_POSITION) {
                                                bookingBillList.remove(pos);
                                                notifyItemRemoved(pos);
                                                notifyItemRangeChanged(pos, bookingBillList.size());
                                            }
                                        } else {
                                            Toast.makeText(v.getContext(),
                                                    "Hủy phòng thất bại (mã " + response.code() + ")",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(v.getContext(),
                                                "Lỗi kết nối: " + t.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            })
                            .setNegativeButton("Thoát", (dialog, which) -> {
                                dialog.dismiss();
                            })
                            .show();

                    return true;
                }
                return false;
            });

            popupMenu.show();
        });
    }


    @Override
    public int getItemCount() {
        return bookingBillList != null ? bookingBillList.size() : 0;
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHomestay, imgOptions;
        TextView txtTitle, txtAddress, txtStatus, txtTotal, txtDeposit;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHomestay = itemView.findViewById(R.id.imgHomestay);
            imgOptions = itemView.findViewById(R.id.imgOptions); // icon 3 chấm
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtTotal = itemView.findViewById(R.id.txtTotal);
            txtDeposit = itemView.findViewById(R.id.txtDeposit);
        }
    }

    // Hàm tạo chỉ số ngẫu nhiên từ 0 đến 17
    private int getRandomImageIndex() {
        Random random = new Random();
        return random.nextInt(homeImages.length);
    }
}
