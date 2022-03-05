import { Component, OnInit } from '@angular/core';
import {Asset, MyPortfolio} from "../../models";

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {
  assets: Asset[]=[
    {asset_id:'BTC', price_usd:1231123},
    {asset_id:'ETH', price_usd:3232},
    {asset_id:'BNB', price_usd:123},
  ];

  constructor() { }

  ngOnInit(): void {

  }

  print_object (i : number) {
    let test = this.assets[i];

    console.log(test)
  }
}
