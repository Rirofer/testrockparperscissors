import {ResourceAdapter} from "../../rest/resource-adapter";
import {Game} from "./game.model";
import {Round} from "./round.model";

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
