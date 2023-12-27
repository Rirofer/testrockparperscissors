import { Command, Resource } from "../../rest";
import { Link } from "../../rest/link";
import { Round } from "./round.model";

export class Game extends Resource {
    readonly status: string;
    readonly winner: string;
    readonly startedOn: Date;
    readonly rounds: Array<Round>;
    readonly finished: boolean;

    constructor(status: string, winner: string, startedOn: Date, rounds: Array<Round>,
        links: Map<string, Link>, commands: Map<string, Command>) {
        super(links, commands);
        this.status = status;
        this.winner = winner;
        this.status = status;
        this.startedOn = startedOn;
        this.rounds = rounds;
        this.finished = 'FINISHED' === status;
    }
}
