import { Component, OnInit } from '@angular/core';
import { Leaderboard } from '../models';
import { TraderService } from '../trader.service';

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.scss']
})
export class LeaderboardComponent implements OnInit {

  leaderboards : Leaderboard[] = [];

  constructor(private traderService : TraderService) { }

  ngOnInit() {
    this.traderService.getLeaderboard()
      .subscribe(data => {
        this.leaderboards = data
      })
  }

}
