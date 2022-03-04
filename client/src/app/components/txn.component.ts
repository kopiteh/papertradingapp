import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
  selector: 'app-txn',
  templateUrl: './txn.component.html',
  styleUrls: ['./txn.component.scss']
})
export class TxnComponent implements OnInit {

  tokens : String[] = ['BTC','ETH','SOL','CRO'];

  constructor(private tokenSvc : TokenStorageService) { }

  ngOnInit(): void {
    console.log('token>>>',this.tokenSvc.getToken())
    console.log('user>>>>', this.tokenSvc.getUser())
  }

}
