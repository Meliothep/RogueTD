package game;

public class GameState {
    private static GameState INSTANCE;

    private GameState(){

    }
    public GameState getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GameState();
        }
        return INSTANCE;
    }
}
