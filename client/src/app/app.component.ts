import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorageService} from "./_services/token-storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  isLoggedIn : boolean = true;

  constructor(private router: Router,
              private tokenSvc : TokenStorageService) {
  }
  ngOnInit(): void {
    this.checkLoggedIn();
  }

  ngOnDestroy() {
    sessionStorage.clear();
  }

  checkLoggedIn(){
    if (this.tokenSvc.getToken()){
      this.isLoggedIn = false;
    }
  }
  toggle(){
    this.isLoggedIn = false;
  }
  logout(){

    sessionStorage.clear();
    this.router.navigate(['/']);
  }




}
