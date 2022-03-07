import {Component, OnInit} from '@angular/core';
import {Asset, Order} from '../models';
import {TraderService} from '../trader.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-tokens',
  templateUrl: './tokens.component.html',
  styleUrls: ['./tokens.component.scss']
})
export class TokensComponent implements OnInit {

  assets: Asset[] = [];
  quantity!: number;
  buyForm!: FormGroup

  constructor(private fb: FormBuilder,
              private router: Router,
              private traderSvc: TraderService) {
  }

  ngOnInit() {
    this.createform();

    this.traderSvc.getLatestPrice()
      .subscribe(data => {
        this.assets = data
      })
  }

  createform() {
    this.buyForm = this.fb.group({
      quantity: this.fb.control('', Validators.required)
    })
  }

  submitBuy(i: number) {
    let selectedToken = this.assets[i];

    const order: Order = {
      asset_id: selectedToken.asset_id,
      price_usd: parseFloat(String(selectedToken.price_usd)),
      quantity: parseFloat(this.buyForm.controls['quantity'].value)
    }

    console.log(order)
    this.traderSvc.buy(order)
      .subscribe(response => {
        console.log(response)
      })

    this.router.navigate(['/myportfolio'])


  }

  refreshPrice() {
    window.location.reload();
  }
}
