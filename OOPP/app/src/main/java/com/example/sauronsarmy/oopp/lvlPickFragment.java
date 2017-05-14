package com.example.sauronsarmy.oopp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Filip on 2017-05-14.
 */

public class lvlPickFragment extends DialogFragment {


    //Interface for users of this fragment
    public interface ClickListener{
        void onclick(int level, int area);
    }
    //The area that was passed as an argument
    private int area;
    //The creator of this fragment
    ClickListener clickListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {

            Activity activity = (Activity) context;
            // Instantiate the ClickListener so we can send events to the host
            clickListener = (ClickListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        //Get the argument
        area = getArguments().getInt("area");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Select a level")
                .setItems(R.array.lvl,new DialogInterface.OnClickListener(){


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Call method in caller, pass area and clicked level
                        clickListener.onclick(which, area);
                    }
                }
        );

        return builder.create();

    }





}
