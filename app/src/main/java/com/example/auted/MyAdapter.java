package com.example.auted;

        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.cardview.widget.CardView;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recstdId.setText(dataList.get(position).getDataStudentId());
        holder.recfirstname.setText(dataList.get(position).getDataFirstName());
        holder.reclastname.setText(dataList.get(position).getDataLastName());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,welcomScreen.class);
                intent.putExtra("",dataList.get(holder.getAdapterPosition()).getDataStudentId());
                intent.putExtra("",dataList.get(holder.getAdapterPosition()).getDataFirstName());
                intent.putExtra("",dataList.get(holder.getAdapterPosition()).getDataLastName());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolder extends  RecyclerView.ViewHolder{
    TextView recstdId,recfirstname,reclastname;
    CardView recCard;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recstdId=itemView.findViewById(R.id.recstdId);
        recfirstname=itemView.findViewById(R.id.recfirstname);
        reclastname=itemView.findViewById(R.id.reclastname);
        recCard=itemView.findViewById(R.id.recCard);
    }
}