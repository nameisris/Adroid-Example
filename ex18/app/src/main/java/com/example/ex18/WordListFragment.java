package com.example.ex18;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class WordListFragment extends ListFragment {
    /*
    String[] WORDS = {"boy", "girl", "school", "hello", "go"};
    String[] DESC= {
            "A boy is a child who will grow up to be a man.",
            "A girl is a female child.",
            "A school is a place where children are educated.",
            "You say 'Hello' to someone when you meet them.",
            "When you go somewhere, you move or travel there."
    };
    */
    ArrayList<String> WORDS;
    ArrayList<String> DESC;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WORDS = new ArrayList<>();
        DESC = new ArrayList<>();

        WORDS.add("boy");
        WORDS.add("girl");
        WORDS.add("school");
        WORDS.add("hello");
        WORDS.add("go");

        DESC.add("A boy is a child who will grow up to be a man.");
        DESC.add("A girl is a female child.");
        DESC.add("A school is a place where children are educated.");
        DESC.add("You say 'Hello' to someone when you meet them.");
        DESC.add("When you go somewhere, you move or travel there.");

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, WORDS)); // WORDS에 저장된 문자열들을 선택형으로 adapt 해줌
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) { // position은 몇 번째 아이템을 선택했는지
        super.onListItemClick(l, v, position, id);
        TextView text = getActivity().findViewById(R.id.text);
        text.setText(DESC.get(position)); // position 번째 DESC 항목을 TextView인 text에 넣어주어 set
        // DESC[position]
    }
}
