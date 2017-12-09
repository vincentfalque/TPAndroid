package io.falque.vincent.tp3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private List<Message> mData;

    MessageAdapter(List<Message> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_messages, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<Message> data) {
        mData = data;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mUserTextView;
        TextView mContentTextView;
        ImageView mUserImageView;

        ViewHolder(View itemView) {
            super(itemView);

            mUserTextView = itemView.findViewById(R.id.userTextView);
            mContentTextView = itemView.findViewById(R.id.contentTextView);
            mUserImageView = itemView.findViewById(R.id.avatarProfile);
        }

        void setData(Message message) {
            mUserTextView.setText(message.userName + ": ");
            mContentTextView.setText(message.content);
            Glide.with(mUserImageView.getContext()).load(Utils.GRAVATAR_PREFIX + Utils.md5(message.userEmail) + ".jpg")
                    .apply(RequestOptions.circleCropTransform()).into(mUserImageView);
        }
    }
}
