package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.change.neatapp.R;
//import com.example.culdesac.R;

import java.util.ArrayList;

import Models.MessageModel;

public class ChatAdapter extends RecyclerView.Adapter {

    ArrayList<MessageModel> messageModels;
    Context context;
    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModels,Context context){
        this.messageModels = messageModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE){
            //View view = LayoutInflater.from(context).inflate(R.layout.sample_sender, parent, attachToRoot:false);
            //return new SenderViewVolder(view);
        }
        else{
            //View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver, parent, attachToRoot:false);
            //return new RecieverViewVolder(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel = messageModels.get(position);

        if(holder.getClass() == SenderViewVolder.class){
            ((SenderViewVolder)holder).senderMsg.setText(messageModel.getMessage());
        }
        else{
            ((RecieverViewVolder)holder).recieverMsg.setText(messageModel.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecieverViewVolder extends RecyclerView.ViewHolder {

        TextView recieverMsg , recieverTime;
        public RecieverViewVolder(@NonNull View itemView) {
            super(itemView);
            //recieverMsg = itemView.findViewById(R.id.receiverText);
            //recieverTime = itemView.findViewById(R.id.receiverTime);


        }
    }

    public class SenderViewVolder extends RecyclerView.ViewHolder {

        TextView senderMsg , senderTime;
        public SenderViewVolder(@NonNull View itemView) {
            super(itemView);
            //senderMsg = itemView.findViewById(R.id.senderText);
            //senderTime = itemView.findViewById(R.id.senderTime);
        }
    }
}
