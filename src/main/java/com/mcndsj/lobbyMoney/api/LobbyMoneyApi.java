package com.mcndsj.lobbyMoney.api;

import com.mcndsj.lobbyMoney.Lobby_Money;
import com.mcndsj.lobbyMoney.MoneyType;

/**
 * Created by Matthew on 21/06/2016.
 */
public class LobbyMoneyApi {

    private static LobbyMoneyApi api;

    public static LobbyMoneyApi get(){
        if(api == null){
            api = new LobbyMoneyApi();
        }
        return api;
    }

    public void getCurrency(String player,MoneyType type,MoneyQueryCallBack callback){
        Lobby_Money.getInstance().getCurrency(player,type,callback);
    }

    public int getCurrency(String player,MoneyType type){
        return Lobby_Money.getInstance().getCurrency(player,type);
    }


    public void setCurrency(String player, MoneyType type,int amount){
        Lobby_Money.getInstance().setCurrency(player,type,amount);
    }
}
