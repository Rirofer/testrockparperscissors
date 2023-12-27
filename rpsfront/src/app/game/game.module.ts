import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {GamesService} from "./shared";
import {GameListComponent} from "./game-list/game-list.component";
import {GameRandomComponent} from "./game-random/game-random.component";
import {HttpClientModule} from "@angular/common/http";
import {GameComponent} from "./game.component";
import {RoundsComponent} from "./rounds/rounds.component";

@NgModule({
  declarations: [GameComponent, GameRandomComponent, GameListComponent, RoundsComponent],
  imports: [
    CommonModule, HttpClientModule
  ],
  exports: [
    GameComponent, GameRandomComponent, GameListComponent, RoundsComponent
  ],
  providers: [GamesService]
})
export class GameModule { }
