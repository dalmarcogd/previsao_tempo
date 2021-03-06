import { Observable } from 'rxjs/Observable';
import { HttpConfigMethod } from './http.config.method';
import { ApplicationError } from './../error/application.error';
import { TokenService } from './../token/token.service';
import { ServiceLocator } from './../locator/service.locator';
import { Injectable, ErrorHandler } from '@angular/core';
import { RequestOptions, Http, Response, Headers } from '@angular/http';
import { HttpHeaders } from './http.headers';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { TokenDTO } from '../../model/token/token.dto';
import { ApplicationErrorHandler } from '../error/application.error.handler';
import { URL_SERVER } from './http.constants';

/**
 * Serviço que implementa métodos de comunicação Http.
 */
@Injectable()
export class HttpService {

  private get tokenService() : TokenService {
    return ServiceLocator.get(TokenService);
  }
  
  private get applicationErrorHandler() : ApplicationErrorHandler {
    return ServiceLocator.get(ApplicationErrorHandler);
  }

  private get http() : Http {
    return ServiceLocator.get(Http);
  }  

  /**
   * Realiza um put no endereço especificado.
   */
  public delete(url: string, config?: HttpConfigMethod): Observable<any> {
    let headers = this.getConfigHeaders();
    let finalURL = this.formatURL(url);
    console.log('Method delete: ' + finalURL);
    let options = this.buildRequestOptions(headers, config);
    return this.http.delete(URL_SERVER + '' + url, options).map(this.extractData).catch((error:any) => this.handleError(error));
  }

  /**
   * Realiza um put no endereço especificado.
   */
  public getOut(url: string, config?: HttpConfigMethod): Observable<any> {
    let headers = new Headers();
    headers.delete("Content-Type");
    headers.delete("Accept");
    console.log('Method get: ' + url);
    let options = this.buildRequestOptions(headers, config);
    return this.http.get(url, options).map(this.extractData).catch((error:any) => this.handleError(error));
  }

  /**
   * Realiza um put no endereço especificado.
   */
  public get(url: string, config?: HttpConfigMethod): Observable<any> {
    let headers = this.getConfigHeaders();
    let finalURL = this.formatURL(url);
    console.log('Method get: ' + finalURL);
    let options = this.buildRequestOptions(headers, config);
    return this.http.get(URL_SERVER + '' + url, options).map(this.extractData).catch((error:any) => this.handleError(error));
  }

  /**
   * Realiza um put no endereço especificado.
   */
  public put(url: string, config?: HttpConfigMethod): Observable<any> {
    let headers = this.getConfigHeaders();
    let finalURL = this.formatURL(url);
    console.log('Method put: ' + finalURL);
    let options = this.buildRequestOptions(headers, config);    
    return this.http.put(URL_SERVER + '' + url, { name }, options).map(this.extractData).catch((error:any) => this.handleError(error));
  }

  /**
   * Realiza um post no endereço especificado.
   */
  public post(url: string, config?: HttpConfigMethod): Observable<any> {
    let headers = this.getConfigHeaders();
    let finalURL = this.formatURL(url);
    console.log('Method post: ' + finalURL);
    let options = this.buildRequestOptions(headers, config);
    return this.http.post(finalURL, { name }, options).map(this.extractData).catch((error:any) => this.handleError(error));
  }

  /**
   * Método para criação padronizada do RequestOptions
   */
  private buildRequestOptions(headers: HttpHeaders, config?: HttpConfigMethod) : RequestOptions {
    let options = new RequestOptions({headers: headers, body: !!config && !!config.data? JSON.stringify(config.data) : null, params: new URLSearchParams()});
    if(!!config && !!config.params) {
      config.params.forEach((value: any, key: string) => {
        options.params.set(key, value);
      }); 
    }
    return options
  }

  private getConfigHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    let token: TokenDTO = this.tokenService.getToken();
    if (!!token) {
      console.log('Token: ' + token.token);
      headers.append('Authorization', token.token);
    }
    return headers;
  }

  private formatURL(url: string): string {
    return URL_SERVER + url;
  }

  private extractData(res: Response): Object {
    return res.json();
  }

  private handleError(error: Response | any) : Observable<any> {
    ServiceLocator.get(ApplicationErrorHandler).handleError(error);
    return Observable.throw(error);
  }
}