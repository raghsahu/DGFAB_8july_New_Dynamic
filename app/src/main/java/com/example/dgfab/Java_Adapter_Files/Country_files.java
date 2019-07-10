package com.example.dgfab.Java_Adapter_Files;

import android.widget.RadioButton;
import android.widget.TextView;

public class Country_files  {
    RadioButton Select;
    String Number;
    TextView  Cont_Name;


    public Country_files(RadioButton select, String number, TextView cont_Name) {
        Select = select;
        Number = number;
        Cont_Name = cont_Name;
    }

    public RadioButton getSelect() {
        return Select;
    }

    public void setSelect(RadioButton select) {
        Select = select;
    }

    public String  getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public TextView getCont_Name() {
        return Cont_Name;
    }

    public void setCont_Name(TextView cont_Name) {
        Cont_Name = cont_Name;
    }
}
