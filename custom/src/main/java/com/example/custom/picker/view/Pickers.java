package com.example.custom.picker.view;

import java.io.Serializable;

/**
 *
 */
public class Pickers implements Serializable{
    private static final long serialVersionUID = 1L;

    private String showConetnt;

    public Pickers(String showConetnt) {
        this.showConetnt = showConetnt;
    }

    public String getShowConetnt() {
        return showConetnt;
    }




}
