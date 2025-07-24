package com.example.javathread;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

//public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
//
//    private List<Post> postList;
//
//    public PostAdapter(List<Post> postList) {
//        this.postList = postList;
//    }
//
//    public void setPostList(List<Post> posts) {
//        this.postList = posts;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item, parent, false);
//        return new PostViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
//        Post post = postList.get(position);
//        holder.titleText.setText(post.getTitle());
//        holder.bodyText.setText(post.getBody());
//    }
//
//    @Override
//    public int getItemCount() {
//        return postList == null ? 0 : postList.size();
//    }
//
//    public static class PostViewHolder extends RecyclerView.ViewHolder {
//        TextView titleText, bodyText;
//
//        public PostViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleText = itemView.findViewById(R.id.titleText);
//            bodyText = itemView.findViewById(R.id.bodyText);
//        }
//    }
//}

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Comment> commentList;

    public PostAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public void setComments(List<Comment> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.nameText.setText(comment.getName());
        holder.emailText.setText(comment.getEmail());
        holder.bodyText.setText(comment.getBody());
    }

    @Override
    public int getItemCount() {
        return commentList == null ? 0 : commentList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, emailText, bodyText;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            emailText = itemView.findViewById(R.id.emailText);
            bodyText = itemView.findViewById(R.id.bodyText);
        }
    }
}
