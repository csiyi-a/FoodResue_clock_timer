package com.example.foodrescue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

public class CartViewBinder extends ItemViewBinder<Cart, CartViewBinder.UIViewHolder> {

    @NonNull
    @Override
    protected UIViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new UIViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull UIViewHolder holder, @NonNull Cart item) {

        holder.title.setText(item.getName());
        holder.quantity.setText(item.getCount() + "");
        holder.price.setText(item.getPrice()+"");

    }


    public static class UIViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.price)
        TextView price;

        UIViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}
