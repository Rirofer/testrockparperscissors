import { NgModule } from '@angular/core';
import {AppComponent} from "./app.component";
import {GameModule} from "./game/game.module";
import {AppRoutingModule} from "./app-routing.module";
import {BrowserModule} from "@angular/platform-browser";
import {HalFormsResourceAdapter, ResourceAdapter} from "./rest";

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    GameModule,
    AppRoutingModule
  ],
  providers: [{ provide: ResourceAdapter, useClass: HalFormsResourceAdapter }],
  bootstrap: [AppComponent]
})
export class AppModule { }
