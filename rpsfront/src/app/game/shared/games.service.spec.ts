import {HttpClient} from "@angular/common/http";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {TestBed} from "@angular/core/testing";
import {GamesService} from "./games.service";
import {environment} from "../../../environments/environment";
import {GAME, GAMES} from "./games.service.test-data";
import {Game} from "./game.model";
import {HalFormsResourceAdapter, ResourceAdapter} from "../../rest";
import {toGames} from "./game-mapper";

const START_GAME_COMMAND = 'start-game-command';

describe('GamesService tests', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let gamesService: GamesService;
  let resourceAdapter: ResourceAdapter;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });

    // Inject the http service and test controller for each test
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    resourceAdapter = new HalFormsResourceAdapter();
    gamesService = new GamesService(resourceAdapter, httpClient);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('#getGames should return game collection resource', () => {
    gamesService.getGames()
      .subscribe(collectionResource => {
        // When observable resolves, result should match test data
        expect(collectionResource.resources.length).toEqual(3);
        expect(collectionResource.getLink('self')).toBeDefined();
        expect(collectionResource.getCommand(START_GAME_COMMAND)).toBeDefined();
        assertCorrectGame(collectionResource.resources[0]);
      });

    const req = httpTestingController.expectOne(environment.apiUri);
    expect(req.request.method).toEqual("GET");
    req.flush(GAMES);
  });


  it('#startGame should call command and return a game', () => {
    const gameCollectionResource = toGames(GAMES, resourceAdapter);
    gamesService.startGame(gameCollectionResource)
      .subscribe(game => {
        assertCorrectGame(game);
      });

    const startGameCommand = gameCollectionResource.getCommand(START_GAME_COMMAND);
    expect(startGameCommand).toBeDefined();
    if (typeof startGameCommand != 'undefined') {
      const req = httpTestingController.expectOne(startGameCommand?.target);
      expect(req.request.method).toEqual("POST");
      req.flush(GAME);
    }
  });

  function assertCorrectGame(game: Game) {
    expect(game.status).toEqual('ONGOING');
    expect(game.winner).toEqual('NONE');
    expect(game.startedOn).toEqual(new Date('2023-12-25T12:03:58.804684297'));
    expect(game.rounds.length).toEqual(1);
    const round = game.rounds[0];
    expect(round.playerOneMove).toEqual('PAPER');
    expect(round.playerTwoMove).toEqual('PAPER');
    expect(round.randomPlayerTwo).toBeTrue();
    expect(game.getCommand('play-random-round-command')).toBeDefined();
    expect(game.getLink('self')).toBeDefined();
  }

  it('#onGameStarted should return the latest started game', () => {
    const gameCollectionResource = toGames(GAMES, resourceAdapter);
    gamesService.startGame(gameCollectionResource).subscribe(game => expect(game).toBeDefined());

    gamesService.onGameStarted().subscribe(game => expect(game).toBeDefined());

    const startGameCommand = gameCollectionResource.getCommand(START_GAME_COMMAND);
    expect(startGameCommand).toBeDefined();
    if (typeof startGameCommand != 'undefined') {
      const req = httpTestingController.expectOne(startGameCommand?.target);
      req.flush(GAME);
    }
  });

});
