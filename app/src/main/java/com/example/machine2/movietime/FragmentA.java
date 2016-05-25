package com.example.machine2.movietime;

/**
 * Created by machine2 on 25/05/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentA extends Fragment {

    TextChangeListener listener;

    public interface TextChangeListener {
        public void onTextChange(CharSequence newText);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentsa, container, false);

        Button btn = (Button) view.findViewById(R.id.button1);
        final EditText editText = (EditText) view.findViewById(R.id.textView1);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onTextChange(editText.getText());
                }

            }
        });
        return view;
    }

    public void setTextChangeListener(TextChangeListener listener) {
        this.listener = listener;
    }
}