import {Game} from "./game.model";
import {Round} from "./round.model";
import {CollectionResource, ResourceAdapter} from "../../rest";

export function toGames(collectionResource: any,  resourceAdapter: ResourceAdapter): CollectionResource<Game> {
  const links = resourceAdapter.toLinks(collectionResource);
  const commands = resourceAdapter.toCommands(collectionResource);
  const resources = resourceAdapter.getResources(collectionResource, 'games');
  return new CollectionResource<Game>(toGameResources(resources, resourceAdapter), links, commands);
}

export function toGameResources(resources: Array<any>,  resourceAdapter: ResourceAdapter): Array<Game> {
  return resources.map(r => toGame(r, resourceAdapter));
}
export function toGame(resource: any, resourceAdapter: ResourceAdapter): Game {
  const links = resourceAdapter.toLinks(resource);
  const commands = resourceAdapter.toCommands(resource);
  const rounds = toRounds(resource['rounds']);
  return new Game(resource['status'], resource['winner'], toDate(resource['startedOn']), rounds, links, commands);
}

export function toRounds(rounds: Array<any>): Array<Round> {
  return rounds.map(toRound);
}

export function toRound(round: any): Round {
  return new Round(round['playerOneMove'], round['playerTwoMove'], round['randomPlayerTwo']);
}

function toDate(str: string): Date {
  return new Date(str);
}
