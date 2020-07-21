package com.example.ex18;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class GenderFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_gender, container, false); // fragment_gender를 inflate해서(펼처서?) return 해줌

        Button btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) { // fragment_gender의 '확인' 버튼을 눌렀을 때
                EditText name = view.findViewById(R.id.name); // view의 name (fragment_gender) 을 받아옴 (새로 입력된 값)
                String strName = name.getText().toString(); // String형 변수에 넣어줌

                TextView text = getActivity().findViewById(R.id.name); // 현재 액티비티 (MainActivity3) 에서 가져오기 위해 getActivity() 메소드 사용
                // 이름을 새로 입력받아 출력하는 액티비티의 TextView의 아이디 값을 받아옴 (액티비티의 name의 출력값을 새로 넣어주기 위해)
                text.setText(strName); // text를 strName으로 Set
                // 프래그먼트 상에서 새로 입력받은 문자열을 현재 액티비티의 name 아이디를 받아와 그 곳에 출력함

                RadioButton male = view.findViewById(R.id.male); // 프래그먼트 xml의 male 아이디를 받아옴
                TextView gender = getActivity().findViewById(R.id.gender); // 현재 액티비티의 gender 아이디를 받아옴
                if(male.isChecked()){ // 만약 프래그먼트에서 받아온 아이디인 male의 항목이 체크되어있다면
                    gender.setText("남자"); // 현재 액티비티의 gender의 text를 "남자"로 Set
                }
                else{ // 그 외의 경우 (female의 항목에 체크되어 있다면)
                    gender.setText("여자"); // 현재 액티비티의 gender의 text를 "여자"로 Set
                }
            }
        });
        return view;
    }
}
