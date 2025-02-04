package com.example.pitchify_main.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pitchify_main.R;
import com.example.pitchify_main.admin_staffoverviewperformance.staff_performance;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> firstname;

    public CustomAdapter(Context context, ArrayList<String> firstname) {
        this.context = context;
        this.firstname = firstname;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String firstName = firstname.get(position);
        holder.vendor_name_txt.setText(firstName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, staff_performance.class);
                intent.putExtra("USER_FIRST_NAME", firstName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return firstname.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vendor_name_txt;
        ImageView imageView24;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vendor_name_txt = itemView.findViewById(R.id.vendor_name_txt);
            imageView24 = itemView.findViewById(R.id.imageView24);
        }
    }
}
