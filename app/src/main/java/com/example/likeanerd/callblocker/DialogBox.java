package com.example.likeanerd.callblocker;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class DialogBox extends DialogFragment implements View.OnClickListener {

    Button yes,no;
    Communicator communicator;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        communicator = (Communicator) context;
        this.context = context;
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog,null);
        no = (Button)view.findViewById(R.id.No);
        yes = (Button)view.findViewById(R.id.yes);
        no.setOnClickListener(this);
        yes.setOnClickListener(this);
        setCancelable(false);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.yes)
        {
            communicator.number();
            Intent main = new Intent(context,MainActivity.class);
            startActivity(main);
            dismiss();

        }
        else if(v.getId()==R.id.No) {
            dismiss();
        }


    }
    interface Communicator
    {
        public void number();
    }
}