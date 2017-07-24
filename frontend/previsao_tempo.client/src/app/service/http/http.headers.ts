import {Headers} from "@angular/http";
/**
 * Header padrão para requisições.
 */

export class HttpHeaders extends Headers {

  constructor() {
    super();
    this.append("Content-Type", "application/json");
    this.append("Accept", "application/json");
    this.append('Access-Control-Allow-Headers', 'Authorization');
    this.append('Access-Control-Allow-Origin', '*');
    //this.append('Access-Control-Allow-Methods', '*');
    //this.append('Access-Control-Allow-Headers', 'X-Requested-With,Content-Type');
  }
}
