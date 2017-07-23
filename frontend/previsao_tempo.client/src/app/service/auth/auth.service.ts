import { Observable } from 'rxjs/Observable';
import { ServiceLocator } from './../locator/service.locator';
/**
 * Created by Guilherme on 06/04/2017.
 */

import { Injectable } from '@angular/core';


import 'rxjs/add/observable/of';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/delay';
import {TokenService} from '../token/token.service';
import {HttpService} from '../http/http.service';

import { AuthCredentials } from './auth.credentials';
import {Router} from '@angular/router';

@Injectable()
export class AuthService {

  constructor(private router: Router) {
  }

  private get httpService() : HttpService {
    return ServiceLocator.get(HttpService);
  }

  private get tokenService() : TokenService {
    return ServiceLocator.get(TokenService);
  }

  /**
   * Efetua a autenticação no servidor, com os parametros recebidos.
   */
  login(username: string, password: string): Observable<Boolean> {
    let obs = this.httpService.post('/auth', {data: new AuthCredentials(username, password)});
    obs.subscribe((data: any) => {
        if (data instanceof Object) {
          this.tokenService.setToken(data, new Date());
          this.router.navigate(['/usercity']);
          return true;
        } else {
          this.tokenService.resetToken();
          this.router.navigate(['/login']);
          return false;
        }
      }
    );
    return obs;
  }

  isLoggedIn(): Observable<boolean> {
    return this.tokenService.isTokenValid();
  }

  logout(): void {
    this.tokenService.resetToken();
    this.router.navigate(['/login']);
  }
}