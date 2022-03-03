import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Asset, Leaderboard } from "./models";

@Injectable()
export class TraderService {

  constructor (private http: HttpClient){}

  getLatestPrice() {
    return this.http.get<Asset[]>(`/api/assets`);
  }

  getLeaderboard() {
    return this.http.get<Leaderboard[]>(`/api/leaderboard`);
  }

}
