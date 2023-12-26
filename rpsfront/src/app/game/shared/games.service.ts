import {Injectable} from "@angular/core";
import {CollectionResource, ResourceAdapter} from "../../rest";
import {Game} from "./game.model";
import {HttpClient} from "@angular/common/http";
import { environment } from '../../../environments/environment';
import {map, Observable} from "rxjs";
import {toGame} from "./game-mapper";

@Injectable()
export class GamesService {

  private readonly resourceAdapter: ResourceAdapter;
  private readonly httpClient: HttpClient;

  constructor(resourceAdapter: ResourceAdapter, httpClient: HttpClient) {
    this.resourceAdapter = resourceAdapter;
    this.httpClient = httpClient;
  }

  getGames(): Observable<CollectionResource<Game>> {
    return this.httpClient.get(environment.apiUri)
      .pipe(map(res => this.toGames(res)));
  }

  startGame(collectionResource: CollectionResource<Game>): Observable<Game> {
    const command = collectionResource.getCommand('start-game');
    if (typeof command == 'undefined') {
      throw new Error("Games collection resource does not have 'start-game' command");
    }
    return this.httpClient.post(command.target, {})
      .pipe(map(r => toGame(r, this.resourceAdapter)));
  }

  private toGames(collectionResource: any): CollectionResource<Game> {
    const links = this.resourceAdapter.toLinks(collectionResource);
    const commands = this.resourceAdapter.toCommands(collectionResource);
    const resources = this.resourceAdapter.getResources(collectionResource, 'games');
    return new CollectionResource<Game>(this.toGameResources(resources), links, commands);
  }

  private toGameResources(resources: Array<any>): Array<Game> {
    return resources.map(r => toGame(r, this.resourceAdapter));
  }

}
