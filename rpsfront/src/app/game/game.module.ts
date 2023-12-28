import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GamesService} from "./shared";
import {GameListComponent} from "./game-list/game-list.component";
import {GameRandomComponent} from "./game-random/game-random.component";
import {HttpClientModule} from "@angular/common/http";
import {GameComponent} from "./game.component";
import {RoundsComponent} from "./rounds/rounds.component";
import {MatButtonModule} from "@angular/material/button";
import {MatDividerModule} from "@angular/material/divider";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatTableModule} from "@angular/material/table";

@NgModule({
  declarations: [GameComponent, GameRandomComponent, GameListComponent, RoundsComponent],
  imports: [
    CommonModule, HttpClientModule,
    MatButtonModule, MatDividerModule, MatInputModule,
    MatSelectModule, BrowserAnimationsModule, MatTableModule
  ],
  exports: [
    GameComponent, GameRandomComponent, GameListComponent, RoundsComponent
  ],
  providers: [GamesService]
})
export class GameModule {
}
