import {Component, OnInit} from '@angular/core';
import {Game, GamesService, Round} from "../shared";
import {Command} from "../../rest";

@Component({
  selector: 'app-game-random',
  templateUrl: './game-random.component.html',
  styleUrl: './game-random.component.scss'
})
export class GameRandomComponent implements OnInit {

  currentGame: Game | undefined;
  playRandomRoundCommand: Command | undefined;
  playerOneMove: string;
  canPlayRound: boolean;
  moveOptions!: Array<string>;
  private readonly gamesService: GamesService;

  constructor(gamesService: GamesService) {
    this.gamesService = gamesService;
    this.playerOneMove = "";
    this.canPlayRound = false;
  }

  ngOnInit(): void {
    this.gamesService.onGameStarted().subscribe(g => this.setCurrentGame(g));
  }

  private setCurrentGame(game: Game) {
    this.currentGame = game;
    this.playRandomRoundCommand = game.getCommand('play-random-round-command');
    this.canPlayRound = this.currentGame.status == 'ONGOING';
    const opts = this.playRandomRoundCommand?.getPropertyOptions('playerOneMove');
    if (typeof opts != 'undefined') {
      this.moveOptions = opts;
      this.playerOneMove = this.moveOptions[0];
    }
  }

  onMoveSelected(event: any) {
    this.playerOneMove = event.target.value;
  }

  playRandomRound() {
    if (typeof this.playRandomRoundCommand != 'undefined') {
      this.gamesService.playRandomRound(this.playerOneMove, this.playRandomRoundCommand).subscribe(g => this.setCurrentGame(g));
    }
  }

}
