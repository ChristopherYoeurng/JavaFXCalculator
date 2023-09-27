package com.example.calculator;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;

public class MonitorValue{
    private String val;

    public MonitorValue(){
        this.val = "";
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val, Label monitor) {
        this.val = val;
        monitor.setText(val);
    }

    public void popVal(Label monitor){
         if (this.val.charAt(this.val.length()-1) == ' '){
             this.val = this.val.substring(0, this.val.length()-3);
         }
         else {
             this.val = this.val.substring(0, this.val.length()-1);
         }
         monitor.setText(val);
    }

}
