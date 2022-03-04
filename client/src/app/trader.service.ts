import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import {Asset, JwtToken, Leaderboard, newUser, User} from "./models";

@Injectable()
export class TraderService {

  constructor (private http: HttpClient){}

  getLatestPrice() {
    return this.http.get<Asset[]>(`/api/assets`);
  }

  getLeaderboard() {
    return this.http.get<Leaderboard[]>(`/api/leaderboard`);
  }


  getSecureLeaderboard() {
    return this.http.get<Leaderboard[]>(`/secure/api/leaderboard`);
  }

  userLogin(user: User){
    return this.http.post<any>(`/api/authenticate`, user);
  }

  registerUser(newUser:newUser){
    return this.http.post<any>(`/api/register`,newUser)
  }

}
