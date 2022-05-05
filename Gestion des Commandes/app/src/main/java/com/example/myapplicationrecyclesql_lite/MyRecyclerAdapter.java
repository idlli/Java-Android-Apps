package com.example.myapplicationrecyclesql_lite;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

//public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyView> {
//    List<Client> Cl;
//
//    public MyRecyclerAdapter(List<Client> cl) {
//        Cl = cl;
//    }
//
//    @NonNull
//    @Override
//    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View v = layoutInflater.inflate(R.layout.client_view, parent,false);
//        return new MyView(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyView holder, int position) {
//        holder.Show(Cl.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return Cl.size();
//    }
//
//    class MyView extends RecyclerView.ViewHolder {
//        int men[] = {R.drawable.arab_man, R.drawable.man, R.drawable.afro, R.drawable.grandfather, R.drawable.businessman};
//        int woman[] = {R.drawable.girl, R.drawable.girl_1, R.drawable.arab_women, R.drawable.african, R.drawable.girl_2};
//        int random;
//
//        ImageView img;
//        TextView nom;
//        TextView ville;
//        TextView sexe;
//
//
//        public MyView(@NonNull View itemView) {
//            super(itemView);
//            img = itemView.findViewById(R.id.Vector);
//            nom = itemView.findViewById(R.id.Nom);
//            ville = itemView.findViewById(R.id.Ville);
//            sexe = itemView.findViewById(R.id.Sexe);


//
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    MainActivity.MyActivity.position = getAdapterPosition();
//                    MainActivity.MyActivity.ReDirect();
//                    return false;
//                }
//            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //MainActivity.MyActivity.position = getAdapterPosition();
//                   MainActivity.MyActivity.ReDirect();
//
//                }
//            });

//        }
//
//        void Show(Client Cl) {
//            random = (int)(Math.random()*5);
//            if (Cl.getSexe().equalsIgnoreCase("homme")) img.setImageResource(men[random]);
//            else img.setImageResource(woman[random]);
//            nom.setText(Cl.getNom());
//            ville.setText(Cl.getVille());
//            sexe.setText(Cl.getSexe());
//        }
    //}
//}
