package PAOO_GAME.Player;

public final class PlayerFactory {
    private Player player;

    public void createPlayer(String type){
        switch (type){
            case "black" -> player=new BlackPlayer();
            case "blue"  -> player=new BluePlayer();
            case "green" -> player=new GreenPlayer();
        }
    }

    public Player getPlayer(){return player;}
}
