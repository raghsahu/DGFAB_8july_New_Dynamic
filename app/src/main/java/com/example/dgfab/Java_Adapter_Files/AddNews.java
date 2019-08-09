package com.example.dgfab.Java_Adapter_Files;

import android.widget.Button;
import android.widget.EditText;

public class AddNews {
    EditText Tilte,Name;
    Button button;

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
        Tilte = tilte;
        Name = name;
        this.button = button;
    }
}
