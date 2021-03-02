package narsis.xiirpl2.appfilosofi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import narsis.xiirpl2.appfilosofi.R;
import narsis.xiirpl2.appfilosofi.model.DataBarangModel;

public class AdapterDataBarang extends RecyclerView.Adapter<AdapterDataBarang.HolderData> {
    private List<DataBarangModel> mlist;
    private Context ctx;

    public AdapterDataBarang (Context ctx, List<DataBarangModel> mlist){
        this.mlist = mlist;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_barang, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataBarangModel dm = mlist.get(position);
        holder.nama.setText(dm.getNama_barang());
        holder.jenis.setText(dm.getJenis_barang());
        holder.harga.setText(dm.getHarga());
        holder.stok.setText(dm.getStok());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nama, jenis, harga, stok;

        public HolderData (View v){
            super(v);
            nama  = v.findViewById(R.id.tvNama);
            jenis = v.findViewById(R.id.tvJenis);
            harga = v.findViewById(R.id.tvHarga);
            stok  = v.findViewById(R.id.tvStok);
        }
    }
}
