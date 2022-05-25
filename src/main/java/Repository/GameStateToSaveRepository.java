package Repository;

public class GameStateToSaveRepository extends GsonRepository<GameStateToSave>{
    public GameStateToSaveRepository() {
        super(GameStateToSave.class);
    }
}
