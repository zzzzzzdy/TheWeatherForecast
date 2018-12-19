package activitytest.exmaple.com.theweatherforecast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecViewHolder> {
        private List<Data> datas = new ArrayList<Data>();
        private Context mcontext;

        public RecyclerAdapter(List<Data> datas, Context context) {
            this.datas = datas;
            this.mcontext = context;
        }

        @NonNull
        @Override
        public RecViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.item, viewGroup,false);
            return new RecViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecViewHolder recViewHolder, int i) {
            recViewHolder.title1.setText("日期： "+datas.get(i).getTitle1());
            recViewHolder.title2.setText("最"+datas.get(i).getTitle2());
            recViewHolder.title3.setText("风力： "+datas.get(i).getTitle3());
            recViewHolder.title4.setText("最"+datas.get(i).getTitle4());
            recViewHolder.title5.setText("风向： "+datas.get(i).getTitle5());
            recViewHolder.title6.setText(datas.get(i).getTitle6());

        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public class RecViewHolder extends RecyclerView.ViewHolder {
            TextView title1;
            TextView title2;
            TextView title3;
            TextView title4;
            TextView title5;
            TextView title6;


            public RecViewHolder(@NonNull View itemView) {
                super(itemView);
                title1=itemView.findViewById(R.id.t1);
                title2=itemView.findViewById(R.id.t2);
                title3=itemView.findViewById(R.id.t3);
                title4=itemView.findViewById(R.id.t4);
                title5=itemView.findViewById(R.id.t5);
                title6=itemView.findViewById(R.id.t6);
            }
        }
}
