package com.example.products;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    Context ctx;
    List<Product> list;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context ctx, List<Product> list) {
        this.ctx = ctx;
        this.list = list;
        layoutInflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.item_showproduct,parent,false);
        return new ViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Product product = list.get(position);
        holder.tvcount.setText(position+1+" ");
        holder.tvtype.setText(product.getType());
        holder.tvprice.setText(product.getPrice());
        holder.tvcountry.setText(product.getCountry());
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,MainDelete.class);
                intent.putExtra("id_delete",product.getId());
                ctx.startActivity(intent);
            }
        });
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ctx,MainUpdate.class);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvcount,tvtype,tvprice,tvcountry;
        Button btnupdate,btndelete;
        CustomAdapter adapter;
        public ViewHolder(@NonNull View itemView , CustomAdapter adt) {
            super(itemView);
            tvcount = itemView.findViewById(R.id.tvcount);
            tvtype = itemView.findViewById(R.id.tvtype);
            tvcountry = itemView.findViewById(R.id.tvcountry);
            tvprice = itemView.findViewById(R.id.tvprice);

            btndelete = itemView.findViewById(R.id.btndelete);
            btnupdate = itemView.findViewById(R.id.btnupdate);


            this.adapter=adt;
        }
    }
}
