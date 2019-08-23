package com.example.dgfab.Java_Adapter_Files;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddNews {
    EditText Tilte;
    String Tiltes;
    String Values;
    EditText Name;
    String Names, NamesDetails;
    RadioButton payRadioButton;


    String payRadioString;
    EditText NameDetails;
    Button button;
    int recid;

    String sletxt;

    public AddNews(int recid, RadioButton payRadioButton) {
        this.payRadioButton = payRadioButton;
        this.payRadioString = payRadioButton.getText().toString();
        this.recid = recid;
    }

    public AddNews(int recid, String tits, String value) {
        this.Tiltes = tits;
        this.Values = value;
        this.recid = recid;
    }

    public String getPayRadioString() {
        return payRadioString;
    }

    public void setPayRadioString(String payRadioString) {
        this.payRadioString = payRadioString;
    }

    public String getTiltes() {
        return Tiltes;
    }

    public void setTiltes(String tiltes) {
        this.Tiltes = tiltes;
    }

    public AddNews(int recid, String sletxt) {
        this.sletxt = sletxt;
        if (recid == 1) {
            this.Names = sletxt;
            this.recid = recid;
        } else if (recid == 2) {
            this.NamesDetails = sletxt;
            this.recid = recid;
        } else if (recid == 5) {
            this.payRadioString = sletxt;
            this.recid = recid;
        }
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        this.Names = names;
    }

    public String getNamesDetails() {
        return NamesDetails;
    }

    public void setNamesDetails(String namesDetails) {
        this.NamesDetails = namesDetails;
    }

    public int getRecid() {
        return recid;
    }

    public String getValues() {
        return Values;
    }

    public void setValues(String values) {
        this.Values = values;
    }

    public String getSletxt() {
        return sletxt;
    }

    public void setSletxt(String sletxt) {
        this.sletxt = sletxt;
    }

    public void setRecid(int recid) {
        this.recid = recid;
    }

//    public AddNews(int recid,String Name) {
//        this.sletxt = sletxt;
//        this.recid = recid;
//    }

    public RadioButton getPayRadioButton() {
        return payRadioButton;
    }

    public void setPayRadioButton(RadioButton payRadioButton) {
        this.payRadioButton = payRadioButton;
    }

    public AddNews(int recid,EditText nameDetails) {
        this.NameDetails = nameDetails;
        this.recid = recid;
    }

    public EditText getNameDetails() {
        return NameDetails;
    }

    public void setNameDetails(EditText nameDetails) {
       NameDetails = nameDetails;
    }

    public EditText getTilte() {
        return Tilte;
    }

    public void setTilte(EditText tilte) {
        Tilte = tilte;
    }

    public EditText getName() {
        return Name;
    }

    public void setName(EditText name) {
        Name = name;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public AddNews(int recid,EditText tilte, EditText name, Button button) {
        this.Tilte = tilte;
        this.Name = name;
        this.button = button;
    }

    public AddNews(int recid,EditText name, Button button) {
       this.Name = name;
        this.button = button;
    }
}
