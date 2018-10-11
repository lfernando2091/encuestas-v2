package saganet.mx.com.cgf.Layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import saganet.mx.com.cgf.DataBase.Controler.Sesion;
import saganet.mx.com.cgf.DataBase.Tablas.PreguntaEO;
import saganet.mx.com.cgf.Modelo.ViewModel;
import saganet.mx.com.cgf.R;
import saganet.mx.com.cgf.Variables.CgfC;
import saganet.mx.com.cgf.logger.LoggerC;

/**
 * Created by LuisFernando on 18/05/2017.
 */

public class CuestionarioFragment extends Fragment {
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private Sesion sesion;
    private List<PreguntaEO> listado;
    public  ContentAdapter adapter;
    LoggerC log=new LoggerC(CuestionarioFragment.class);

    public void Adata(Sesion sesion) {
        this.sesion = sesion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        refreshLayout = (SwipeRefreshLayout) inflater.inflate(
                R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView)refreshLayout.findViewById(R.id.my_recycler_views);
        // Iniciar la tarea asíncrona al revelar el indicador
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new ACuestionario().execute();
                    }
                }
        );
        CgfC.mapeo= new HashMap<Integer, String>();
        new ACuestionario().execute();
        return refreshLayout;
    }

    public class ACuestionario extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            listado= new ArrayList<PreguntaEO>();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            listado=sesion.getPreguntas();
            return listado.toString();
        }

        @Override
        protected void onPostExecute(String aVoid) {
            log.printf(aVoid);
            if(refreshLayout.isRefreshing()) refreshLayout.setRefreshing(false);
            doRefresh();
        }
    }

    /**
     * Refresca el contedo de to do el View
     */
    public void doRefresh(){
        adapter = new ContentAdapter(listado,CuestionarioFragment.this,getActivity());
        recyclerView.removeAllViews();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    /**
     * Focus a un elemento en tal posicion
     */
    public void doSmoothScroll(int position) {
        recyclerView.smoothScrollToPosition(position);
    }
    /**
     * Agregar el mapeo de una nueva pregunta
     */
    public void Add(PreguntaEO historialEO){
        adapter.addCard(historialEO);
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
        private List<PreguntaEO> listado;
        private CuestionarioFragment context;
        private FragmentActivity typeg;

        public ContentAdapter(List<PreguntaEO> listado, CuestionarioFragment context, FragmentActivity typeg) {
            this.listado=listado;this.context=context;this.typeg=typeg;
        }

        /**
         * Call to mask of card view.
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(parent.getContext());
            View v = li.inflate(R.layout.card_component, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.pregunta.setText(listado.get(position).getPregunta());
            holder.respuestas.removeAllViews();
            new ViewModel(holder.respuestas, listado.get(position).getTipo(),listado.get(position).getListado(), CgfC.mapeo.get(position+1),position, getActivity());
        }

        @Override
        public int getItemCount() {
            return  listado.size();
        }

        @Override
        public long getItemId(int position) {
            return listado.get(position).get_id();
        }

        /**
        * Animación de revelado circular*/
        public void animateCircularReveal(View view){
            int cX=0, cY=0, sRadius=0, eRadius=Math.max(view.getWidth(),view.getHeight());
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Animator animator = ViewAnimationUtils.createCircularReveal(view,cX,cY,sRadius,eRadius);
                view.setVisibility(View.VISIBLE);
                animator.start();
            }
        }
        /**
        * Animación de supresion circular*/
        public void animateCircularSupress(final View view, final int lposition){
            int cX=view.getWidth(), cY=view.getHeight(), sRadius=view.getWidth(), eRadius=0;
            Animator animator = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                animator = ViewAnimationUtils.createCircularReveal(view,cX,cY,sRadius,eRadius);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.INVISIBLE);
                        listado.remove(lposition);
                        notifyItemRemoved(lposition);
                    }
                });
                animator.start();
            }else {
                view.setVisibility(View.INVISIBLE);
                listado.remove(lposition);
                notifyItemRemoved(lposition);
            }
        }
        @Override
        public void onViewAttachedToWindow(ViewHolder viewHolder){
            super.onViewAttachedToWindow(viewHolder);
            //--animateCircularReveal(viewHolder.itemView);
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder viewHolder){
            super.onViewDetachedFromWindow(viewHolder);
            //--viewHolder.itemView.clearAnimation();
        }

        public void addCard(PreguntaEO historialEO) {
            listado.add(historialEO);
            ((CuestionarioFragment)context).doSmoothScroll(getItemCount());
            notifyItemInserted(getItemCount());
        }

        public void deleteCard(View view, int list_position) {
            animateCircularSupress(view, list_position);
        }
        /**
        * render one by one items card
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            private AppCompatTextView pregunta;
            private LinearLayout respuestas;
            public ViewHolder(View itemViews) {
                super(itemViews);
                pregunta = (AppCompatTextView) itemViews.findViewById(R.id.tPregunta);
                respuestas = (LinearLayout) itemViews.findViewById(R.id.tRespuestas);
                itemViews.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showItem();
                    }
                });
                respuestas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int requestCode = getAdapterPosition();
                       log.printf("Click elemento " + listado.get(requestCode).getPregunta());
                    }
                });
            }

            private void showItem(){

            }
            public void setErro(){
                pregunta.requestFocus();
                pregunta.setError("El campo es obligatorio.");
            }
        }
    }
}
