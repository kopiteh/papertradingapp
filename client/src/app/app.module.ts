import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login.component';
import { MyportfolioComponent } from './components/myportfolio.component';
import { LeaderboardComponent } from './components/leaderboard.component';

import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TokensComponent } from './components/tokens.component';
import { TraderService } from './trader.service';
import { TxnComponent } from './components/txn.component';
import { RegisterComponent } from './components/register.component';
import {TokenStorageService} from "./_services/token-storage.service";
import { authInterceptorProviders} from "./_services/auth.service";
import { ErrorpageComponent } from './_error/errorpage/errorpage.component';


const appRoutes: Routes = [
  {path: '', component:LoginComponent},
  {path: 'leaderboard', component:LeaderboardComponent},
  {path: 'myportfolio', component:MyportfolioComponent},
  {path: 'tokens', component:TokensComponent},
  {path: 'transaction', component:TxnComponent},
  {path: 'register', component:RegisterComponent},
  {path: `error`, component:ErrorpageComponent},
  {path: '**', redirectTo:'error', pathMatch:'full'}

]

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LeaderboardComponent,
    MyportfolioComponent,
    TokensComponent,
    TxnComponent,
    RegisterComponent,
    ErrorpageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    BrowserAnimationsModule
  ],
  exports:[RouterModule],
  providers: [TraderService, TokenStorageService, authInterceptorProviders],
  bootstrap: [AppComponent]

})
export class AppModule { }
