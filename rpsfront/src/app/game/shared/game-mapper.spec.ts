import {toGame} from "./game-mapper";
import {FINISHED_GAME} from "./games.service.test-data";
import {FakeResourceAdapter} from "./fake-resource-adapter";

describe('GamesService tests', () => {

  it('#toGame should return finished game when status is FINISHED',() => {
    const game = toGame(FINISHED_GAME, new FakeResourceAdapter());

    expect(game.finished).toEqual(true);
  });
});
