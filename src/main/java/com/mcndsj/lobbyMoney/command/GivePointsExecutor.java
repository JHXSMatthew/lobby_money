package com.mcndsj.lobbyMoney.command;

import com.mcndsj.Lobby_Display.Lobby_Display;
import com.mcndsj.lobbyMoney.MoneyType;
import com.mcndsj.lobbyMoney.api.LobbyMoneyApi;
import com.mcndsj.lobbyMoney.api.MoneyQueryCallBack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Matthew on 21/06/2016.
 */
public class GivePointsExecutor implements MoneyQueryCallBack {

    private int coming = -1;
    public GivePointsExecutor(int coming){
        this.coming = coming;
    }

    public void onCall(String player,MoneyType type, int amount) {
        Player p = Bukkit.getPlayer(player);
        LobbyMoneyApi.get().setCurrency(player,type,amount + coming);
        if(p != null) {
            p.sendMessage(ChatColor.RED + "YourCraft >> 感谢您赞助点券 " + ChatColor.GREEN + coming + ChatColor.RED + " 点，YourCraft 有你更精彩！");
            Lobby_Display.getInstance().getApi().refreshBoard(p);
        }
    }
}
