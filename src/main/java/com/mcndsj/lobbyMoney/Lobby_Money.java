package com.mcndsj.lobbyMoney;

import com.mcndsj.lobbyMoney.api.LobbyMoneyApi;
import com.mcndsj.lobbyMoney.api.MoneyQueryCallBack;
import com.mcndsj.lobbyMoney.command.GivePointsExecutor;
import com.mcndsj.lobbyMoney.command.givePointsCommand;
import com.mcndsj.lobbyMoney.utils.SQLUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Matthew on 21/06/2016.
 */
public class Lobby_Money extends JavaPlugin {

    private ExecutorService pool ;
    private static Lobby_Money instance;
    private static Economy economy;

    public void onEnable(){
        instance = this;
        setupEconomy();
        pool = Executors.newCachedThreadPool();

        getCommand("givepoints").setExecutor(new givePointsCommand());
    }


    public  void getCurrency(final String name, final MoneyType type, final MoneyQueryCallBack callBack){
        pool.execute(new Runnable() {
            public void run() {
                switch (type){
                    case money:
                        callBack.onCall(name,type, (int)economy.getBalance(Bukkit.getPlayer(name)));
                        break;
                    case point:
                        callBack.onCall(name,type, SQLUtils.getPoints(name));
                        break;
                }
            }
        });
    }

    public  int getCurrency(final String name, final MoneyType type){
        switch (type){
            case money:
                return (int)economy.getBalance(Bukkit.getPlayer(name));
            case point:
                return SQLUtils.getPoints(name);
        }
        return 0;
    }

    public void setCurrency(final String name, final MoneyType type , final int amount){
        pool.execute(new Runnable() {
            public void run() {
                switch (type){
                    case money:
                        try {
                            double current = economy.getBalance(Bukkit.getPlayer(name));
                            if (amount - current < 0) {
                                economy.withdrawPlayer(Bukkit.getPlayer(name), current - amount);
                            } else {
                                economy.depositPlayer(Bukkit.getPlayer(name), amount - current);
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case point:
                        SQLUtils.setPoints(name,amount);
                        break;
                }
            }
        });
    }

    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }


    public static LobbyMoneyApi getAPI(){
        return LobbyMoneyApi.get();
    }

    public static Lobby_Money getInstance(){
        return instance;
    }


}
