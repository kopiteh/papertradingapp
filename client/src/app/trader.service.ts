import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import {Asset, Leaderboard, MyPortfolio, newUser, Order, User} from "./models";

@Injectable()
export class TraderService {

  constructor (private http: HttpClient){}

  getLatestPrice() {
    return this.http.get<Asset[]>(`/secure/api/assets`);
  }

  getLeaderboard() {
    return this.http.get<Leaderboard[]>(`/secure/api/leaderboard`);
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

  getMyPortfolio(){
    return this.http.get<MyPortfolio[]>(`/secure/api/myportfolio`)
  }

  buy(order: Order){
    return this.http.post<any>(`/secure/api/buy`, order)
  }

  sell(order: Order){
    return this.http.post<any>(`/secure/api/sell`, order)
  }
}
