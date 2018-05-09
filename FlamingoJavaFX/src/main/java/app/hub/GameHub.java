package app.hub;

import java.io.IOException;

import netgame.common.Hub;
import pkgCore.Action;
import pkgCore.Deck;
import pkgCore.GamePlay;
import pkgCore.GamePlayBlackJack;
import pkgCore.Table;
import pkgEnum.eAction;
import pkgEnum.eGameType;

public class GameHub extends Hub {

	private Table HubPokerTable = null;
	private GamePlay HubGamePlay = null;
	private eGameType eGameType = null;

	public GameHub(int port) throws IOException {
		super(port);
		this.setAutoreset(true);
	}

	@Override
	protected void messageReceived(int playerID, Object message) {

		System.out.println("Action received from the hub");
		
		if (HubPokerTable == null)
			HubPokerTable = new Table();
		
		if (message instanceof Action) {
			
			Action a = (Action)message;

			switch (a.geteAction()) {
			case Sit:
				HubPokerTable.AddPlayerToTable(a.getActPlayer());
				resetOutput();
				sendToAll(HubPokerTable);				
				break;
			case Leave:
				HubPokerTable.RemovePlayerFromTable(a.getActPlayer());
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			case TableState:
				resetOutput();
				sendToAll(HubPokerTable);
			case GameState:
				//TODO: Implement this
				break;
			case StartGameBlackJack:
				
				eGameType = eGameType.BLACKJACK;
				HubGamePlay = new GamePlayBlackJack(HubPokerTable.getHmTablePlayer(), new Deck(6));
				sendToAll(HubGamePlay);
				
				break;
			case Draw:
				
				break;
			}
				


		}
	}

}
