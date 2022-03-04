import { Component, OnInit } from '@angular/core';
import { Leaderboard } from '../models';
import { TraderService } from '../trader.service';
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.scss']
})
export class LeaderboardComponent implements OnInit {

  leaderboards : Leaderboard[] = [];

  constructor(private traderService : TraderService, private tokenSvc:TokenStorageService) { }

  ngOnInit() {
    this.traderService.getSecureLeaderboard() //changed getLeaderboard() to getSecureLeaderboard()
      .subscribe(data => {
        this.leaderboards = data
      })
    console.log('User  >>>', this.tokenSvc.getUser())
    console.log('Token  >>>', this.tokenSvc.getToken())

  }

}
