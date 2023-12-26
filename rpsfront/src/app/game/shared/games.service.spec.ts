import {HttpClient} from "@angular/common/http";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {TestBed} from "@angular/core/testing";
import {GamesService} from "./games.service";
import {environment} from "../../../environments/environment";
import {GAMES} from "./games.service.test-data";
import {Game} from "./game.model";
import {HalFormsResourceAdapter, ResourceAdapter} from "../../rest";

describe('GamesService tests', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let gamesService: GamesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });

    // Inject the http service and test controller for each test
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    const resourceAdapter: ResourceAdapter = new HalFormsResourceAdapter();
    gamesService = new GamesService(resourceAdapter, httpClient);
  });

  it('#getGames should return game collection resource', () => {
    // Make an HTTP GET request
    gamesService.getGames()
      .subscribe(collectionResource => {
        // When observable resolves, result should match test data
        expect(collectionResource.resources.length).toEqual(3);
        expect(collectionResource.getLink('self')).toBeDefined();
        expect(collectionResource.getCommand('start-game')).toBeDefined();
        assertCorrectGame(collectionResource.resources[0]);
      });

    const req = httpTestingController.expectOne(environment.apiUri);
    expect(req.request.method).toEqual("GET");
    req.flush(GAMES);
    httpTestingController.verify();
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
  }

});
