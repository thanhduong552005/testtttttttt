package com.example.khkt_2023.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.khkt_2023.R;

import java.io.Serializable;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> implements Serializable {
//public class SuggestionAdapter implements Serializable {

    private Suggestion[] localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView getTitle() {
            return title;
        }
        public TextView getContent() {
            return content;
        }
        public Button getButton() {
            return button;
        }

        private final TextView title;
        private final TextView content;
        private final Button button;

        public ViewHolder(View view) {
            super(view);
            //Define click listener for the ViewHolder's View

            title = (TextView) view.findViewById(R.id.suggest_title);
            content = (TextView) view.findViewById(R.id.suggest_content);
            button = (Button) view.findViewById(R.id.suggest_button);
        }
    }

    public SuggestionAdapter(Suggestion[] dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Tạo một dạng xem mới, xác định giao diện người dùng của mục danh sách
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.suggestion_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Lấy phần tử từ tập dữ liệu của bạn tại vị trí này và thay thế phần tử
        // nội dung của chế độ xem với phần tử đó
        viewHolder.getTitle().setText(localDataSet[position].getTitle());
        viewHolder.getContent().setText(localDataSet[position].getContent());
    }

    // Trả về kích thước tập dữ liệu của bạn (được gọi bởi trình quản lý bố cục)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}