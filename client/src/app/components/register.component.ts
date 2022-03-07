import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TraderService} from "../trader.service";
import {newUser} from "../models";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  regForm! : FormGroup;
  constructor(private fb: FormBuilder, private traderSvc:TraderService, private router:Router) { }

  ngOnInit(): void {
    this.createform();
  }


  createform() {
    this.regForm = this.fb.group({
    username: this.fb.control('',[Validators.required, Validators.minLength(4)]),
    email: this.fb.control('',[Validators.required, Validators.email]),
    password: this.fb.control('',[Validators.required, Validators.minLength(4)])
    })
  }


  register(){
    const newUser = this.regForm.value as newUser;
    this.traderSvc.registerUser(newUser)
      .subscribe(response=> {
        console.log(response)
      })
    this.router.navigate(['/']).then(r => console.log(r))
  }
}
