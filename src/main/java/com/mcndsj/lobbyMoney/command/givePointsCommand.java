package com.mcndsj.lobbyMoney.command;


import com.mcndsj.lobbyMoney.MoneyType;
import com.mcndsj.lobbyMoney.api.LobbyMoneyApi;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class givePointsCommand implements CommandExecutor{

    public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
                             String[] arg3) {

        if(!(arg0 instanceof ConsoleCommandSender)){
            return false;
        }

        if(arg3.length < 2){
            return false;
        }

        String name = arg3[0];
        int i = -1;
        try{
            i = Integer.parseInt(arg3[1]);
            if(i == -1){
                return false; // IO error
            }
        }catch(Exception e){
            System.out.print("Wrong amount");
            return false;
        }


        GivePointsExecutor executor = new GivePointsExecutor(i);
        LobbyMoneyApi.get().getCurrency(name, MoneyType.point,executor);

        return true;
    }


}
