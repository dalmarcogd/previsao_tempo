import {BaseDTO} from "../base/base.dto";

/**
 * Representa um usuario.
 */
export class UserDTO extends BaseDTO {

  name: string = null;
  username: string = null;
  password: string = null;
  email: string = null;
}
