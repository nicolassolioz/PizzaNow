package com.mycompany.pizzanow.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.database.entity.PosEntity;

import com.mycompany.pizzanow.model.Pizza;
import com.mycompany.pizzanow.util.RecyclerViewItemClickListener;

public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<T> mData;
    private RecyclerViewItemClickListener mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;
        ViewHolder(TextView textView) {
            super(textView);
            mTextView = textView;
        }
    }

    public RecyclerAdapter(RecyclerViewItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(view -> mListener.onItemClick(view, viewHolder.getAdapterPosition()));
        v.setOnLongClickListener(view -> {
            mListener.onItemLongClick(view, viewHolder.getAdapterPosition());
            return true;
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        T item = mData.get(position);
        if (item.getClass().equals(PizzaEntity.class))
            holder.mTextView.setText(((PizzaEntity) item).getNom()+"\t "+((PizzaEntity) item).getPrix()+"\n"+((PizzaEntity) item).getDescription());
        if (item.getClass().equals(CollaborateurEntity.class))
            holder.mTextView.setText(((CollaborateurEntity) item).getPrenomCollab() + " " + ((CollaborateurEntity) item).getNomCollab());
        if (item.getClass().equals(PosEntity.class))
            holder.mTextView.setText(((PosEntity) item).getNom()+" à "+((PosEntity)item).getLocalite());
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public void setData(final List<T> data) {
        if (mData == null) {
            mData = data;
            notifyItemRangeInserted(0, data.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mData.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof PizzaEntity) {
                        return ((PizzaEntity) mData.get(oldItemPosition)).getIdPizza() == (((PizzaEntity) data.get(newItemPosition)).getIdPizza());
                    }
                    if (mData instanceof CollaborateurEntity) {
                        return ((CollaborateurEntity) mData.get(oldItemPosition)).getIdCollab()==(
                                ((CollaborateurEntity) data.get(newItemPosition)).getIdCollab());
                    }
                    if (mData instanceof PosEntity) {
                        return ((PosEntity) mData.get(oldItemPosition)).getIdFiliale() == (((PosEntity) data.get(newItemPosition)).getIdFiliale());
                    }
                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof PizzaEntity) {
                        PizzaEntity newAccount = (PizzaEntity) data.get(newItemPosition);
                        PizzaEntity oldAccount = (PizzaEntity) mData.get(newItemPosition);
                        return newAccount.getIdPizza()==(oldAccount.getIdPizza())
                                && Objects.equals(newAccount.getNom(), oldAccount.getNom())
                                && Objects.equals(newAccount.getDescription(), oldAccount.getDescription())
                                && newAccount.getPrix()==(oldAccount.getPrix());
                    }
                    if (mData instanceof CollaborateurEntity) {
                        CollaborateurEntity newClient = (CollaborateurEntity) data.get(newItemPosition);
                        CollaborateurEntity oldClient = (CollaborateurEntity) mData.get(newItemPosition);
                        return Objects.equals(newClient.getIdCollab(), oldClient.getIdCollab())
                                && Objects.equals(newClient.getNomCollab(), oldClient.getNomCollab())
                                && Objects.equals(newClient.getPrenomCollab(), oldClient.getPrenomCollab())
                                && newClient.getIdPosCollab()==(oldClient.getIdPosCollab());
                    }
                    if (mData instanceof PosEntity) {
                        PosEntity newAccount = (PosEntity) data.get(newItemPosition);
                        PosEntity oldAccount = (PosEntity) mData.get(newItemPosition);
                        return newAccount.getIdFiliale()==(oldAccount.getIdFiliale())
                                && Objects.equals(newAccount.getNom(), oldAccount.getNom())
                                && Objects.equals(newAccount.getLocalite(), oldAccount.getLocalite())
                                && Objects.equals(newAccount.getEmail(), oldAccount.getEmail())
                                && Objects.equals(newAccount.getNPA(), oldAccount.getNPA())
                                && newAccount.getAdresse()==(oldAccount.getAdresse());
                    }
                    return false;
                }
            });
            mData = data;
            result.dispatchUpdatesTo(this);
        }
    }
}
