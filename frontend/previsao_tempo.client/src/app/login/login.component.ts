import { AuthService } from './../service/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public form: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  public doLogin(value: any) {
    console.log(value.username);
    console.log(value.password);
    this.authService.login(value.username, value.password);
  }

  ngOnInit() {
    this.form = this.fb.group({
      username: new FormControl('', [Validators.compose([Validators.required, Validators.minLength(4)])]),
      password: new FormControl('', [Validators.compose([Validators.required, Validators.minLength(4)])])
    });
  }

}
