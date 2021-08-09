package com.example.pst_ta5_g12.adapter;


import android.net.Uri;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.pst_ta5_g12.R;
        import com.example.pst_ta5_g12.object.Libro;
        import java.util.List;

public class AdapterLibro extends RecyclerView.Adapter<AdapterLibro.viewholderlibro> {

    List<Libro> libroList;
    public AdapterLibro(List<Libro> libroList){
        this.libroList=libroList;
    }
    @NonNull
    @Override
    public viewholderlibro onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_libros,parent,false);
        viewholderlibro holder = new viewholderlibro(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderlibro holder, int position) {
        Libro lb=libroList.get(position);
        holder.tvname.setText(lb.getNombre());
        holder.tvautor.setText(lb.getAutor());
        holder.tveditorial.setText(lb.getEditorial());
        holder.imag.setImageURI(Uri.parse(lb.getImagen()));
    }

    @Override
    public int getItemCount() {
        return libroList.size();
    }

    public class viewholderlibro extends RecyclerView.ViewHolder {
        TextView tvname,tvautor,tveditorial;
        ImageView imag;

        public viewholderlibro(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvautor = itemView.findViewById(R.id.tvautor);
            tveditorial = itemView.findViewById(R.id.tveditorial);
            imag = itemView.findViewById(R.id.imagelibro);
        }
    }
}
