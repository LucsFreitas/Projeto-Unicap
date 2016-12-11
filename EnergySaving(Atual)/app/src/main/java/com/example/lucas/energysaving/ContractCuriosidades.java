package com.example.lucas.energysaving;

import java.io.Serializable;

/**
 * Created by Brenon on 11/12/2016.
 */

public class ContractCuriosidades implements Serializable {
    private String title;
    private String desc;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public ContractCuriosidades (String title, String desc){
        this.title = title;
        this.desc = desc;
    }

}

