package com.example.khaataa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    public TextView data;
    public TextView date;
    public TextView phone;
    public TextView status;
    public TextView amount;
    private ItemClickListener itemClickListener;

    public DataHolder(@NonNull View itemView) {
        super(itemView);
        data=itemView.findViewById(R.id.data);
        date=itemView.findViewById(R.id.date);
        phone=itemView.findViewById(R.id.phone);
        status=itemView.findViewById(R.id.status);
        amount=itemView.findViewById(R.id.amount);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), true);
        return true;
    }

}
public class DataAdapter extends RecyclerView.Adapter<DataHolder>{
    private ArrayList<String> data_office;
    private ArrayList<String> data_date;
    private ArrayList<String> data_amount;
    private ArrayList<String> data_status;
    private ArrayList<String> data_phone;
    private Context context;

    public DataAdapter(ArrayList<String> data_office, ArrayList<String> data_date, ArrayList<String> data_amount, ArrayList<String> data_status, ArrayList<String> data_phone, Context context) {
        this.data_office = data_office;
        this.data_date = data_date;
        this.data_amount = data_amount;
        this.data_status = data_status;
        this.data_phone = data_phone;
        this.context = context;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.data_layout,parent,false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataHolder holder, int position) {
        final String list1=data_office.get(position);
        final String list2=data_date.get(position);
        final String list3=data_amount.get(position);
        final String list4=data_status.get(position);
        final String list5=data_phone.get(position);
        holder.date.setText(list2);
        holder.data.setText(list1);
        holder.amount.setText(list3);
        holder.status.setText(list4);
        holder.phone.setText(list5);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick)
                {
                showDialog(list1,list2);
                }
                else {
                    ConfirmDialog(list5);

                }
            }
            private void ConfirmDialog(final String list5)
            {
                final AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(context.getString(R.string.sure));
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startWhatsAppChat(list5);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
            private void startWhatsAppChat(final String phone)
            {
                String url = "https://api.whatsapp.com/send?phone=" +"91"+ phone;
                try {
                    PackageManager pm = context.getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(context, "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            private void showDialog(final String list1, final String list2) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle(context.getString(R.string.sure1));
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RecordHelper db;
                        db = new RecordHelper(context);
                       boolean ck= db.deleteData(list1,list2,list3,list4,list5);
                       if(ck==true)
                        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                       else
                           Toast.makeText(context, "Not Deleted", Toast.LENGTH_SHORT).show();

                        Intent i=new Intent(context,SeeData.class);
                        Intent j=new Intent(context,FirstActivity.class);

                        Cursor cursor;
                        cursor=db.allData();
                        if(cursor.getCount()==0)
                        {
                            context.startActivity(j);
                            Toast.makeText(context, "Empty Cart", Toast.LENGTH_SHORT).show();
                        }
                        else
                            context.startActivity(i);

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                builder.show();

            }

        });

    }



    @Override
    public int getItemCount() {
        return data_office.size();
    }
}