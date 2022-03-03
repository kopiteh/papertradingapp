import { Component, OnInit } from '@angular/core';
import { Asset } from '../models';
import { TraderService } from '../trader.service';

@Component({
  selector: 'app-tokens',
  templateUrl: './tokens.component.html',
  styleUrls: ['./tokens.component.scss']
})
export class TokensComponent implements OnInit {

  assets: Asset[]=[];

  constructor(private traderService : TraderService) { };

  ngOnInit() {

    this.traderService.getLatestPrice()
      .subscribe(data=>{
        this.assets = data
      })
  }

  refreshPrice(){
    this.traderService.getLatestPrice()
    .subscribe(data=>{
      this.assets = data
    })
  }
}
