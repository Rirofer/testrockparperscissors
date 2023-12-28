import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {Round} from "../shared";

@Component({
  selector: 'app-rounds',
  templateUrl: './rounds.component.html',
  styleUrl: './rounds.component.scss'
})
export class RoundsComponent  {

  @Input() rounds: Array<Round> | undefined;
  displayedColumns: string[] = ['playerOneMove', 'playerTwoMove', 'randomPlayerTwo'];

}
