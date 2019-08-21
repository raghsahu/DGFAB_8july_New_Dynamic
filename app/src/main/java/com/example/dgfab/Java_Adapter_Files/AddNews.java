package com.example.dgfab.Java_Adapter_Files;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddNews {
    EditText Tilte;
    EditText Name;
    RadioButton payRadioButton;
    EditText NameDetails;
    Button button;

    public AddNews(RadioButton payRadioButton) {
        this.payRadioButton = payRadioButton;
    }

    public RadioButton getPayRadioButton() {
        return payRadioButton;
    }

    public void setPayRadioButton(RadioButton payRadioButton) {
        this.payRadioButton = payRadioButton;
    }

    public AddNews(EditText nameDetails) {
        this.NameDetails = nameDetails;
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

    public AddNews(EditText tilte, EditText name, Button button) {
        this.Tilte = tilte;
        this.Name = name;
        this.button = button;
    }

    public AddNews(EditText name, Button button) {
       this.Name = name;
        this.button = button;
    }
}
