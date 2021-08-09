package com.example.pst_ta5_g12.ui.home;

        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.widget.SearchView;
        import androidx.fragment.app.Fragment;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProvider;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.pst_ta5_g12.R;
        import com.example.pst_ta5_g12.adapter.AdapterLibro;
        import com.example.pst_ta5_g12.adapter.adapterPelicula;
        import com.example.pst_ta5_g12.databinding.FragmentHomeBinding;
        import com.example.pst_ta5_g12.object.Libro;
        import com.example.pst_ta5_g12.object.Pelicula;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.sql.DatabaseMetaData;
        import java.util.ArrayList;
        import java.util.Locale;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    DatabaseReference ref;
    ArrayList<Libro> list;
    RecyclerView rv;
    SearchView searchView;
    AdapterLibro adapter;
    LinearLayoutManager lm;

    private void buscar(String s) {
        ArrayList<Libro> mylist= new ArrayList<>();
        for(Libro obj:list){
            if(obj.getAutor().toLowerCase().contains(s.toLowerCase())){
                mylist.add(obj);
            }
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ref= FirebaseDatabase.getInstance().getReference().child("Libros");
       // rv = root.findViewById(R.id.rv);
        rv = binding.rv;
        searchView = binding.search;
        //searchView = root.findViewById(R.id.search);
        lm = new LinearLayoutManager(root.getContext());
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        Libro ms = snapshot1.getValue(Libro.class);
                        Log.d("myTag", ""+ms+"");
                        list.add(ms);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                buscar(s);
                return true;
            }
        });
        final TextView textView = binding.texthome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}