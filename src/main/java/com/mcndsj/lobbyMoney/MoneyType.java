package com.mcndsj.lobbyMoney;

/**
 * Created by Matthew on 21/06/2016.
 */
public enum MoneyType {

    money("金币"),point("点券");

    private String name;
    MoneyType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
