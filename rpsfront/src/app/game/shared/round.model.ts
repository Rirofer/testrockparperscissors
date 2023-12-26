export class Round {
    readonly playerOneMove: string;
    readonly playerTwoMove: string;
    readonly randomPlayerTwo: boolean;

    constructor(playerOneMove: string, playerTwoMove: string, randomPlayerTwo: boolean) {
        this.playerOneMove = playerOneMove;
        this.playerTwoMove = playerTwoMove;
        this.randomPlayerTwo = randomPlayerTwo;
    }
}