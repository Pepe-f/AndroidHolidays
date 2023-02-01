package ru.kopylov.holidaysapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.kopylov.holidaysapp.model.HolidaysListInfo;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
    final private ListItemClickListener onClickListener;
    private HolidaysListInfo holidaysListInfo;
    private int length;

    public Adapter(HolidaysListInfo holidaysListInfo, int length, ListItemClickListener listener) {
        this.length = length;
        this.holidaysListInfo = holidaysListInfo;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.holiday_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return length;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView holidayName;
        private TextView holidayDate;

        public ViewHolder(View itemView) {
            super(itemView);
            holidayName = itemView.findViewById(R.id.holiday_name);
            holidayDate = itemView.findViewById(R.id.holiday_date);

            holidayName.setOnClickListener(this);
        }

        public void bind(int position) {
            String holidayName = holidaysListInfo.holidaysListInfo[position].getHolidayName();
            String holidayDate = holidaysListInfo.holidaysListInfo[position].getHolidayDate();
            this.holidayName.setText(holidayName);
            this.holidayDate.setText(holidayDate);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onClickListener.onListItemClick(position);
        }
    }
}
