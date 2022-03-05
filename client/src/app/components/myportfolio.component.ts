import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../_services/token-storage.service";
import {TraderService} from "../trader.service";
import {MyPortfolio} from "../models";

@Component({
  selector: 'app-myportfolio',
  templateUrl: './myportfolio.component.html',
  styleUrls: ['./myportfolio.component.scss']
})
export class MyportfolioComponent implements OnInit {

  myPortfolios! : MyPortfolio[];
  total_value! : number;

  constructor(private tokenSvc : TokenStorageService,
              private traderSvc : TraderService) {}

  ngOnInit(): void {

    const username = this.tokenSvc.getUser()

    this.traderSvc.getMyPortfolio()
      .subscribe(data=>{
        this.myPortfolios = data
      })

  }


}
