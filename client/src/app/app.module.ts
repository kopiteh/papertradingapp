import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LandingComponent } from './components/landing.component';
import { MyportfolioComponent } from './components/myportfolio.component';
import { LeaderboardComponent } from './components/leaderboard.component';

import { HttpClientModule } from '@angular/common/http';
import { MdbAccordionModule } from 'mdb-angular-ui-kit/accordion';
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { MdbCheckboxModule } from 'mdb-angular-ui-kit/checkbox';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { MdbPopoverModule } from 'mdb-angular-ui-kit/popover';
import { MdbRadioModule } from 'mdb-angular-ui-kit/radio';
import { MdbRangeModule } from 'mdb-angular-ui-kit/range';
import { MdbRippleModule } from 'mdb-angular-ui-kit/ripple';
import { MdbScrollspyModule } from 'mdb-angular-ui-kit/scrollspy';
import { MdbTabsModule } from 'mdb-angular-ui-kit/tabs';
import { MdbTooltipModule } from 'mdb-angular-ui-kit/tooltip';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TokensComponent } from './components/tokens.component';
import { TraderService } from './trader.service';
import { TxnComponent } from './components/txn.component';
import { RegisterComponent } from './components/register.component';
import {TokenStorageService} from "./_services/token-storage.service";
import {AuthInterceptor, authInterceptorProviders} from "./_services/auth.service";
import { TestComponent } from './test/test/test.component';

const appRoutes: Routes = [
  {path: '', component:LandingComponent},
  {path: 'leaderboard', component:LeaderboardComponent},
  {path: 'myportfolio', component:MyportfolioComponent},
  {path: 'tokens', component:TokensComponent},
  {path: 'transaction', component:TxnComponent},
  {path: 'register', component:RegisterComponent},
  {path: `test`, component:TestComponent},
  {path: '**', redirectTo:'', pathMatch:'full'}

]

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    LeaderboardComponent,
    MyportfolioComponent,
    TokensComponent,
    TxnComponent,
    RegisterComponent,
    TestComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    MdbAccordionModule,
    MdbCarouselModule,
    MdbCheckboxModule,
    MdbCollapseModule,
    MdbDropdownModule,
    MdbFormsModule,
    MdbModalModule,
    MdbPopoverModule,
    MdbRadioModule,
    MdbRangeModule,
    MdbRippleModule,
    MdbScrollspyModule,
    MdbTabsModule,
    MdbTooltipModule,
    MdbValidationModule,
    BrowserAnimationsModule,
  ],
  providers: [TraderService, TokenStorageService, authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
