import {Injectable} from "@angular/core";
import {CollectionResource, Command, ResourceAdapter} from "../../rest";
import {Game} from "./game.model";
import {HttpClient} from "@angular/common/http";
import { environment } from '../../../environments/environment';
import {map, Observable, Subject} from "rxjs";
import {toGame, toGames} from "./game-mapper";

@Injectable()
export class GamesService {

  private readonly resourceAdapter: ResourceAdapter;
  private readonly httpClient: HttpClient;
  private readonly lastGameSubject: Subject<Game>;

  constructor(resourceAdapter: ResourceAdapter, httpClient: HttpClient) {
    this.resourceAdapter = resourceAdapter;
    this.httpClient = httpClient;
    this.lastGameSubject = new Subject<Game>();
  }

  getGames(): Observable<CollectionResource<Game>> {
    return this.httpClient.get(environment.apiUri)
      .pipe(map(res => toGames(res, this.resourceAdapter)));
  }

  startGame(collectionResource: CollectionResource<Game>): Observable<Game> {
    const command = collectionResource.getCommand('start-game-command');
    if (typeof command == 'undefined') {
      throw new Error("Games collection resource does not have 'start-game' command");
    }
    return this.httpClient.post(command.target, {})
      .pipe(map(r => this.toGame(r)));
  }

  private toGame(resource: any): Game {
    const game = toGame(resource, this.resourceAdapter);
    this.setLatestGame(game);
    return game;
  }

  private setLatestGame(game: Game) {
    this.lastGameSubject.next(game);
  }

  onGameStarted(): Observable<Game> {
    return this.lastGameSubject;
  }

  playRandomRound(playerOneMove: string, command: Command): Observable<Game> {
    return  this.httpClient.post(command.target, {playerOneMove: playerOneMove})
      .pipe(map(r => this.toGame(r)));
  }

}
