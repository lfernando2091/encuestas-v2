package saganet.mx.com.cgf.Modelo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import saganet.mx.com.cgf.DataBase.Catalogo.TipoPreguntaDO;
import saganet.mx.com.cgf.DataBase.Tablas.RespuestaEO;
import saganet.mx.com.cgf.Layout.CuestionarioFragment;
import saganet.mx.com.cgf.R;
import saganet.mx.com.cgf.Variables.CgfC;
import saganet.mx.com.cgf.logger.LoggerC;

/**
 * Created by LuisFernando on 18/05/2017.
 */

public class ViewModel {
    LoggerC log=new LoggerC(ViewModel.class);
    private LinearLayout.LayoutParams llp;
    public ViewModel(LinearLayout layout, TipoPreguntaDO tipo, List<RespuestaEO> listado, String seleccionado, int id, FragmentActivity activity){
        seleccionado=seleccionado==null?"":seleccionado;
        llp = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT);
        llp.setMargins(5, 14, 5, 0);
            switch (tipo){
            case CERRADA_UNICA:
                if(listado.size()<10) new BotonRadio(layout,tipo,listado,seleccionado,id,activity);
                else new Combobox(layout,listado,seleccionado,id,activity);
                break;
            case CERRADA_MULTIPLE:
                new BotonCheck(layout,tipo,listado,seleccionado,id,activity);
                break;
            case CERRADA_FECHA:
                new CampoFecha(layout,seleccionado,id,activity);
                break;
            case CERRADA_HORA:
                new CampoHora(layout,seleccionado,id,activity);
                break;
            case CERRADA_NUMERICO_RANGO: case CERRADA_TEXTO_RANGO:
                new Combobox(layout,listado,seleccionado,"",id,activity);
                break;
            case CERRADA_FECHA_RANGO:
                new CampoFecha(layout,seleccionado,"",id,activity);
                break;
            case CERRADA_HORA_RANGO:
                new CampoHora(layout,seleccionado,"",id,activity);
                break;
            case ABIERTA_TEXTO:
                case ABIERTA_PARRAFO:
                case ABIERTA_NUMERICO:
                case ABIERTA_NUMERICO_RANGO:
                case ABIERTA_TELEFONO:
                case ABIERTA_EMAIL:
                case ABIERTA_FECHA_RANGO:
                new CajaTexto(layout,tipo,seleccionado, id,activity);
                break;
            case ABIERTA_FECHA:
                break;
            case ABIERTA_HORA:
                break;
            case ABIERTA_HORA_RANGO:
                break;
            case ABIERTA_TEXTO_LISTA:
                break;
            case ABIERTA_NUMERICO_LISTA:
                break;
            case ABIERTA_TELEFONO_LISTA:
                break;
            case ABIERTA_EMAIL_LISTA:
                break;
                case FACE_STATE: case LIKE_DISLIKE:
                    new BotonRadioEspecial(layout,tipo,listado,seleccionado,id,activity);
                    break;
            case ITERABLE:
                break;
            case COMPONENTE:
                break;
        }
    }
    /**
     * Elemento boton de radio de estados de animo y megusta/no me gusta
     */
    class BotonRadioEspecial{
        private RadioGroup group;
        private AppCompatRadioButton radioButton;
        public BotonRadioEspecial(LinearLayout layout, TipoPreguntaDO tipo,final List<RespuestaEO> listado, String seleccionado, int id,FragmentActivity activity){
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, 80);
            group= new RadioGroup(activity);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    CgfC.mapeo.put(listado.get(checkedId).getIdPregunta(),listado.get(checkedId).getRespuesta());
                }
            });
            for (RespuestaEO r: listado){
                radioButton = new AppCompatRadioButton(activity);
                radioButton.setId(listado.indexOf(r));
                //radioButton.setId(id);
                radioButton.setChecked(r.getRespuesta().equals(seleccionado));
                radioButton.setTextSize(10);
                switch (tipo){
                    case FACE_STATE:
                        switch (r.getRespuesta()){
                            case "ALEGRE":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.smileemoji, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            case "NEUTRAL":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.neutralemoji, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            case "TRIZTE":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.sademoji, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            case "ENOJADO":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.angryemoji, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            case "SORPRENDIDO":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.hooemoji, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            case "QUE":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.whatemoji, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            case "MUYTRIZTE":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.moresademoji, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            default:
                                radioButton.setTag(r.getRespuesta());
                                radioButton.setText(r.getRespuesta());
                                break;
                        }
                        break;
                    case LIKE_DISLIKE:
                        switch (r.getRespuesta()){
                            case "MEGUSTA":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.handlike, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            case "NOMEGUSTA":
                                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.handdislike, 0, 0, 0);
                                radioButton.setTag(r.getRespuesta());
                                break;
                            default:
                                radioButton.setTag(r.getRespuesta());
                                radioButton.setText(r.getRespuesta());
                                break;
                        }
                        break;
                }
                //TODO MAKE SOME WITH IMAGES IMOGI OPTION
                //// TODO: 23/05/2017 MAKE SOME WITH YES/ NOT THAN LIKE O DISLIKE
                group.addView(radioButton,params_rb);
            }
            //group.setId(id);
            if(((LinearLayout) layout).getChildCount()<listado.size())((LinearLayout) layout).addView(group);
        }
    }
    /**
     * Elemento boton de radio
     */
    class BotonRadio{
        private RadioGroup group;
        private AppCompatRadioButton radioButton;
        public BotonRadio(LinearLayout layout, TipoPreguntaDO tipo,final List<RespuestaEO> listado, String seleccionado, int id,FragmentActivity activity){
            //((LinearLayout) layout).removeAllViews();
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, 50);
            group= new RadioGroup(activity);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    CgfC.mapeo.put(listado.get(checkedId).getIdPregunta(),listado.get(checkedId).getRespuesta());
                }
            });
            for (RespuestaEO r: listado){
                radioButton = new AppCompatRadioButton(activity);
                radioButton.setId(listado.indexOf(r));
                radioButton.setText(r.getRespuesta());
                radioButton.setTag(id);
                radioButton.setChecked(r.getRespuesta().equals(seleccionado));
                radioButton.setTextSize(15);
                //TODO MAKE SOME WITH IMAGES IMOGI OPTION
                //// TODO: 23/05/2017 MAKE SOME WITH YES/ NOT THAN LIKE O DISLIKE
                //radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.neutral_face, 0, 0, 0);
                group.addView(radioButton,params_rb);
            }
            //group.setId(id);
            if(((LinearLayout) layout).getChildCount()<listado.size())((LinearLayout) layout).addView(group);
        }
    }
    /**
     * Elemento combo box
     */
    class Combobox{
        private AppCompatSpinner spinner, spinner2;
        private ArrayList<String> list;
        private AppCompatTextView etiqueta(FragmentActivity activity, String t){
            AppCompatTextView eti= new AppCompatTextView(activity);
            eti.setText(t);
            return eti;
        }
        public Combobox(LinearLayout layout,final List<RespuestaEO> listado, String seleccionado, int id,FragmentActivity activity){
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, 125);
            spinner= new AppCompatSpinner(activity, Spinner.MODE_DIALOG);
            list= new ArrayList<String>();
            list.add(0,"Selecciona Respuesta");
            for(RespuestaEO s: listado)list.add(s.getRespuesta());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.select_dialog_singlechoice, list);
            adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
            spinner.setAdapter(adapter);
            if(spinner.equals(""))spinner.setSelection(0, true);
            else {
                int g =list.indexOf(seleccionado);
                spinner.setSelection(g,true);
            }
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int y;
                        if(position>0){
                            y=position-1;
                            CgfC.mapeo.put(listado.get(y).getIdPregunta(),listado.get(y).getRespuesta());
                        }
                        else {
                            y=0;
                            CgfC.mapeo.put(listado.get(y).getIdPregunta(),"");
                        }

                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            ((LinearLayout) layout).addView(spinner,params_rb);
        }
        public Combobox(LinearLayout layout,final List<RespuestaEO> listado, String seleccionado,String seleccionado2, int id,FragmentActivity activity){
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT);
            spinner= new AppCompatSpinner(activity, Spinner.MODE_DIALOG);
            list= new ArrayList<String>();
            list.add(0,"Selecciona Respuesta");
            for(RespuestaEO s: listado)list.add(s.getRespuesta());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.select_dialog_singlechoice, list);
            adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
            spinner.setAdapter(adapter);
            if(spinner.equals(""))spinner.setSelection(0, true);
            else {
                int g =list.indexOf(seleccionado);
                spinner.setSelection(g,true);
            }
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int y;
                    if(position>0){
                        y=position-1;
                        CgfC.mapeo.put(listado.get(y).getIdPregunta(),listado.get(y).getRespuesta());
                    }
                    else {
                        y=0;
                        CgfC.mapeo.put(listado.get(y).getIdPregunta(),"");
                    }

                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner2= new AppCompatSpinner(activity, Spinner.MODE_DIALOG);
            spinner2.setAdapter(adapter);
            if(spinner2.equals(""))spinner2.setSelection(0, true);
            else {
                int g =list.indexOf(seleccionado);
                spinner2.setSelection(g,true);
            }
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int y;
                    if(position>0){
                        y=position-1;
                        CgfC.mapeo.put(listado.get(y).getIdPregunta(),listado.get(y).getRespuesta());
                    }
                    else {
                        y=0;
                        CgfC.mapeo.put(listado.get(y).getIdPregunta(),"");
                    }

                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ((LinearLayout) layout).addView(etiqueta(activity,"Desde: "),params_rb);
            ((LinearLayout) layout).addView(spinner,params_rb);
            ((LinearLayout) layout).addView(etiqueta(activity,"Hasta: "),params_rb);
            ((LinearLayout) layout).addView(spinner2,params_rb);
        }
    }
    /**
     * Elemento check para multiple selección
     */
    class BotonCheck{
        private AppCompatCheckBox checkBox;
        public BotonCheck(LinearLayout layout, TipoPreguntaDO tipo,final List<RespuestaEO> listado, String seleccionado, int id,FragmentActivity activity){
            //((LinearLayout) layout).removeAllViews();
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, 50);
            for (RespuestaEO r: listado){
                checkBox= new AppCompatCheckBox(activity);
                checkBox.setText(r.getRespuesta());
                checkBox.setTag(id);
                checkBox.setTextSize(15);
                checkBox.setId(listado.indexOf(r));

                if(((LinearLayout) layout).getChildCount()<listado.size())((LinearLayout) layout).addView(checkBox,params_rb);
            }
        }
        public void setError() throws Exception{
            checkBox.requestFocus();
            checkBox.setError("El campo es obligatorio.");
        }

    }
    /**
     * Elemento campo de texto
     */
    class CajaTexto{
        private AppCompatEditText text, text2;
        private AppCompatTextView etiqueta(FragmentActivity activity, String t){
            AppCompatTextView eti= new AppCompatTextView(activity);
            eti.setText(t);
            return eti;
        }
        public  CajaTexto(LinearLayout layout, TipoPreguntaDO tipo, String seleccionado, int id,FragmentActivity activity){
            switch (tipo){
                case ABIERTA_TEXTO: case ABIERTA_NUMERICO: case ABIERTA_TELEFONO: case ABIERTA_EMAIL:
                    CampoTexto(layout,tipo,seleccionado,id,activity);
                    break;
                case ABIERTA_PARRAFO:
                    CampoParrafo(layout,seleccionado,id,activity);
                    break;
                case ABIERTA_NUMERICO_RANGO: case ABIERTA_FECHA_RANGO:
                    CampoTextoRango(layout,tipo,seleccionado,id,activity);
                    break;
            }
        }
        private void CampoTexto(LinearLayout layout, TipoPreguntaDO tipo, String seleccionado, final int id, FragmentActivity activity){
            //((LinearLayout) layout).removeAllViews();
            text= new AppCompatEditText(activity);
            text.setTag(id);
            text.setText(seleccionado.equals("")?"":seleccionado);
            text.setSingleLine(true);
            text.setTextSize(15);
            switch (tipo) {
                case ABIERTA_TEXTO:
                    text.setHint("Respuesta");
                    text.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                    break;
                case ABIERTA_NUMERICO:
                    text.setHint("Cantidad");
                    text.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                case ABIERTA_TELEFONO:
                    text.setHint("Telefono");
                    text.setInputType(InputType.TYPE_CLASS_NUMBER);
                    final String blockCharacterSet = "0123456789";
                    InputFilter filter = new InputFilter() {
                        @Override
                        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                            if (source != null && blockCharacterSet.contains(("" + source))) return null;
                            return "";
                        }
                    };
                    text.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10), filter});
                    break;
                case ABIERTA_EMAIL:
                    text.setHint("ejemplo@dominio.com");
                    text.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    final String blockCharacterSet2 = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890@_-.";
                    InputFilter filter2 = new InputFilter() {
                        @Override
                        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                            if (source != null && blockCharacterSet2.contains(("" + source))) return null;
                            return "";
                        }
                    };
                    text.setFilters(new InputFilter[] {filter2});
                    break;
            }
            text.addTextChangedListener(new TextWatcher() {
                String g;
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        g = String.valueOf(text.getText());
                    }catch (Exception e){
                        log.printf(e.getMessage());
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    CgfC.mapeo.put(id+1,g);
                }
            });
            if(((LinearLayout) layout).getChildCount()<1) ((LinearLayout) layout).addView(text,llp);
        }

        private void CampoTextoRango(final LinearLayout layout, TipoPreguntaDO tipo, String seleccionado, int id,FragmentActivity activity){
            //((LinearLayout) layout).removeAllViews();
            LinearLayout linearLayout= new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,CollapsingToolbarLayout.LayoutParams.MATCH_PARENT);
                switch (tipo) {
                    case ABIERTA_NUMERICO_RANGO:
                        text= new AppCompatEditText(activity);
                        text.setTag(id);
                        text.setText(seleccionado.equals("")?"":seleccionado);
                        text.setSingleLine(true);
                        text.setTextSize(15);
                        text.setHint("Cantidad");
                        text.setInputType(InputType.TYPE_CLASS_NUMBER);
                        text2= new AppCompatEditText(activity);
                        text2.setTag(id);
                        text2.setText(seleccionado.equals("")?"":seleccionado);
                        text2.setSingleLine(true);
                        text2.setTextSize(15);
                        text2.setHint("Cantidad");
                        text2.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case ABIERTA_FECHA_RANGO:
                            text= new AppCompatEditText(activity);
                            text.setId(id);
                            text.setTag(id);
                            text.setText(seleccionado.equals("")?"":seleccionado);
                            text.setSingleLine(true);
                            text.setTextSize(15);
                            text.setHint("dd/mm/aaaa");
                            text.setInputType(InputType.TYPE_CLASS_DATETIME);
                            final String blockCharacterSet = "0123456789/";
                            InputFilter filter = new InputFilter() {
                                @Override
                                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                                    if (source != null && blockCharacterSet.contains(("" + source))) return null;
                                    return "";
                                }
                            };
                            text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10), filter});
                            text.addTextChangedListener(new TextWatcher() {
                                String g;
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    //text=(AppCompatEditText)g;
                                    text.setText(g);
                                }
                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    try {
                                        g = String.valueOf(text.getText());
                                        if (!g.isEmpty() && (g.length() == 2 || g.length() == 5)) g += "/";
                                        log.printf(g);
                                    }catch (Exception e){
                                        log.printf(e.getMessage());
                                    }
                                }
                                @Override
                                public void afterTextChanged(Editable s) {
                                }
                            });

                            text2= new AppCompatEditText(activity);
                            text2.setTag(id);
                            text2.setText(seleccionado.equals("")?"":seleccionado);
                            text2.setSingleLine(true);
                            text2.setTextSize(15);
                            text2.setHint("dd/mm/aaaa");
                            text2.setInputType(InputType.TYPE_CLASS_DATETIME);
                            final String blockCharacterSet2 = "0123456789/";
                            InputFilter filter2 = new InputFilter() {
                                @Override
                                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                                    if (source != null && blockCharacterSet2.contains(("" + source))) return null;
                                    return "";
                                }
                            };
                            text2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10), filter2});
                            text2.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                }
                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                        String g = String.valueOf(text2.getText());
                                        if (!g.isEmpty() && (g.length() == 2 || g.length() == 5)) g += "/";
                                        log.printf(g);
                                        text2.setText(g);
                                }
                                @Override
                                public void afterTextChanged(Editable s) {
                                }
                            });
                        break;
                }
            linearLayout.addView(etiqueta(activity,"Desde:"),params_rb);
            linearLayout.addView(text,params_rb);
            linearLayout.addView(etiqueta(activity,"Hasta:"),params_rb);
            linearLayout.addView(text2,params_rb);
                //linearLayout.addView(etiqueta(activity,i==0?"Desde:":"Hasta:"),params_rb);
                //linearLayout.addView(text,params_rb);
            if(((LinearLayout) layout).getChildCount()<5) ((LinearLayout) layout).addView(linearLayout,params_rb);
        }

        private void CampoParrafo(LinearLayout layout, String seleccionado, final int id, FragmentActivity activity){
            //((LinearLayout) layout).removeAllViews();
            LinearLayout linearLayout= new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,CollapsingToolbarLayout.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams params_rb_2 = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT);
            text= new AppCompatEditText(activity);
            text.setHint("Descripción...");
            text.setTag(id);
            text.setText(seleccionado.equals("")?"":seleccionado);
            text.setMinLines(5);
            text.setMaxLines(10);
            text.setLines(5);
            text.setTextSize(15);
            text.setGravity(Gravity.LEFT | Gravity.TOP);
            text.addTextChangedListener(new TextWatcher() {
                String g;
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        g = String.valueOf(text.getText());
                    }catch (Exception e){
                        log.printf(e.getMessage());
                    }
                }
                @Override
                public void afterTextChanged(Editable s) {
                    CgfC.mapeo.put(id+1,g);
                }
            });
            //text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            linearLayout.addView(text,params_rb_2);
            if(((LinearLayout) layout).getChildCount()<1) ((LinearLayout) layout).addView(linearLayout,params_rb);
        }

        public void getError() throws Exception{
            text.requestFocus();
            text.setError("El campo es obligatorio.");
        }
    }
    /**
     * Elemento fecha
     */
    class CampoFecha{
        private AppCompatButton button, button2;
        private AppCompatTextView eti, eti2;
        private int d,m,a,i;
        private AppCompatTextView etiqueta(FragmentActivity activity, String t){
            eti= new AppCompatTextView(activity);
            eti.setTextSize(20);
            eti.setText(t);
            return eti;
        }
        private AppCompatTextView etiqueta2(FragmentActivity activity, String t){
            eti2= new AppCompatTextView(activity);
            eti2.setTextSize(20);
            eti2.setText(t);
            return eti2;
        }
        public CampoFecha(LinearLayout layout, final String seleccionado, int id, final FragmentActivity activity){
            ContextThemeWrapper newContext = new ContextThemeWrapper(activity, R.style.AppTheme_Button_Colored);
            button= new AppCompatButton(newContext);
            button.setText("Cambiar");
            button.setId(id);
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,CollapsingToolbarLayout.LayoutParams.MATCH_PARENT);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    TimeFragment newFragment = new TimeFragment();
                    i=1;
                    newFragment.CargarFecha(seleccionado);
                    newFragment.show(ft, "datePicker");
                }
            });
            ((LinearLayout) layout).addView(etiqueta(activity,seleccionado.equals("")?"dd/mm/aaaa":seleccionado),params_rb);
            ((LinearLayout) layout).addView(button,params_rb);
        }
        public CampoFecha(LinearLayout layout, final String seleccionado, final String seleccionado2, int id, final FragmentActivity activity){
            ContextThemeWrapper newContext = new ContextThemeWrapper(activity, R.style.AppTheme_Button_Colored);
            button= new AppCompatButton(newContext);
            button.setText("Cambiar");
            button.setId(id);
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,CollapsingToolbarLayout.LayoutParams.MATCH_PARENT);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    TimeFragment newFragment = new TimeFragment();
                    i=1;
                    newFragment.CargarFecha(seleccionado);
                    newFragment.show(ft, "datePicker");
                }
            });
            //----
            button2= new AppCompatButton(newContext);
            button2.setText("Cambiar");
            button2.setId(id);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    TimeFragment newFragment = new TimeFragment();
                    i=2;
                    newFragment.CargarFecha(seleccionado2);
                    newFragment.show(ft, "datePicker");
                }
            });
            ((LinearLayout) layout).addView(etiqueta(activity,"Desde: "),params_rb);
            ((LinearLayout) layout).addView(etiqueta(activity,seleccionado.equals("")?"dd/mm/aaaa":seleccionado),params_rb);
            ((LinearLayout) layout).addView(button,params_rb);
            ((LinearLayout) layout).addView(etiqueta2(activity,"Hasta: "),params_rb);
            ((LinearLayout) layout).addView(etiqueta2(activity,seleccionado2.equals("")?"dd/mm/aaaa":seleccionado2),params_rb);
            ((LinearLayout) layout).addView(button2,params_rb);
        }

        public void updateDisplay(int i) {
            int n= m+1;
            String sn=String.valueOf(n),kn=String.valueOf(d);
            if(n<10)sn= "0"+ String.valueOf(n);
            if(d<10)kn= "0"+ String.valueOf(d);
            if(i==1){
                eti.setText(
                        new StringBuilder()
                                .append(kn).append("/")
                                .append(sn).append("/")
                                .append(a).append(" "));
                CgfC.mapeo.put(button.getId()+1,eti.getText().toString());
                log.printf(String.valueOf(button.getId()));
            }
            else if(i==2){eti2.setText(
                        new StringBuilder()
                                .append(kn).append("/")
                                .append(sn).append("/")
                                .append(a).append(" "));
                CgfC.mapeo.put(button2.getId()+1,eti2.getText().toString());
                log.printf(String.valueOf(button2.getId()));
            }
        }

        @SuppressLint("ValidFragment")
        class TimeFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
            private int year, month, day;
            public TimeFragment(){}
            public void CargarFecha(String fe){
                if(fe.contains("/")){
                    String d= String.valueOf((fe.charAt(0)))+String.valueOf((fe.charAt(1))),
                            m=String.valueOf(fe.charAt(3)) +String.valueOf(fe.charAt(4)),
                            y=String.valueOf(fe.charAt(6))+
                                    String.valueOf(fe.charAt(7))+
                                    String.valueOf(fe.charAt(8))+
                                    String.valueOf(fe.charAt(9));
                    day= Integer.parseInt(d);
                    month=Integer.parseInt(m)-1;
                    year=Integer.parseInt(y);
                }
            }
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                // Use the current date as the default date in the picker
                final Calendar c = Calendar.getInstance();
                year =year==0? c.get(Calendar.YEAR):year;
                month = month==0?c.get(Calendar.MONTH):month;
                day = day==0?c.get(Calendar.DAY_OF_MONTH):day;
                // Create a new instance of DatePickerDialog and return it
                return new DatePickerDialog(getActivity(), this, year, month, day);
            }

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                d=dayOfMonth;m=month;a=year;
                updateDisplay(i);
            }
        }
    }
    /**
     * Elemento hora
     */
    class CampoHora{
        private AppCompatButton button, button2;
        private AppCompatTextView eti, eti2;
        private int h,m,i;
        private AppCompatTextView etiqueta(FragmentActivity activity, String t){
            eti= new AppCompatTextView(activity);
            eti.setTextSize(20);
            eti.setText(t);
            return eti;
        }
        private AppCompatTextView etiqueta2(FragmentActivity activity, String t){
            eti2= new AppCompatTextView(activity);
            eti2.setTextSize(20);
            eti2.setText(t);
            return eti2;
        }
        public CampoHora(LinearLayout layout, final String seleccionado, int id, final FragmentActivity activity){
            ContextThemeWrapper newContext = new ContextThemeWrapper(activity, R.style.AppTheme_Button_Colored);
            button= new AppCompatButton(newContext);
            button.setText("Cambiar");
            button.setId(id);
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,CollapsingToolbarLayout.LayoutParams.MATCH_PARENT);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    HourFragment newFragment = new HourFragment();
                    i=1;
                    newFragment.CargarHora(seleccionado);
                    newFragment.show(ft, "datePicker");
                }
            });
            ((LinearLayout) layout).addView(etiqueta(activity,seleccionado.equals("")?"hh:mm":seleccionado),params_rb);
            ((LinearLayout) layout).addView(button,params_rb);
        }
        public CampoHora(LinearLayout layout, final String seleccionado, final String seleccionado2, int id, final FragmentActivity activity){
            ContextThemeWrapper newContext = new ContextThemeWrapper(activity, R.style.AppTheme_Button_Colored);
            button= new AppCompatButton(newContext);
            button.setText("Cambiar");
            button.setId(id);
            LinearLayout.LayoutParams params_rb = new LinearLayout.LayoutParams(CollapsingToolbarLayout.LayoutParams.MATCH_PARENT,CollapsingToolbarLayout.LayoutParams.MATCH_PARENT);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    HourFragment newFragment = new HourFragment();
                    i=1;
                    newFragment.CargarHora(seleccionado);
                    newFragment.show(ft, "datePicker");
                }
            });
            //----
            button2= new AppCompatButton(newContext);
            button2.setText("Cambiar");
            button2.setId(id);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.app.FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    HourFragment newFragment = new HourFragment();
                    i=2;
                    newFragment.CargarHora(seleccionado2);
                    newFragment.show(ft, "datePicker");
                }
            });
            ((LinearLayout) layout).addView(etiqueta(activity,"Desde: "),params_rb);
            ((LinearLayout) layout).addView(etiqueta(activity,seleccionado.equals("")?"hh:mm":seleccionado),params_rb);
            ((LinearLayout) layout).addView(button,params_rb);
            ((LinearLayout) layout).addView(etiqueta2(activity,"Hasta: "),params_rb);
            ((LinearLayout) layout).addView(etiqueta2(activity,seleccionado2.equals("")?"hh:mm":seleccionado2),params_rb);
            ((LinearLayout) layout).addView(button2,params_rb);
        }

        public void updateDisplay(int i) {
            String sn=String.valueOf(h),kn=String.valueOf(m);
            if(h<10)sn= "0"+ sn;
            if(m<10)kn= "0"+ kn;
            if(i==1){
                eti.setText(
                        new StringBuilder()
                                .append(sn).append(":")
                                .append(kn).append(""));
            }
            else if(i==2){eti2.setText(
                    new StringBuilder()
                            .append(sn).append(":")
                            .append(kn).append(""));
            }
        }

        @SuppressLint("ValidFragment")
        class HourFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
            private int hour, minute;
            public HourFragment(){}
            public void CargarHora(String fe){
                if(fe.contains(":")){
                    String h= String.valueOf((fe.charAt(0)))+String.valueOf((fe.charAt(1))),
                            m=String.valueOf(fe.charAt(3)) +String.valueOf(fe.charAt(4));
                    hour= Integer.parseInt(h);
                    minute=Integer.parseInt(m);
                }
            }
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                // Use the current date as the default date in the picker
                final Calendar c = Calendar.getInstance();
                hour =hour==0? c.get(Calendar.HOUR):hour;
                minute = minute==0?c.get(Calendar.MINUTE):minute;
                // Create a new instance of DatePickerDialog and return it
                return new TimePickerDialog(getActivity(), this,Calendar.HOUR, Calendar.MINUTE,true);
            }

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                h=hourOfDay;m=minute;
                updateDisplay(i);
            }
        }
    }
}
