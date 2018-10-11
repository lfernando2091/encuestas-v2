package saganet.mx.com.cgf.Layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import saganet.mx.com.cgf.R;

/**
 * Created by LuisFernando on 15/05/2017.
 */

public class EncuestasFragment extends Fragment {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        refreshLayout = (SwipeRefreshLayout) inflater.inflate(
                R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView)refreshLayout.findViewById(R.id.my_recycler_views);
        // Iniciar la tarea asíncrona al revelar el indicador
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {}
                }
        );
        return refreshLayout;
    }
}
