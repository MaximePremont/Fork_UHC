package fr.blueslime.uhc.commands;

import fr.blueslime.uhc.Messages;
import fr.blueslime.uhc.UHC;
import fr.blueslime.uhc.arena.ArenaCommon.ArenaType;
import fr.blueslime.uhc.arena.ArenaPlayer;
import fr.blueslime.uhc.arena.ArenaTeam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJoin
{
    public static boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings)
    {
        if(cs instanceof Player)
        {
            if(UHC.getPlugin().getArena().getArenaType() == ArenaType.TEAM)
            {
                if(!UHC.getPlugin().getArena().isGameStarted())
                {
                    String team = strings[1];
                    String player = strings[2];
                    ArenaPlayer aPlayer = UHC.getPlugin().getArena().getPlayer(Bukkit.getPlayer(player).getUniqueId());
                    ArenaTeam aTeam = UHC.getPlugin().getArena().getTeamByNumber(Integer.valueOf(team));

                    if(!aPlayer.hasTeam())
                    {
                        if(aTeam.isInvited(aPlayer.getUUID()))
                        {
                            aTeam.join(aPlayer);
                            aPlayer.getPlayerIfOnline().sendMessage(Messages.teamJoined.toString().replace("${TEAM}", aTeam.getChatColor() + aTeam.getName()) + ChatColor.YELLOW);
                        }
                    }
                }
            }
        }
        
        return true;
    }
}
