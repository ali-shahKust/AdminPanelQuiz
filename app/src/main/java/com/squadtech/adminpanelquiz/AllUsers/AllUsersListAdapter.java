package com.squadtech.adminpanelquiz.AllUsers;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squadtech.adminpanelquiz.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AllUsersListAdapter extends RecyclerView.Adapter<AllUsersListAdapter.Viewholder>
{
    private Context mContext;
    private ArrayList<Alluserslist_Model> alist;

    public AllUsersListAdapter(Context mContext, ArrayList<Alluserslist_Model> alist)
    {
        this.mContext = mContext;
        this.alist = alist;
    }

    @Override
    public Viewholder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_users_list, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder( Viewholder holder, int position)
    {
        Alluserslist_Model modelofusers = alist.get(position);
        holder.username.setText(modelofusers.getUser_name());
        holder.email.setText(modelofusers.getUser_email());
        Picasso.get().load(modelofusers.getUser_dp()).placeholder(R.drawable.ic_menu_camera).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return alist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView username, email;

        public Viewholder( View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.user_profile_img);
            username = (TextView)itemView.findViewById(R.id.username_id);
            email = (TextView)itemView.findViewById(R.id.useremail_id);

        }
    }
}
