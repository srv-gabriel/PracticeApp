package com.example.practiceapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String polys[];
    String teor[];
    Context cont;
    String value = "";
    ArrayList<Integer> indexList = new ArrayList<Integer>();
    public static ArrayList<String> editModelArrayList = new ArrayList<>();


    public MyAdapter(Context cont, String polys[], String teor[]){
        this.cont = cont;
        this.polys = polys;
        this.teor = teor;

        // Populating indexList and editModelArrayList
        for(int i=0;i<polys.length;i++){

            indexList.add(0);
            editModelArrayList.add("");

            }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if(viewType == R.layout.my_row) {
            LayoutInflater layoutInflater = LayoutInflater.from(cont);
            view = layoutInflater.inflate(R.layout.my_row, parent, false);

        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.final_result, parent, false);
        }
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        if(position == polys.length){

        }
        else {
            // Disabling the edit text
            if(holder.spin.getSelectedItem() == null){
                holder.edtText.setEnabled(false);
            }

            // Setting the text previous typed into the EditText
            holder.edtText.setText(editModelArrayList.get(position));

            // Setting the text on the TextView
            holder.txtView.setText(teor[position]);

            // Creating the ArrayAdapter to the spinner
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(cont, R.array.polygons, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.spin.setAdapter(adapter);

            // Setting the previous selection to the spinner
            holder.spin.setSelection(indexList.get(position));

            // Action when the spinner is selected
            holder.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                    // Setting the index of the selected value to the position of the spinner
                    indexList.set(position, index);
                    if(index!=0){
                        // Enabling the editText if any value is selected
                        holder.edtText.setEnabled(true);
                    }
                    else{
                        // Disabling the editText if a value is not selected
                        holder.edtText.setEnabled(false);
                        //holder.edtText.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            // Action when the EditText is selected
            holder.edtText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    // Saving the typed text into the editModelArrayList
                    editModelArrayList.set(position, holder.edtText.getText().toString());

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return polys.length+1;
    }

    public int getItemViewType(int position) {
        return (position == polys.length) ? R.layout.final_result : R.layout.my_row;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtView;
        EditText edtText;
        Spinner spin;
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtView = itemView.findViewById(R.id.textView);
            edtText = itemView.findViewById(R.id.editText);
            spin = itemView.findViewById(R.id.spinner);
            btn = itemView.findViewById(R.id.button);




        }
    }
}
