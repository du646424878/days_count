package com.example.mydays_count;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by QiWangming on 2015/6/13.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder>{
    private List<jinianri> days;
    private Context context;
    private LayoutInflater mLayoutInflater;


    public RecyclerViewAdapter(List<jinianri> days, Context context) {
        this.days = days;
        this.context= context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }


    //自定义ViewHolder类
    public class myViewHolder extends RecyclerView.ViewHolder{


        TextView miaoshu;
        TextView daojishi;

        myViewHolder(final View itemView) {
            super(itemView);
            miaoshu= (TextView) itemView.findViewById(R.id.tv);
            daojishi= (TextView) itemView.findViewById(R.id.daysss);

        }


    }

    //定义结束

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            return new myViewHolder(mLayoutInflater.inflate(R.layout.item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(myViewHolder mv, int i) {
        long now = System.currentTimeMillis();
        System.out.println(new Date(now));
        if(days.get(i).getDate()<now){
            mv.miaoshu.setText(days.get(i).getDes()+"已经过去");
            long targetdate = days.get(i).getDate();
            System.out.println(new Date(targetdate));
            int between_days=(int)((now-targetdate)/(1000*3600*24));
            mv.daojishi.setText(between_days+"");
        }
        else{
            mv.miaoshu.setText("距离"+days.get(i).getDes()+"还有");
            long targetdate = days.get(i).getDate();
            System.out.println(new Date(targetdate));
            int between_days=(int)(((targetdate-now)/(1000*3600*24))+1);

            mv.daojishi.setText(between_days+"");
        }
    }
    //  添加数据
    public void addData(int position,jinianri jnr) {
//      在list中添加数据，并通知条目加入一条
        days.add(jnr);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

}
