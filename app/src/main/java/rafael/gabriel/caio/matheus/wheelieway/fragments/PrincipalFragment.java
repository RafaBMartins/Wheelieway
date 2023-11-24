package rafael.gabriel.caio.matheus.wheelieway.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.checkerframework.checker.nullness.qual.NonNull;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.activities.HomeActivity;
import rafael.gabriel.caio.matheus.wheelieway.adapter.EstabelecimentoComparator;
import rafael.gabriel.caio.matheus.wheelieway.adapter.EstabelecimentoListAdapter;
import rafael.gabriel.caio.matheus.wheelieway.models.EstabelecimentoItem;
import rafael.gabriel.caio.matheus.wheelieway.models.HomeViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrincipalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrincipalFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private View view;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrincipalFragment newInstance() {
        return new PrincipalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_principal, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        EstabelecimentoListAdapter listAdapter = new EstabelecimentoListAdapter(new EstabelecimentoComparator());
        LiveData<PagingData<EstabelecimentoItem>> liveData = homeViewModel.getPageEi();
        liveData.observe(getViewLifecycleOwner(), new Observer<PagingData<EstabelecimentoItem>>() {
            @Override
            public void onChanged(PagingData<EstabelecimentoItem> objectPagingData) {
                listAdapter.submitData(getViewLifecycleOwner().getLifecycle(), objectPagingData);
            }
        });

        RecyclerView rvGallery = (RecyclerView) view.findViewById(R.id.rvEstabelecimentoPrincipal);
        rvGallery.setAdapter(listAdapter);
        rvGallery.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}