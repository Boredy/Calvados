package pkgCore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import pkgEnum.ePlayerIdentity;
import pkgInterfaces.iPlayer;

public class Table implements Serializable {

	private UUID TableID;
	private HashMap<UUID, Player> hmTablePlayer = new HashMap<UUID, Player>();

	public Table() {
		super();
		this.TableID = UUID.randomUUID();
	}

	public void AddPlayerToTable(Player p) {
		hmTablePlayer.put(p.getPlayerID(), p);
	}

	public void RemovePlayerFromTable(Player p) {
		hmTablePlayer.remove(p.getPlayerID());
	}

	public Player GetPlayerFromTable(Player p) {
		return (Player) hmTablePlayer.get(p.getPlayerID());

	}

	public HashMap<UUID, Player> getHmTablePlayer() {
		return hmTablePlayer;
	}

//	public ArrayList<Player> GetTablePlayers() {
//
//		return new ArrayList<Player>(hmTablePlayer.values());
//	}
	
	public ArrayList<iPlayer> GetTablePlayers(UUID PlayerID)
	{
		ArrayList<iPlayer> iPlayers = new ArrayList<iPlayer>();
		for (Player p: hmTablePlayer.values())
		{
			if (p.getPlayerID().equals(PlayerID))
			{
				p.seteIdent(ePlayerIdentity.ME);
			}
			else
			{
				p.seteIdent(ePlayerIdentity.OTHER);
			}		
			iPlayers.add(p);
		}		
		return iPlayers;
	}

}
