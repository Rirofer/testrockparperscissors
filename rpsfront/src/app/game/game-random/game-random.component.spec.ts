import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameRandomComponent } from './game-random.component';
import {GameModule} from "../game.module";

describe('GameRandomComponent', () => {
  let component: GameRandomComponent;
  let fixture: ComponentFixture<GameRandomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameRandomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
