import {AfterViewInit, Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../_services/token-storage.service";
import {TraderService} from "../trader.service";
import {Order, MyPortfolio} from "../models";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-myportfolio',
  templateUrl: './myportfolio.component.html',
  styleUrls: ['./myportfolio.component.scss']
})
export class MyportfolioComponent implements OnInit,AfterViewInit {

  myPortfolios!: MyPortfolio[];
  total_value!: number;
  sellForm!: FormGroup;

  constructor(private tokenSvc: TokenStorageService,
              private traderSvc: TraderService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.createform();

    const username = this.tokenSvc.getUser()

    this.traderSvc.getMyPortfolio()
      .subscribe(data => {
        this.myPortfolios = data
      })
  }

  ngAfterViewInit() {
    this.mpf_total_value();
  }

  createform() {
    this.sellForm = this.fb.group({
      quantity: this.fb.control('', Validators.required)
    })
  }

  sellToken(i: number) {
    let selectedToken = this.myPortfolios[i];

    const order: Order = {
      asset_id: selectedToken.asset_id,
      price_usd: selectedToken.price_usd,
      quantity: parseFloat(this.sellForm.controls['quantity'].value) * -1
    }

    console.log(order)
     this.traderSvc.sell(order)
       .subscribe(response=>{
         console.log(response)
       })

    window.location.reload();
  }

  mpf_total_value(){
    let total_value : number = 0;

    for (let x of this.myPortfolios){
      total_value = total_value + x.value;
    }
  }
}
