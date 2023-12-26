import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {GamesService} from "./shared";
import {GameListComponent} from "./game-list/game-list.component";
import {GameRandomComponent} from "./game-random/game-random.component";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [GameRandomComponent, GameListComponent],
  imports: [
    CommonModule, HttpClientModule
  ],
  exports: [
     GameRandomComponent, GameListComponent
  ],
  providers: [GamesService]
})
export class GameModule { }
