import {BaseDTO} from "../base/base.dto";

/**
 * Representa um token de autenticação do usuario.
 */
export class TokenDTO extends BaseDTO {

  token: string = null;
  username: string = null;
  password: string = null;
}