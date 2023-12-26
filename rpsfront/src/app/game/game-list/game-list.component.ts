import {Component, OnInit} from '@angular/core';
import {Game, GamesService} from '../shared';
import {CollectionResource} from "../../rest";

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrl: './game-list.component.scss',
  providers: []
})
export class GameListComponent implements OnInit {

  games: CollectionResource<Game>;
  private gamesService: GamesService;

  constructor(gamesService: GamesService) {
    this.gamesService = gamesService;
    this.games = new CollectionResource<Game>([], new Map, new Map);
  }

  ngOnInit(): void {
    this.refreshGames();
  }

  private refreshGames(): void {
    this.gamesService.getGames()
      .subscribe(res => this.games = res);
  }

  startGame(): void {
    this.gamesService.startGame(this.games).subscribe(g => this.refreshGames());
  }


}
