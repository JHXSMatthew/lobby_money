package com.mcndsj.lobbyMoney.api;

import com.mcndsj.lobbyMoney.MoneyType;

/**
 * Created by Matthew on 21/06/2016.
 */
public interface MoneyQueryCallBack {

    void onCall(String player,MoneyType type, int amount);

}
