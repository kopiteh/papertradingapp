import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../models';
import { TraderService } from '../trader.service';
import {TokenStorageService} from "../_services/token-storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-landing',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm! : FormGroup;
  username! : string;

  constructor(private fb: FormBuilder,
              private traderSvc : TraderService,
              private tokenSvc: TokenStorageService,
              private router : Router)  { }

  ngOnInit(): void {
    this.createForm();

  }

  createForm() {
    this.loginForm = this.fb.group({
      username: this.fb.control('',[Validators.required, Validators.minLength(3)]),
      password: this.fb.control('',[Validators.required])
    })
  }


  login(){
    const credentials = this.loginForm.value as User

    this.traderSvc.userLogin(credentials)
      .subscribe(response => {
        console.log(response)
        this.tokenSvc.saveToken(response['token'])
        this.tokenSvc.saveUser(response['subject'])

      })
    this.router.navigate(['/myportfolio'])
  }

}
